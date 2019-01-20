package com.jestgit.egot.wycieczka;

import lombok.Data;

import java.util.Date;

/**
 * A class used to transfer data about Wycieczka between View layer and Controller layer
 * @author Michał Jończyk
 * @author Mateusz Wójcik
 * @version 1.2
 */
@Data
public class WycieczkaDTO {
    private Long numerWycieczki;

    private Long numerKsiazeczki;

    private Date dataOdbycia;

    private String opis;

    private Date dataZakonczenia;

    private String opiekun;

    //private Set<PozycjaWycieczki> pozycje;

    private Long numerDecyzji;

    /**
     * Default constructor used to pass WycieczkaDTO object to the View
     */
    public WycieczkaDTO(){}

    /**
     * Parameterized constructor used to create WycieczkaDTO and initialize all of its fields
     * @param numerWycieczki Id of this Wycieczka
     * @param numerKsiazeczki Ksiazeczka of Turysta who participated in this Wycieczka
     * @param dataOdbycia date of the start of this Wycieczka
     * @param opis description of this Wycieczka
     * @param dataZakonczenia date of the end of this Wycieczka
     * @param opiekun Przodownik who participated in this Wycieczka
     * @param numerDecyzji Id of the Decyzja this Wycieczka relates to
     */
    public WycieczkaDTO(Long numerWycieczki, Long numerKsiazeczki, Date dataOdbycia, String opis, Date dataZakonczenia, String opiekun, Long numerDecyzji){
        this.numerWycieczki = numerWycieczki;
        this.numerKsiazeczki = numerKsiazeczki;
        this.dataOdbycia = dataOdbycia;
        this.opis = opis;
        this.dataZakonczenia = dataZakonczenia;
        this.opiekun = opiekun;
        this.numerDecyzji = numerDecyzji;
    }
}
