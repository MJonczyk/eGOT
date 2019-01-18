package com.jestgit.egot.trasa;

import lombok.Data;

@Data
public class TrasaSearchDTO {

    private String searchPhrase;

    private String nazwaRegionu;

    private String nazwaGrupy;

    private String punktPoczatkowy;

    private String punktKoncowy;

    private Integer punktyMax;

    private Integer punktyMin;

    public TrasaSearchDTO(){}

    public TrasaSearchDTO(String searchPhrase, String nazwaRegionu, String nazwaGrupy, String punktPoczatkowy, String punktKoncowy, Integer punktyMax, Integer punktyMin){
        this.searchPhrase = searchPhrase;
        this.nazwaRegionu = nazwaRegionu;
        this.nazwaGrupy = nazwaGrupy;
        this.punktPoczatkowy = punktPoczatkowy;
        this.punktKoncowy = punktKoncowy;
        this.punktyMax = punktyMax;
        this.punktyMin = punktyMin;
    }
}
