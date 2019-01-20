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
import java.util.concurrent.TimeUnit;

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
        modelAndView.addObject("wycieczkiIDlugosci", wycieczkaService.getWycieczkiIDlugosci(wycieczki));
        modelAndView.addObject("wycieczkiIPunkty", wycieczkaService.getWycieczkiIPunkty(wycieczki));
        return modelAndView;
    }

    @GetMapping("/wycieczki/niezweryfikowane")
    public ModelAndView getWycieczkiNiezweryfikowane(){
        ArrayList<Wycieczka> wycieczki = wycieczkaService.getWycieczkiNiezweryfikowane();
        ModelAndView modelAndView = new ModelAndView("wycieczki");
        modelAndView.addObject("wycieczki", wycieczki);
        modelAndView.addObject("wycieczkiIDlugosci", wycieczkaService.getWycieczkiIDlugosci(wycieczki));
        modelAndView.addObject("wycieczkiIPunkty", wycieczkaService.getWycieczkiIPunkty(wycieczki));
        modelAndView.addObject("jakieWycieczki", "n");
        return modelAndView;
    }

    @GetMapping("/wycieczki/zweryfikowane")
    public ModelAndView getWycieczkiZweryfikowane(){
        ArrayList<Wycieczka> wycieczki = wycieczkaService.getWycieczkiZweryfikowane();
        ModelAndView modelAndView = new ModelAndView("wycieczki");
        modelAndView.addObject("wycieczki", wycieczki);
        modelAndView.addObject("wycieczkiIDlugosci", wycieczkaService.getWycieczkiIDlugosci(wycieczki));
        modelAndView.addObject("wycieczkiIPunkty", wycieczkaService.getWycieczkiIPunkty(wycieczki));
        modelAndView.addObject("jakieWycieczki", "z");
        return modelAndView;
    }



    @RequestMapping("weryfikuj/{numerWycieczki}")
    public ModelAndView modifyWycieczka(@PathVariable Long numerWycieczki){
        ModelAndView modelAndView = new ModelAndView("weryfikuj");
        Wycieczka wycieczka = wycieczkaService.getOne(numerWycieczki);
        Long numerDecyzji = wycieczka.getDecyzja() == null ? null : wycieczka.getDecyzja().getNumerDecyzji();
        modelAndView.addObject("wycieczkaDto", new WycieczkaDTO(numerWycieczki, wycieczka.getNumerKsiazeczki().getNumerKsiazeczki(),
                wycieczka.getDataRozpoczecia(), wycieczka.getOpis(), wycieczka.getDataZakonczenia(),
                wycieczka.getOpiekun(), numerDecyzji));
        Long l = new Long(numerWycieczki);
        ArrayList<PozycjaWycieczki> pozycjeWycieczki = wycieczkaService.getPozycjeWycieczkiByNumerWycieczki(l);
        modelAndView.addObject("pozycjeWycieczki", pozycjeWycieczki);
        modelAndView.addObject("dlugoscWycieczki", wycieczkaService.calculateDlugoscWycieczki(pozycjeWycieczki));
        modelAndView.addObject("punktyZaWycieczke", wycieczkaService.calculatePunktyZaWycieczke(pozycjeWycieczki));
        modelAndView.addObject("dlugosci", wycieczkaService.getWycieczkaIDlugosci(pozycjeWycieczki));
        modelAndView.addObject("weryfikujDTO", new WeryfikujDTO());
        modelAndView.addObject("walidacja", validateWycieczka(wycieczka, pozycjeWycieczki));
        return modelAndView;
    }

    public String validateWycieczka(Wycieczka wycieczka, ArrayList<PozycjaWycieczki> pozycjeWycieczki){
        String isAccepted = "ok";

        if(wycieczka.getOpiekun() != null){
            System.out.println("opiekun");
            return "opiekun obecny na wycieczce";
        }
        if(pozycjeWycieczki.stream().anyMatch(pozycjaWycieczki -> pozycjaWycieczki.getDataRozpoczecia().equals(pozycjaWycieczki.getDataZakonczenia())
                && wycieczkaService.distanceBetweenPoints(pozycjaWycieczki.getNumerTrasy().getPunktPoczatkowy(), pozycjaWycieczki.getNumerTrasy().getPunktKoncowy()) > 20.0f)){
            System.out.println("2");
            return "zbyt długi dystans przebyty jednego dnia";
        }
        if(wycieczkaService.calculateDlugoscWycieczki(pozycjeWycieczki) > 100.0f){
            System.out.println("za dluga wycieczka");
            return "wycieczka zbyt długa";
        }
        if(pozycjeWycieczki.stream().anyMatch(pozycjaWycieczki ->
                pozycjeWycieczki.stream().filter(pozycjaWycieczki1 -> pozycjaWycieczki.getDataZakonczenia().equals(pozycjaWycieczki1.getDataZakonczenia())).count() > 20)){
            System.out.println("za duzo jednego dnia");
            return "zbyt wiele tras jednego dnia";
        }
        if(pozycjeWycieczki.size() > 100){
            System.out.println("za duzo tras");
            return "zbyt wiele tras w ramach wycieczki";
        }
        if((wycieczkaService.calculateDlugoscWycieczki(pozycjeWycieczki) /
                (TimeUnit.DAYS.convert(
                        Math.abs(wycieczka.getDataZakonczenia().getTime() - wycieczka.getDataRozpoczecia().getTime())
                            , TimeUnit.MILLISECONDS))) > 10.0){
            System.out.println("srednia na dzien zbyt duza");
            return "średnia liczba km/dzień zbyt duża";
        }

        return isAccepted;
    }

    @PostMapping("weryfikuj/{numerWycieczki}")
    public String postWeryfikuj(){

        return "";
    }

}
