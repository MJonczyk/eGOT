package com.jestgit.egot.wycieczka;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class WycieczkaController {
    private WycieczkaService wycieczkaService;

    WycieczkaController(WycieczkaService wycieczkaService){
        this.wycieczkaService = wycieczkaService;
    }

    @GetMapping("/weryfikuj")
    public ModelAndView verifyTrasa(){
        ArrayList<Wycieczka> wycieczki = wycieczkaService.getAll();
        ModelAndView modelAndView = new ModelAndView("weryfikuj");
        modelAndView.addObject("wycieczki", wycieczki);
        return modelAndView;
    }

}
