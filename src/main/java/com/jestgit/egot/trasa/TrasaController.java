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

/**
 * A class that maps http requests to the proper method
 * @author Michał Jończyk
 * @author Mateusz Wójcik
 * @version 1.2
 */
@Controller
public class TrasaController {

    /**
     * Instance of TrasaService used to compute complex operations
     */
    private TrasaService trasaService;

    /**
     * Constructor of the Controller, initializes required fields
     * @param trasaService object that provides implementation of complex operations on Trasa's data
     */
    TrasaController(TrasaService trasaService) {
        this.trasaService = trasaService;
    }

    /**
     * Method that processes GET request (/index, /) and creates suitable View using proper Model
     * @return name of View that will be generated with Model data
     */
    @GetMapping({"/index", "/"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    /**
     * Method that processes GET request (/wyswietl) and creates suitable View using proper Model
     * @return name of the View that will present list of Trasas that are in the database
     */
    @GetMapping("/wyswietl")
    public ModelAndView getAll() {
        ArrayList<Trasa> trasy = trasaService.getAll();
        ModelAndView modelAndView = new ModelAndView("wyswietl");
        modelAndView.addObject("trasy", trasy);
        return modelAndView;
    }

    /**
     * Method that processes GET request (/wyszukaj) and creates suitable View using proper Model
     * @return name of the View that will present the search form with needed data
     */
    @GetMapping("/wyszukaj")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView("wyszukaj");
        modelAndView.addObject("trasaSearchDto", new TrasaSearchDTO());
        return modelAndView;
    }

    /**
     * Method that processes GET request (/wyniki) and creates suitable View using proper Model
     * @param trasaSearchDto object that transfers data about search parameters
     * @return name of the View that will present results of the search
     */
    @GetMapping("/wyniki")
    public ModelAndView searchResults(@ModelAttribute("trasaSearchDto") TrasaSearchDTO trasaSearchDto ) {
        ModelAndView modelAndView = new ModelAndView("wyniki");
        modelAndView.addObject("trasy", trasaService.search(trasaSearchDto));
        return modelAndView;
    }

    /**
     * Method that processes GET request (/dodaj) and creates suitable View using proper Model
     * @return name of the View that will present the add form with needed data
     */
    @GetMapping("/dodaj")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("dodaj");
        modelAndView.addObject("trasaDto", new TrasaDTO());
        modelAndView.addObject("punkty", trasaService.getPunkty());
        return modelAndView;
    }

