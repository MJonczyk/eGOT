package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.grupa.GrupaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrasaService {
    private final TrasaRepository repository;
    private final GrupaRepository grupaRepository;

    TrasaService(TrasaRepository repository, GrupaRepository grupaRepository) {
        this.repository = repository;
        this.grupaRepository = grupaRepository;
    }

    public void add(TrasaDTO trasaDto){
        Grupa grupa = grupaRepository.findById(trasaDto.getNazwaGrupy()).orElse(null);
        System.out.println(grupa.getNazwaGrupy());
        Trasa trasa1 = new Trasa(grupa, trasaDto.getPunktPoczatkowy(),trasaDto.getPunktKoncowy(),
                trasaDto.getPunktyZaTrase(), trasaDto.getOpis(), "S", "005/2007/W" ,1);
        repository.save(trasa1);
    }

    public ArrayList<Trasa> getAll(){
        return (ArrayList) repository.findAll();
    }
}
