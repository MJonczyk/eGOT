package com.jestgit.egot.trasa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/wyszukaj")
    public ModelAndView getAll() {
        ArrayList<Trasa> trasy = (ArrayList) repository.findAll();
        System.out.println(trasy.get(1).getGrupaGorskanazwaGrupy());
        ModelAndView modelAndView = new ModelAndView("wyszukaj");
        modelAndView.addObject("trasy", trasy);
        return modelAndView;
    }

    @PostMapping("/dodaj")
    public String addTrasa(@RequestBody Trasa trasa){
        return "dodaj";
    }

}
