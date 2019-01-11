package com.jestgit.egot.trasa;


import lombok.Data;

@Data
public class TrasaDTO {
    private String nazwaGrupy;

    private String punktPoczatkowy;

    private String punktKoncowy;

    private String punktyZaTrase;

    private String opis;

    private Object id;

    public TrasaDTO(){}

    public TrasaDTO(String grupa, String punktPoczatkowy, String punktKoncowy, String punktyZaTrase, String opis, Object id){
        this.nazwaGrupy = grupa;
        this.punktPoczatkowy = punktPoczatkowy;
        this.punktKoncowy = punktKoncowy;
        this.punktyZaTrase = punktyZaTrase;
        this.opis = opis;
        this.id = id;
    }
}
