package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class TrasaController {

    private TrasaService trasaService;

    TrasaController(TrasaService trasaService) {
        this.trasaService = trasaService;
    }

    @GetMapping({"/index", "/"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/wyswietl")
    public ModelAndView getAll() {
        ArrayList<Trasa> trasy = trasaService.getAll();
        ModelAndView modelAndView = new ModelAndView("wyswietl");
        modelAndView.addObject("trasy", trasy);
        return modelAndView;
    }

    @GetMapping("/wyszukaj")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView("wyszukaj");
        modelAndView.addObject("trasaDto", new TrasaDTO());
        return modelAndView;
    }

    @GetMapping("/wyniki")
    public ModelAndView searchResults() {
        ModelAndView modelAndView = new ModelAndView("wyniki");
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
        trasaService.add(trasaDto);
        return modelAndView;
    }

    @GetMapping("/modyfikuj")
    public ModelAndView modifyTrasa(){
        ModelAndView modelAndView = new ModelAndView("modyfikuj");
        return modelAndView;
    }

    @GetMapping("/weryfikuj")
    public ModelAndView verifyTrasa(){
        ModelAndView modelAndView = new ModelAndView("weryfikuj");
        return modelAndView;
    }
}
