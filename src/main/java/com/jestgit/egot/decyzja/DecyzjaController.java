package com.jestgit.egot.decyzja;

import com.jestgit.egot.WeryfikujDTO;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import com.jestgit.egot.wycieczka.Wycieczka;
import com.jestgit.egot.wycieczka.WycieczkaDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
        String uzasadnienie = isAccepted ? "Zgoda" : "";
        modelAndView.addObject("decyzjaDto", new DecyzjaDTO(numerWycieczki, "a", isAccepted.toString(), new Date(), uzasadnienie));
        modelAndView.addObject("nextDecisionNumber", decyzjaService.getNexDecisionNumber());
        return modelAndView;
    }

    @PostMapping("weryfikuj/{numerWycieczki}/{isAccepted}")
    public ModelAndView postDecyzja(@ModelAttribute("decyzjaDto") @Valid DecyzjaDTO decyzjaDTO, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/wycieczki");

        decyzjaDTO.setData(new Date());
        if(bindingResult.hasErrors()){
            modelAndView = new ModelAndView("decyzja", bindingResult.getModel());
            modelAndView.addObject("nextDecisionNumber", decyzjaService.getNexDecisionNumber());
            return modelAndView;
        }
        else {
            String zatwierdzenie = Boolean.parseBoolean(decyzjaDTO.getCzyZatwierdzona()) ? "T" :"N";
            decyzjaDTO.setCzyZatwierdzona(zatwierdzenie);
            decyzjaService.add(decyzjaDTO);
        }
        return modelAndView;
    }
}
