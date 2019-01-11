package com.jestgit.egot.wycieczka;

import com.jestgit.egot.decyzja.Decyzja;
import com.jestgit.egot.ksiazeczka.Ksiazeczka;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Wycieczka")
public class Wycieczka {

    @Column(name = "NUMERWYCIECZKI")
    @Id
    @GeneratedValue
    private Long numerWycieczki;

    @ManyToOne
    @JoinColumn(name = "KsiazeczkanumerKsiazeczki")
    private Ksiazeczka numerKsiazeczki;

    @Column(name = "PUNKTY")
    private Float punkty;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATAODBYCIA")
    private Date dataOdbycia;

    @Column(name = "OPIS")
    private String opis;

    @Column(name = "DLUGOSC")
    private Float dlugosc;

    @Column(name = "DATAZAKONCZENIA")
    private Date dataZakonczenia;

    @Column(name = "OPIEKUN")
    private String opiekun;

    @OneToMany(mappedBy = "numerWycieczki")
    private Set<PozycjaWycieczki> pozycje;

    @Nullable
    @OneToOne(mappedBy = "numerWycieczki")
    private Decyzja decyzja;

    public Wycieczka(){}

    public Wycieczka(Ksiazeczka ksiazeczka, Float punkty, Date dataOdbycia, Float dlugosc, Date dataZakonczenia, String opiekun){
        this.numerKsiazeczki = ksiazeczka;
        this.punkty = punkty;
        this.dataOdbycia = dataOdbycia;
        this.dlugosc = dlugosc;
        this.dataZakonczenia = dataZakonczenia;
        this.opiekun = opiekun;
    }

}
