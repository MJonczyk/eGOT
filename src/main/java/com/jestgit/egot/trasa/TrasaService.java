package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import com.jestgit.egot.punkt.Punkt;
import com.jestgit.egot.punkt.PunktRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TrasaService {
    private final TrasaRepository repository;
    private final GrupaRepository grupaRepository;
    private final PunktRepository punktRepository;

    TrasaService(TrasaRepository repository, GrupaRepository grupaRepository, PunktRepository punktRepository) {
        this.repository = repository;
        this.grupaRepository = grupaRepository;
        this.punktRepository = punktRepository;
    }

    public void add(TrasaDTO trasaDto){
        Grupa grupa = grupaRepository.findById(trasaDto.getNazwaGrupy()).orElse(null);
        Punkt punktPoczatkowy = punktRepository.findById(trasaDto.getPunktPoczatkowy()).orElse(null);
        Punkt punktKoncowy = punktRepository.findById(trasaDto.getPunktKoncowy()).orElse(null);
        Trasa trasa1 = new Trasa(grupa, punktPoczatkowy, punktKoncowy,
                trasaDto.getPunktyZaTrase(), trasaDto.getOpis(), "S", "005/2007/W" ,1);
        repository.save(trasa1);
    }

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

    public void deleteTrasaById(Long numerTrasy){
        repository.deleteTrasaByNumerTrasy(numerTrasy);
    }

    public Punkt getPunktByNumber(Long number){
        return punktRepository.getOne(number);
    }

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

    public ArrayList<Punkt> getPunkty() {
        return (ArrayList<Punkt>) punktRepository.findAll();
    }

    public ArrayList<Trasa> getAll(){
        return (ArrayList) repository.findAll();
    }

    public Trasa getOne(Long id){
        return repository.getOne(id);
    }
}