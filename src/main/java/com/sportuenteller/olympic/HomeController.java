package com.sportuenteller.olympic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    String home(Model model){
        return "redirect:/vote/teams";
    }
}
