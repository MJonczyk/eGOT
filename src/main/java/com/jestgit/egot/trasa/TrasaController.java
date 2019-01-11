package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrasaController {

    private final TrasaRepository repository;
    private final GrupaRepository grupaRepository;

    TrasaController(TrasaRepository repository, GrupaRepository grupaRepository) {
        this.repository = repository;
        this.grupaRepository = grupaRepository;
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
        modelAndView.addObject("trasaDto", new TrasaDTO());
        return modelAndView;
    }

    @PostMapping("/dodaj")
    public ModelAndView addTrasa(@ModelAttribute("trasaDto")TrasaDTO trasaDto){
        ModelAndView modelAndView = new ModelAndView("dodaj");
        Grupa grupa = grupaRepository.findById(trasaDto.getNazwaGrupy()).orElse(null);
        System.out.println(grupa.getNazwaGrupy());
        Trasa trasa1 = new Trasa(grupa, trasaDto.getPunktPoczatkowy(),trasaDto.getPunktKoncowy(),
                                 trasaDto.getPunktyZaTrase(), trasaDto.getOpis(), "S", "005/2007/W" ,1);
        repository.save(trasa1);
        repository.
        return modelAndView;
    }

}
