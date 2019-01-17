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

    @Temporal(TemporalType.DATE)
    @Column(name = "DATAROZPOCZECIA")
    private Date dataRozpoczecia;

    @Column(name = "OPIS")
    private String opis;

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

    public Wycieczka(Ksiazeczka ksiazeczka, Date dataRozpoczecia, Date dataZakonczenia, String opiekun){
        this.numerKsiazeczki = ksiazeczka;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.opiekun = opiekun;
    }

}
