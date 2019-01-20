package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import com.jestgit.egot.punkt.Punkt;
import com.jestgit.egot.punkt.PunktRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class that provides implementation of complex operations on Trasa data that are used in TrasaController
 * @author Michał Jończyk
 * @author Mateusz Wójcik
 * @version 1.2
 */
@Service
public class TrasaService {
    private final TrasaRepository repository;
    private final GrupaRepository grupaRepository;
    private final PunktRepository punktRepository;

    /**
     * Constructor of the Service, initializes required fields
     * @param repository provides CRUD and other basic operations on Trasas data
     * @param grupaRepository provides CRUD and other basic operations on Grupas data
     * @param punktRepository provides CRUD and other basic operations on Punkts data
     */
    TrasaService(TrasaRepository repository, GrupaRepository grupaRepository, PunktRepository punktRepository) {
        this.repository = repository;
        this.grupaRepository = grupaRepository;
        this.punktRepository = punktRepository;
    }

    /**
     * Method that adds Trasa to the database
     * @param trasaDto data used to create new Trasa
     */
    public void add(TrasaDTO trasaDto){
        Grupa grupa = grupaRepository.findById(trasaDto.getNazwaGrupy()).orElse(null);
        Punkt punktPoczatkowy = punktRepository.findById(trasaDto.getPunktPoczatkowy()).orElse(null);
        Punkt punktKoncowy = punktRepository.findById(trasaDto.getPunktKoncowy()).orElse(null);
        Trasa trasa1 = new Trasa(grupa, punktPoczatkowy, punktKoncowy,
                trasaDto.getPunktyZaTrase(), trasaDto.getOpis(), "S", "005/2007/W" ,1);
        repository.save(trasa1);
    }

    /**
     * Method that modifies chosen Trasa in the database
     * @param trasaDto data used to modify chosen Trasa
     */
    public void modify(TrasaDTO trasaDto){
        Grupa grupa = grupaRepository.findById(trasaDto.getNazwaGrupy()).orElse(null);
        Trasa trasaToUpdate = repository.getOne(Long.valueOf(trasaDto.getId().toString()));
        trasaToUpdate.setGrupaGorskanazwaGrupy(grupa);
        trasaToUpdate.setPunktPoczatkowy(punktRepository.findById(trasaDto.getPunktPoczatkowy()).orElse(null));
        trasaToUpdate.setPunktKoncowy(punktRepository.findById(trasaDto.getPunktKoncowy()).orElse(null));
        trasaToUpdate.setPunktyZaTrase(trasaDto.getPunktyZaTrase());
        trasaToUpdate.setOpis(trasaDto.getOpis());
        repository.save(trasaToUpdate);
    }

    /**
     * Method that deletes chosen Trasa from the database
     * @param numerTrasy Id of Trasa chosen to delete
     */
    public void deleteTrasaById(Long numerTrasy){
        repository.deleteTrasaByNumerTrasy(numerTrasy);
    }

    /**
     * Method that returns chosen Punkt from the database
     * @param number Id of the chosen Punkt
     * @return data about chosen Punkt
     */
    public Punkt getPunktByNumber(Long number){
        return punktRepository.getOne(number);
    }

    /**
     * Method that returns results of search
     * @param trasaSearchDTO contains search parameters
     * @return list of Trasas that meet the requirements
     */
    public ArrayList<Trasa> search(TrasaSearchDTO trasaSearchDTO){
        List<Trasa> trasy1;
        if(trasaSearchDTO.getNazwaGrupy() == null && trasaSearchDTO.getNazwaRegionu() == null && trasaSearchDTO.getPunktKoncowy().equals("")
          && trasaSearchDTO.getPunktPoczatkowy().equals("") && trasaSearchDTO.getPunktyMax() == null && trasaSearchDTO.getPunktyMin() == null
          && trasaSearchDTO.getSearchPhrase().equals(""))
            trasy1 = new ArrayList<>();
        else
            trasy1 = repository.search(trasaSearchDTO.getNazwaGrupy(), trasaSearchDTO.getNazwaRegionu(),
                                       trasaSearchDTO.getPunktPoczatkowy(), trasaSearchDTO.getPunktKoncowy(), trasaSearchDTO.getSearchPhrase());

        if(trasaSearchDTO.getPunktyMax() != null && trasaSearchDTO.getPunktyMin() != null) {
            Iterator<Trasa> iter = trasy1.iterator();
            while (iter.hasNext()) {
                Trasa t = iter.next();
                String[] pkt = t.getPunktyZaTrase().split("/");
                if (!((Integer.parseInt(pkt[0]) <= trasaSearchDTO.getPunktyMax() && Integer.parseInt(pkt[0]) >= trasaSearchDTO.getPunktyMin())
                        || (Integer.parseInt(pkt[1]) <= trasaSearchDTO.getPunktyMax() && Integer.parseInt(pkt[1]) >= trasaSearchDTO.getPunktyMin())))
                    iter.remove();
            }
        }
        else
        if(trasaSearchDTO.getPunktyMax() != null) {
            Iterator<Trasa> iter = trasy1.iterator();
            while (iter.hasNext()) {
                Trasa t = iter.next();
                String[] pkt = t.getPunktyZaTrase().split("/");
                if (!(Integer.parseInt(pkt[0]) <= trasaSearchDTO.getPunktyMax() || Integer.parseInt(pkt[1]) <= trasaSearchDTO.getPunktyMax()))
                    iter.remove();
            }
        }
        else
        if(trasaSearchDTO.getPunktyMin() != null) {
            Iterator<Trasa> iter = trasy1.iterator();
            while (iter.hasNext()) {
                Trasa t = iter.next();
                String[] pkt = t.getPunktyZaTrase().split("/");
                if (!(Integer.parseInt(pkt[0]) >= trasaSearchDTO.getPunktyMin() || Integer.parseInt(pkt[1]) >= trasaSearchDTO.getPunktyMin()))
                    iter.remove();
            }
        }
        return (ArrayList<Trasa>) trasy1;
    }

    /**
     * Method that returns all Punkts
     * @return list of Punkts from database
     */
    public ArrayList<Punkt> getPunkty() {
        return (ArrayList<Punkt>) punktRepository.findAll();
    }

    /**
     * Method that returns all Trasas
     * @return list of Trasas from database
     */
    public ArrayList<Trasa> getAll(){
        return (ArrayList) repository.findAll();
    }

    /**
     * Method that returns chosen Trasa from the database
     * @param id Id of the chosen Trasa
     * @return data about chosen Trasa
     */
    public Trasa getOne(Long id){
        return repository.getOne(id);
    }

    /**
     * Method that checks if given Trasa is unique
     * @param trasaDTO data about the Trasa
     * @param number
     * @return
     */
    public boolean isTrasaUnique(TrasaDTO trasaDTO, int number){
        Punkt pp = getPunktByNumber(trasaDTO.getPunktPoczatkowy());
        Punkt pk = getPunktByNumber(trasaDTO.getPunktKoncowy());
        return getAll().stream().filter(trasa -> trasaDTO.getNumerTrasy() != trasa.getNumerTrasy() && trasa.getPunktPoczatkowy().getNazwaPunktu().equals(pp.getNazwaPunktu())
                && trasa.getPunktKoncowy().getNazwaPunktu().equals(pk.getNazwaPunktu())).count() < number;
    }

    /**
     * Method that computes distance between two Punkts
     * @param p1 first Punkt
     * @param p2 second Punkt
     * @return distance between two points, in meters
     */
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