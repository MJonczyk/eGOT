package com.jestgit.egot.wycieczka;

import com.jestgit.egot.decyzja.Decyzja;
import com.jestgit.egot.ksiazeczka.Ksiazeczka;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * A class used to model Wycieczka data from the database
 * @author Michał Jończyk
 * @author Mateusz Wójcik
 * @version 1.2
 */
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

    /**
     * Default constructor
     */
    public Wycieczka(){}

    /**
     * Parameterized constructor used to create Wycieczka and initialize all of its fields
     * @param ksiazeczka Ksiazeczka of Turysta who participated in this Wycieczka
     * @param dataRozpoczecia date of the start of this Wycieczka
     * @param dataZakonczenia date of the end of this Wycieczka
     * @param opiekun Przodownik who participated in this Wycieczka
     */
    public Wycieczka(Ksiazeczka ksiazeczka, Date dataRozpoczecia, Date dataZakonczenia, String opiekun){
        this.numerKsiazeczki = ksiazeczka;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.opiekun = opiekun;
    }

}
