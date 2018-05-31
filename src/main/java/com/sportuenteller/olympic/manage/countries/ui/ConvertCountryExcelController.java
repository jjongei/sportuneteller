package com.sportuenteller.olympic.manage.countries.ui;

import com.sportuenteller.olympic.common.convert.FileNotUploadException;
import com.sportuenteller.olympic.common.convert.MultipartConvert;
import com.sportuenteller.olympic.manage.countries.application.convert.ConvertCountryExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("manage/countries")
public class ConvertCountryExcelController {
    @Autowired
    ConvertCountryExcelService service;
    @Autowired
    View downloadView;

    @RequestMapping(value ="upload", method = RequestMethod.POST)
    String postUpload(@RequestParam("files") MultipartFile[] mFiles){
        File file = null;
        try {
            file = MultipartConvert.convertFirst(mFiles);
        } catch (IOException e) {
            throw new FileNotUploadException(e, "Country list excel file");
        }
        service.upload(file);
        return "redirect:/manage/countries";
    }

    @RequestMapping(value ="download", method = RequestMethod.GET)
    ModelAndView getDownload(Model model){
        File file = service.download(null);

        ModelAndView mav = new ModelAndView();
        mav.addObject("downloadFile", file);
        mav.addObject("downloadFileName", "Country("+Long.toString(System.nanoTime())+").xlsx");
        mav.setView(this.downloadView);
        return mav;
    }
}
