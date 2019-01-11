package com.jestgit.egot.pozycjawycieczki;

import com.jestgit.egot.trasa.TrasaRepository;
import com.jestgit.egot.wycieczka.WycieczkaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PozycjaWycieczkiService {
    private final PozycjaWycieczkiRepository pozycjaWycieczkiRepository;
    private final WycieczkaRepository wycieczkaRepository;
    private final TrasaRepository trasaRepository;

    public PozycjaWycieczkiService(PozycjaWycieczkiRepository pozycjaWycieczkiRepository, WycieczkaRepository wycieczkaRepository, TrasaRepository trasaRepository) {
        this.pozycjaWycieczkiRepository = pozycjaWycieczkiRepository;
        this.wycieczkaRepository = wycieczkaRepository;
        this.trasaRepository = trasaRepository;
    }

    public ArrayList<PozycjaWycieczki> getAll(){
        return (ArrayList) trasaRepository.findAll();
    }
}
