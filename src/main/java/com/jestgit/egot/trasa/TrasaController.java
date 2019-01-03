package com.jestgit.egot.trasa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrasaController {

    private final TrasaRepository repository;

    TrasaController(TrasaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trasy")
    List<Trasa> all() {
        return repository.findAll();
    }

    @PostMapping("/trasy")
    Trasa newTrasa(@RequestBody Trasa trasa){
        return repository.save(trasa);
    }
}
