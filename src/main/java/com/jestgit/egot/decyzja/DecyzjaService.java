package com.jestgit.egot.decyzja;

import com.jestgit.egot.przodownik.Przodownik;
import com.jestgit.egot.przodownik.PrzodownikRepository;
import com.jestgit.egot.wycieczka.Wycieczka;
import com.jestgit.egot.wycieczka.WycieczkaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DecyzjaService {
    private final DecyzjaRepository decyzjaRepository;
    private final WycieczkaRepository wycieczkaRepository;
    private final PrzodownikRepository przodownikRepository;

    public DecyzjaService(DecyzjaRepository decyzjaRepository, WycieczkaRepository wycieczkaRepository, PrzodownikRepository przodownikRepository) {
        this.decyzjaRepository = decyzjaRepository;
        this.wycieczkaRepository = wycieczkaRepository;
        this.przodownikRepository = przodownikRepository;
    }

    public void add(DecyzjaDTO decyzjaDTO){
        Wycieczka wycieczka = wycieczkaRepository.findById(decyzjaDTO.getNumerWycieczki()).orElse(null);
        Przodownik przodownik = przodownikRepository.findAll().get(0);
        Date date = new Date();
        Decyzja decyzja = new Decyzja(wycieczka, przodownik, decyzjaDTO.getCzyZatwierdzona(), date, decyzjaDTO.getUzasadnienie());
        decyzjaRepository.save(decyzja);
    }

    public Long getNexDecisionNumber(){
        Long number = decyzjaRepository.findAll().isEmpty() ? 0 : decyzjaRepository.findAll().get(decyzjaRepository.findAll().size() - 1).getNumerDecyzji();
        return ++number;
    }
}
