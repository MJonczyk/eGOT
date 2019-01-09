package com.jestgit.egot.trasa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrasaController {

    private final TrasaRepository repository;

    TrasaController(TrasaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/main")
    public String all() {
        return "main";
    }

    @GetMapping("/trasy")
    public ModelAndView getAll(){
        ArrayList<Trasa> trasy = (ArrayList) repository.findAll();
        ModelAndView modelAndView = new ModelAndView("wyswietl");
        modelAndView.addObject("trasyy", trasy);

        return modelAndView;
    }

    @GetMapping("/dodaj")
    public String dodaj(){
        return "dodaj";
    }

    @GetMapping("/wyszukaj")
    public String wyszukaj(){
        return "wyszukaj";
    }

    @GetMapping("/wyswietl")
    public String wyswietl(){
        return "wyswietl";
    }


//    @GetMapping("/trasy")
//    public String getTrasy(){
//        return "trasy";
//    }

}
