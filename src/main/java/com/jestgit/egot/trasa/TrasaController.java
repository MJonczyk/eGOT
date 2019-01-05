package com.jestgit.egot.trasa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class TrasaController {

    private final TrasaRepository repository;

    TrasaController(TrasaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/main")
    public String all() {
        System.out.println("YOLO");
        return "main";
    }

    @PostMapping("/trasy")
    Trasa newTrasa(@RequestBody Trasa trasa){
        return repository.save(trasa);
    }
}
