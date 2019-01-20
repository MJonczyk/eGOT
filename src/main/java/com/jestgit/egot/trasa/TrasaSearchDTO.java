package com.jestgit.egot.trasa;

import lombok.Data;

/**
 *A class used to transfer search parameters
 * @author Michał Jończyk
 * @version 1.2
 */
@Data
public class TrasaSearchDTO {

    private String searchPhrase;

    private String nazwaRegionu;

    private String nazwaGrupy;

    private String punktPoczatkowy;

    private String punktKoncowy;

    private Integer punktyMax;

    private Integer punktyMin;

    /**
     * Default constructor used to pass TrasaSearchDTO object to the View
     */
    public TrasaSearchDTO(){}

    /**
     * Parameterized constructor used to create TrasaSearchDTO and initialize all of its fields
     * @param searchPhrase phrase that is going be used to check if the Trasa data contains it
     * @param nazwaRegionu name of the Region searched Trasa belongs to
     * @param nazwaGrupy name of the Grupa searched Trasa belongs to
     * @param punktPoczatkowy name of the starting Punkt searched Trasa starts with
     * @param punktKoncowy name of the end Punkt searched Trasa ends with
     * @param punktyMax maximum number of points searched Trasa might have
     * @param punktyMin minimum number of points searched Trasa might have
     */
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
