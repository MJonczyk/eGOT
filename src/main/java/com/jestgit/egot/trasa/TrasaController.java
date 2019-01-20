package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import com.jestgit.egot.punkt.Punkt;
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
        modelAndView.addObject("trasaSearchDto", new TrasaSearchDTO());
        return modelAndView;
    }

    @GetMapping("/wyniki")
    public ModelAndView searchResults(@ModelAttribute("trasaSearchDto") TrasaSearchDTO trasaSearchDto ) {
        ModelAndView modelAndView = new ModelAndView("wyniki");
        modelAndView.addObject("trasy", trasaService.search(trasaSearchDto));
        return modelAndView;
    }

    @GetMapping("/dodaj")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("dodaj");
        modelAndView.addObject("trasaDto", new TrasaDTO());
        modelAndView.addObject("punkty", trasaService.getPunkty());
        return modelAndView;
    }

    @PostMapping("/dodaj")
    public ModelAndView addTrasa(@ModelAttribute("trasaDto") @Valid TrasaDTO trasa, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/wyswietl#dodano");
        if(bindingResult.hasErrors()){
            modelAndView = new ModelAndView("dodaj", bindingResult.getModel());
            modelAndView.addObject("punkty", trasaService.getPunkty());
            return modelAndView;
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
        modelAndView.addObject("trasaDto", new TrasaDTO(numerTrasy, trasa.getGrupaGorskanazwaGrupy().getNazwaGrupy(),
                trasa.getPunktPoczatkowy().getIdPunktu(), trasa.getPunktKoncowy().getIdPunktu(), trasa.getPunktyZaTrase(), trasa.getOpis(), trasa.getNumerTrasy()));
        modelAndView.addObject("punktPoczatkowy", trasa.getPunktPoczatkowy());
        modelAndView.addObject("punktKoncowy", trasa.getPunktKoncowy());
        modelAndView.addObject("punkty", trasaService.getPunkty());
        return modelAndView;
    }

    @GetMapping("/usun")
    public ModelAndView getUsun() {
        ArrayList<Trasa> trasy = trasaService.getAll();
        ModelAndView modelAndView = new ModelAndView("wyswietl");
        modelAndView.addObject("usun", "usun");
        modelAndView.addObject("trasy", trasy);
        return modelAndView;
    }

    @RequestMapping(value = "/usun/{numerTrasy}")
    public ModelAndView usunForm(@PathVariable Long numerTrasy){
        ModelAndView modelAndView = new ModelAndView("usun");
        Trasa trasa = trasaService.getOne(numerTrasy);
        modelAndView.addObject("trasaDto", new TrasaDTO(numerTrasy, trasa.getGrupaGorskanazwaGrupy().getNazwaGrupy(),
                trasa.getPunktPoczatkowy().getIdPunktu(), trasa.getPunktKoncowy().getIdPunktu(), trasa.getPunktyZaTrase(), trasa.getOpis(), trasa.getNumerTrasy()));
        modelAndView.addObject("punktPoczatkowy", trasa.getPunktPoczatkowy());
        modelAndView.addObject("punktKoncowy", trasa.getPunktKoncowy());
        modelAndView.addObject("punkty", trasaService.getPunkty());
        return modelAndView;
    }

    @PostMapping("/usun/{numerTrasy}")
    public ModelAndView usunTrasa(@ModelAttribute("trasaDto") TrasaDTO trasaDto){
        ModelAndView modelAndView = new ModelAndView("redirect:/usun#usunieto");
        trasaService.deleteTrasaById(trasaDto.getNumerTrasy());
        return modelAndView;
    }


    @PostMapping("/modyfikuj/{numerTrasy}")
    public ModelAndView modifyTrasa(@ModelAttribute("trasaDto") @Valid TrasaDTO trasaDto, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/wyswietl#success");
        if(bindingResult.hasErrors() || !isTrasaUnique(trasaDto)){
            modelAndView = new ModelAndView("modyfikuj", bindingResult.getModel());
            Punkt punktPoczatkowy = trasaService.getOne(trasaDto.getNumerTrasy()).getPunktPoczatkowy();
            Punkt punktKoncowy = trasaService.getOne(trasaDto.getNumerTrasy()).getPunktKoncowy();
            modelAndView.addObject("punktPoczatkowy", punktPoczatkowy);
            modelAndView.addObject("punktKoncowy", punktKoncowy);
            modelAndView.addObject("punkty", trasaService.getPunkty());
            modelAndView.addObject("srogiError", "error");
            return modelAndView;
        }
        else {
            trasaService.modify(trasaDto);
        }
        return modelAndView;
    }

    public boolean isTrasaUnique(TrasaDTO trasaDTO){
        Punkt pp = trasaService.getPunktByNumber(trasaDTO.getPunktPoczatkowy());
        Punkt pk = trasaService.getPunktByNumber(trasaDTO.getPunktKoncowy());
        return trasaService.getAll().stream().filter(trasa -> trasaDTO.getNumerTrasy() != trasa.getNumerTrasy() && trasa.getPunktPoczatkowy().getNazwaPunktu().equals(pp.getNazwaPunktu())
                && trasa.getPunktKoncowy().getNazwaPunktu().equals(pk.getNazwaPunktu())).count() < 1;
    }

}
