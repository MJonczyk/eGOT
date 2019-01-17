package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import com.jestgit.egot.punkt.Punkt;
import com.jestgit.egot.punkt.PunktRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
