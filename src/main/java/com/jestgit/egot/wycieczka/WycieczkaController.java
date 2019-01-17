package com.jestgit.egot.wycieczka;

import com.jestgit.egot.WeryfikujDTO;
import com.jestgit.egot.decyzja.Decyzja;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/wycieczki")
    public ModelAndView getWycieczki(){
        ArrayList<Wycieczka> wycieczki = wycieczkaService.getAll();
        ModelAndView modelAndView = new ModelAndView("wycieczki");
        modelAndView.addObject("wycieczki", wycieczki);
        return modelAndView;
    }

    @RequestMapping("weryfikuj/{numerWycieczki}")
    public ModelAndView modifyWycieczka(@PathVariable Long numerWycieczki){
        ModelAndView modelAndView = new ModelAndView("weryfikuj");
        Wycieczka wycieczka = wycieczkaService.getOne(numerWycieczki);
        Long numerDecyzji = wycieczka.getDecyzja() == null ? null : wycieczka.getDecyzja().getNumerDecyzji();
        modelAndView.addObject("wycieczkaDto", new WycieczkaDTO(numerWycieczki, wycieczka.getNumerKsiazeczki().getNumerKsiazeczki(),
                wycieczka.getPunkty(), wycieczka.getDataOdbycia(), wycieczka.getOpis(), wycieczka.getDlugosc(), wycieczka.getDataZakonczenia(),
                wycieczka.getOpiekun(), numerDecyzji));
        Long l = new Long(1);
        ArrayList<PozycjaWycieczki> pozycjeWycieczki = wycieczkaService.getPozycjeWycieczkiByNumerWycieczki(l);
        modelAndView.addObject("pozycjeWycieczki", pozycjeWycieczki);
        modelAndView.addObject("weryfikujDTO", new WeryfikujDTO());
        return modelAndView;
    }

    @PostMapping("weryfikuj/{numerWycieczki}")
    public String postWeryfikuj(){

        return "";
    }

    //
//    @PostMapping("/weryfikuj/{numerTrasy}")
//    public ModelAndView modifyTrasa(@ModelAttribute("trasaDto")TrasaDTO trasaDto){
//        ModelAndView modelAndView = new ModelAndView("modyfikuj");
//        trasaService.modify(trasaDto);
//        return modelAndView;
//    }

}
