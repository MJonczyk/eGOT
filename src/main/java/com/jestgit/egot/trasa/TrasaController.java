package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import com.jestgit.egot.wycieczka.Wycieczka;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView addTrasa(@ModelAttribute("trasaDto") @Valid TrasaDTO trasa, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("dodaj");
        if(bindingResult.hasErrors()){
            return new ModelAndView("dodaj", bindingResult.getModel());
        }
        else {
            trasaService.add(trasa);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/modyfikuj/{numerTrasy}")
    public ModelAndView modifyForm(@PathVariable Long numerTrasy){
        ModelAndView modelAndView = new ModelAndView("modyfikuj");
        Trasa trasa = trasaService.getOne(numerTrasy);
        modelAndView.addObject("trasaDto", new TrasaDTO(trasa.getGrupaGorskanazwaGrupy().getNazwaGrupy(),
                trasa.getPunktPoczatkowy(), trasa.getPunktKoncowy(), trasa.getPunktyZaTrase(), trasa.getOpis(), trasa.getNumerTrasy()));
        return modelAndView;
    }

    @PostMapping("/modyfikuj/{numerTrasy}")
    public ModelAndView modifyTrasa(@ModelAttribute("trasaDto") @Valid TrasaDTO trasaDto, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("modyfikuj");
        if(bindingResult.hasErrors()){
            return new ModelAndView("modyfikuj", bindingResult.getModel());
        }
        else {
            trasaService.modify(trasaDto);
        }
        return modelAndView;
    }

}
