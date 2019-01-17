package com.jestgit.egot.wycieczka;

import lombok.Data;

import java.util.Date;

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

    public WycieczkaDTO(){}

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
