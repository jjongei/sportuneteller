package com.sportuenteller.olympic.manage.countries.ui;

import com.sportuenteller.olympic.manage.countries.application.query.CountryListPaging;
import com.sportuenteller.olympic.manage.countries.application.query.CountryListPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("manage/countries")
public class FindCountriesPageController {

    @Autowired
    CountryListPagingService service;

    @RequestMapping(method = RequestMethod.GET)
    String getList(Model model){
        CountryListPaging intPage = new CountryListPaging(1, 30);

        CountryListPaging page = service.findCountryPage(intPage.getParams(), intPage.getPageRequest());
        model.addAttribute("dossiers", page);
        return "manage/country/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.POST)
    String postSearch(Model model, CountryListPaging request){
        CountryListPaging page = service.findCountryPage(request.getParams(), request.getPageRequest());
        model.addAttribute("dossiers", page);
        return "manage/country/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.GET)
    String postSearch(Model model){
        return "redirect:/manage/countries";
    }
}
