package com.jestgit.egot.decyzja;

import com.jestgit.egot.WeryfikujDTO;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import com.jestgit.egot.wycieczka.Wycieczka;
import com.jestgit.egot.wycieczka.WycieczkaDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DecyzjaController {
    private DecyzjaService decyzjaService;

    DecyzjaController(DecyzjaService decyzjaService){
        this.decyzjaService = decyzjaService;
    }

    @RequestMapping("weryfikuj/{numerWycieczki}/{isAccepted}")
    public ModelAndView getDecyzja(@PathVariable Long numerWycieczki, @PathVariable Boolean isAccepted){
        ModelAndView modelAndView = new ModelAndView("decyzja");
        modelAndView.addObject("decyzjaDto", new DecyzjaDTO(numerWycieczki, "a", isAccepted.toString(), new Date(), "a"));
        modelAndView.addObject("nextDecisionNumber", decyzjaService.getNexDecisionNumber());
        return modelAndView;
    }

    @PostMapping("weryfikuj/{numerWycieczki}/{isAccepted}")
    public String postDecyzja(@ModelAttribute("decyzjaDto")DecyzjaDTO decyzjaDTO){
        decyzjaService.add(decyzjaDTO);
        return "redirect:/wycieczki";
    }
}
