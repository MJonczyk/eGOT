package com.jestgit.egot.trasa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrasaController {

    private final TrasaRepository repository;

    TrasaController(TrasaRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/index", "/"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/wyswietl")
    public ModelAndView getAll() {
        ArrayList<Trasa> trasy = (ArrayList) repository.findAll();
        ModelAndView modelAndView = new ModelAndView("wyswietl");
        modelAndView.addObject("trasy", trasy);
        return modelAndView;
    }

    @GetMapping("/wyszukaj")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView("wyszukaj");
        return modelAndView;
    }

    @GetMapping("/dodaj")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("dodaj");
        return modelAndView;
    }

    @PostMapping("/dodaj")
    public ModelAndView addTrasa(@RequestBody Trasa trasa){
        ModelAndView modelAndView = new ModelAndView("dodaj");
        return modelAndView;
    }

}
