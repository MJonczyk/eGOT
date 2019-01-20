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
        if(bindingResult.hasErrors() || !isTrasaUnique(trasa, 1)){
            modelAndView = new ModelAndView("dodaj", bindingResult.getModel());
            modelAndView.addObject("punkty", trasaService.getPunkty());
            modelAndView.addObject("srogiError", "error");
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
        modelAndView.addObject("odleglosc", distanceBetweenPoints(trasa.getPunktPoczatkowy(), trasa.getPunktKoncowy()));
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
        modelAndView.addObject("odleglosc", distanceBetweenPoints(trasa.getPunktPoczatkowy(), trasa.getPunktKoncowy()));
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
        if(bindingResult.hasErrors() || !isTrasaUnique(trasaDto, 1)){
            modelAndView = new ModelAndView("modyfikuj", bindingResult.getModel());
            Punkt punktPoczatkowy = trasaService.getOne(trasaDto.getNumerTrasy()).getPunktPoczatkowy();
            Punkt punktKoncowy = trasaService.getOne(trasaDto.getNumerTrasy()).getPunktKoncowy();
            modelAndView.addObject("punktPoczatkowy", punktPoczatkowy);
            modelAndView.addObject("punktKoncowy", punktKoncowy);
            modelAndView.addObject("punkty", trasaService.getPunkty());
            modelAndView.addObject("srogiError", "error");
            modelAndView.addObject("odleglosc", distanceBetweenPoints(punktPoczatkowy, punktKoncowy));
            return modelAndView;
        }
        else {
            trasaService.modify(trasaDto);
        }
        return modelAndView;
    }

    public boolean isTrasaUnique(TrasaDTO trasaDTO, int number){
        Punkt pp = trasaService.getPunktByNumber(trasaDTO.getPunktPoczatkowy());
        Punkt pk = trasaService.getPunktByNumber(trasaDTO.getPunktKoncowy());
        return trasaService.getAll().stream().filter(trasa -> trasaDTO.getNumerTrasy() != trasa.getNumerTrasy() && trasa.getPunktPoczatkowy().getNazwaPunktu().equals(pp.getNazwaPunktu())
                && trasa.getPunktKoncowy().getNazwaPunktu().equals(pk.getNazwaPunktu())).count() < number;
    }

    public Float distanceBetweenPoints(Punkt p1, Punkt p2){
        Float R = 6371.0f;
        double lat1 = p1.getSzerokoscGeograficzna() * Math.PI / 180.0;
        double lat2 = p2.getSzerokoscGeograficzna() * Math.PI / 180.0;
        double long1 = p1.getDlugoscGeograficzna() * Math.PI / 180.0;
        double long2 = p2.getDlugoscGeograficzna() * Math.PI / 180.0;
        double dLong = long2 - long1;
        double dLat = lat2 - lat1;
        double a = Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLong/2) * Math.sin(dLong/2);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (float) (R * c);
    }


}
