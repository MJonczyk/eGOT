package com.jestgit.egot.wycieczka;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import com.jestgit.egot.ksiazeczka.Ksiazeczka;
import com.jestgit.egot.ksiazeczka.KsiazeczkaRepository;
import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.trasa.TrasaDTO;
import com.jestgit.egot.trasa.TrasaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WycieczkaService {

    private final WycieczkaRepository repository;
    private final KsiazeczkaRepository ksiazeczkaRepository;

    WycieczkaService(WycieczkaRepository repository, KsiazeczkaRepository ksiazeczkaRepository) {
        this.repository = repository;
        this.ksiazeczkaRepository = ksiazeczkaRepository;
    }

    public ArrayList<Wycieczka> getAll(){
        return (ArrayList) repository.findAll();
    }

}
