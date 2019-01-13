package com.jestgit.egot.wycieczka;

import com.jestgit.egot.ksiazeczka.KsiazeczkaRepository;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczkiRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WycieczkaService {

    private final WycieczkaRepository repository;
    private final KsiazeczkaRepository ksiazeczkaRepository;
    private final PozycjaWycieczkiRepository pozycjaWycieczkiRepository;

    WycieczkaService(WycieczkaRepository repository, KsiazeczkaRepository ksiazeczkaRepository, PozycjaWycieczkiRepository pozycjaWycieczkiRepository) {
        this.repository = repository;
        this.ksiazeczkaRepository = ksiazeczkaRepository;
        this.pozycjaWycieczkiRepository = pozycjaWycieczkiRepository;
    }

    public Wycieczka getOne(Long id){
        return repository.getOne(id);
    }

    public ArrayList<Wycieczka> getAll(){
        return (ArrayList) repository.findAll();
    }

    public ArrayList<PozycjaWycieczki> getPozycjeWycieczkiByNumerWycieczki(Long numerWycieczki){
        return pozycjaWycieczkiRepository.findAllByNumerWycieczki(numerWycieczki);
    }

}