    /**
     * Method that processes POST request (/dodaj) and creates suitable View using proper Model
     * @param trasa data of a Trasa that is going to be added
     * @param bindingResult object that contains validation data
     * @return name of the View that will present whether the operation was successful or not
     */
    @PostMapping("/dodaj")
    public ModelAndView addTrasa(@ModelAttribute("trasaDto") @Valid TrasaDTO trasa, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/wyswietl#dodano");
        if(bindingResult.hasErrors() || !trasaService.isTrasaUnique(trasa, 1)){
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

    /**
     * Method that processes http request (/modyfikuj/{numerTrasy}) and creates suitable View using proper Model
     * @param numerTrasy Id of the Trasa to modify
     * @return name of the View that will present the modify form with needed data
     */
    @RequestMapping(value = "/modyfikuj/{numerTrasy}")
    public ModelAndView modifyForm(@PathVariable Long numerTrasy){
        ModelAndView modelAndView = new ModelAndView("modyfikuj");
        Trasa trasa = trasaService.getOne(numerTrasy);
        modelAndView.addObject("trasaDto", new TrasaDTO(numerTrasy, trasa.getGrupaGorskanazwaGrupy().getNazwaGrupy(),
                trasa.getPunktPoczatkowy().getIdPunktu(), trasa.getPunktKoncowy().getIdPunktu(), trasa.getPunktyZaTrase(), trasa.getOpis(), trasa.getNumerTrasy()));
        modelAndView.addObject("punktPoczatkowy", trasa.getPunktPoczatkowy());
        modelAndView.addObject("punktKoncowy", trasa.getPunktKoncowy());
        modelAndView.addObject("punkty", trasaService.getPunkty());
        modelAndView.addObject("odleglosc", trasaService.distanceBetweenPoints(trasa.getPunktPoczatkowy(), trasa.getPunktKoncowy()));
        return modelAndView;
    }

    /**
     * Method that processes GET request (/usun) and creates suitable View using proper Model
     * @return name of the View that will present the list of Trasas available to delete
     */
    @GetMapping("/usun")
    public ModelAndView getUsun() {
        ArrayList<Trasa> trasy = trasaService.getAll();
        ModelAndView modelAndView = new ModelAndView("wyswietl");
        modelAndView.addObject("usun", "usun");
        modelAndView.addObject("trasy", trasy);
        return modelAndView;
    }

    /**
     * Method that processes http request (/usun/{numerTrasy}) and creates suitable View using proper Model
     * @param numerTrasy Id of the Trasa to delete
     * @return name of the View that will present the delete form with needed data
     */
    @RequestMapping(value = "/usun/{numerTrasy}")
    public ModelAndView usunForm(@PathVariable Long numerTrasy){
        ModelAndView modelAndView = new ModelAndView("usun");
        Trasa trasa = trasaService.getOne(numerTrasy);
        modelAndView.addObject("trasaDto", new TrasaDTO(numerTrasy, trasa.getGrupaGorskanazwaGrupy().getNazwaGrupy(),
                trasa.getPunktPoczatkowy().getIdPunktu(), trasa.getPunktKoncowy().getIdPunktu(), trasa.getPunktyZaTrase(), trasa.getOpis(), trasa.getNumerTrasy()));
        modelAndView.addObject("punktPoczatkowy", trasa.getPunktPoczatkowy());
        modelAndView.addObject("punktKoncowy", trasa.getPunktKoncowy());
        modelAndView.addObject("punkty", trasaService.getPunkty());
        modelAndView.addObject("odleglosc", trasaService.distanceBetweenPoints(trasa.getPunktPoczatkowy(), trasa.getPunktKoncowy()));
        return modelAndView;
    }

    /**
     * Method that processes POST request (/usun/{numerTrasy}) and creates suitable View using proper Model
     * @param trasaDto data of a Trasa that is going to be deleted
     * @return name of the View that will present whether the operation was successful or not
     */
    @PostMapping("/usun/{numerTrasy}")
    public ModelAndView usunTrasa(@ModelAttribute("trasaDto") TrasaDTO trasaDto){
        ModelAndView modelAndView = new ModelAndView("redirect:/usun#usunieto");
        trasaService.deleteTrasaById(trasaDto.getNumerTrasy());
        return modelAndView;
    }


    /**
     * Method that processes POST request (/modyfikuj/{numerTrasy}) and creates suitable View using proper Model
     * @param trasaDto data of a Trasa that is going to be modified
     * @param bindingResult object that contains validation data
     * @return name of the View that will present whether the operation was successful or not
     */
    @PostMapping("/modyfikuj/{numerTrasy}")
    public ModelAndView modifyTrasa(@ModelAttribute("trasaDto") @Valid TrasaDTO trasaDto, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/wyswietl#success");
        if(bindingResult.hasErrors() || !trasaService.isTrasaUnique(trasaDto, 1)){
            modelAndView = new ModelAndView("modyfikuj", bindingResult.getModel());
            Punkt punktPoczatkowy = trasaService.getOne(trasaDto.getNumerTrasy()).getPunktPoczatkowy();
            Punkt punktKoncowy = trasaService.getOne(trasaDto.getNumerTrasy()).getPunktKoncowy();
            modelAndView.addObject("punktPoczatkowy", punktPoczatkowy);
            modelAndView.addObject("punktKoncowy", punktKoncowy);
            modelAndView.addObject("punkty", trasaService.getPunkty());
            modelAndView.addObject("srogiError", "error");
            modelAndView.addObject("odleglosc", trasaService.distanceBetweenPoints(punktPoczatkowy, punktKoncowy));
            return modelAndView;
        }
        else {
            trasaService.modify(trasaDto);
        }
        return modelAndView;
    }
}
