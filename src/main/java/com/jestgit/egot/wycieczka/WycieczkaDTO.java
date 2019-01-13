package com.jestgit.egot.wycieczka;

import lombok.Data;

import java.util.Date;

@Data
public class WycieczkaDTO {
    private Long numerWycieczki;

    private Long numerKsiazeczki;

    private Float punkty;

    private Date dataOdbycia;

    private String opis;

    private Float dlugosc;

    private Date dataZakonczenia;

    private String opiekun;

    //private Set<PozycjaWycieczki> pozycje;

    private Long numerDecyzji;

    public WycieczkaDTO(){}

    public WycieczkaDTO(Long numerWycieczki, Long numerKsiazeczki, Float punkty, Date dataOdbycia, String opis, Float dlugosc, Date dataZakonczenia, String opiekun, Long numerDecyzji){
        this.numerWycieczki = numerWycieczki;
        this.numerKsiazeczki = numerKsiazeczki;
        this.punkty = punkty;
        this.dataOdbycia = dataOdbycia;
        this.opis = opis;
        this.dlugosc = dlugosc;
        this.dataZakonczenia = dataZakonczenia;
        this.opiekun = opiekun;
        this.numerDecyzji = numerDecyzji;
    }
}
