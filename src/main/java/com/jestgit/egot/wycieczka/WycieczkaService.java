package com.jestgit.egot.wycieczka;

import com.jestgit.egot.ksiazeczka.KsiazeczkaRepository;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczkiRepository;
import com.jestgit.egot.punkt.Punkt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;

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

    public ArrayList<Wycieczka> getWycieczkiNiezweryfikowane(){
        return repository.getAllByNiezweryfikowane();
    }

    public ArrayList<Wycieczka> getWycieczkiZweryfikowane(){
        return repository.getAllByZweryfikowane();
    }

    public Float[] getWycieczkaIDlugosci(ArrayList<PozycjaWycieczki> pozycjeWycieczki){
        Float[] wycieczkaIDlugosci = new Float[pozycjeWycieczki.size()];
        for (int i = 0; i < pozycjeWycieczki.size(); i++) {
            wycieczkaIDlugosci[i] = distanceBetweenPoints(pozycjeWycieczki.get(i).getNumerTrasy().getPunktPoczatkowy(), pozycjeWycieczki.get(i).getNumerTrasy().getPunktKoncowy());
        }
        return wycieczkaIDlugosci;
    }

    public Hashtable<Long, Float> getWycieczkiIDlugosci(ArrayList<Wycieczka> wycieczki){
        Hashtable<Long, Float> wycieczkiIDlugosci = new Hashtable<>();
        for (Wycieczka wycieczka : wycieczki){
            Long nrWycieczki = wycieczka.getNumerWycieczki();
            wycieczkiIDlugosci.put(nrWycieczki, calculateDlugoscWycieczki(pozycjaWycieczkiRepository.findAllByNumerWycieczki(nrWycieczki)));
        }
        return wycieczkiIDlugosci;
    }

    public Hashtable<Long, Float> getWycieczkiIPunkty(ArrayList<Wycieczka> wycieczki){
        Hashtable<Long, Float> wycieczkiIDlugosci = new Hashtable<>();
        for (Wycieczka wycieczka : wycieczki){
            Long nrWycieczki = wycieczka.getNumerWycieczki();
            wycieczkiIDlugosci.put(nrWycieczki, calculatePunktyZaWycieczke(pozycjaWycieczkiRepository.findAllByNumerWycieczki(nrWycieczki)));
        }
        return wycieczkiIDlugosci;
    }

    public Float calculateDlugoscWycieczki(ArrayList<PozycjaWycieczki> pozycjeWycieczki) {
        Float suma = 0f;
        for (PozycjaWycieczki pozycjaWycieczki : pozycjeWycieczki){
            suma += distanceBetweenPoints(pozycjaWycieczki.getNumerTrasy().getPunktPoczatkowy(), pozycjaWycieczki.getNumerTrasy().getPunktKoncowy());
        }
        return suma;
    }

    public Float calculatePunktyZaWycieczke(ArrayList<PozycjaWycieczki> pozycjeWycieczki){
        Float suma = 0f;
        for (PozycjaWycieczki pozycjaWycieczki : pozycjeWycieczki){
            String[] punkty = pozycjaWycieczki.getNumerTrasy().getPunktyZaTrase().split("/");
            suma += pozycjaWycieczki.getKierunek().equals("G") ? Float.parseFloat(punkty[0]) : Float.parseFloat(punkty[1]);
        }
        return suma;
    }

    public Float distanceBetweenPoints(Punkt p1, Punkt p2){
        Float R = 6371.0f;
        double lat1 = p1.getSzerokoscGeograficzna() * Math.PI / 180.0;
        double lat2 = p2.getSzerokoscGeograficzna() * Math.PI / 180.0;
        double long1 = p1.getDlugoscGeograficzna() * Math.PI / 180.0;
        double long2 = p2.getDlugoscGeograficzna() * Math.PI / 180.0;
        double dLong = long2 - long1;
        double dLat = lat2 - lat1;
        double a = Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLong/2) * Math.sin(dLong/2);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (float) (R * c);
    }

}
