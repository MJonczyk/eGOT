package com.jestgit.egot.pozycjawycieczki;

import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.wycieczka.Wycieczka;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * A class used to model PozycjaWycieczki data from the database
 * @author Michał Jończyk
 * @version 1.2
 */
@Data
@Entity
@Table(name = "PozycjaWycieczki")
@IdClass(PozycjaWycieczkiKey.class)
public class PozycjaWycieczki {

    @Id
    @ManyToOne
    @JoinColumn(name = "WycieczkanumerWycieczki")
    private Wycieczka numerWycieczki;

    @Id
    @ManyToOne
    @JoinColumn(name = "TrasanumerTrasy")
    private Trasa numerTrasy;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATAROZPOCZECIA")
    private Date dataRozpoczecia;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATAZAKONCZENIA")
    private Date dataZakonczenia;

    @Column(name = "KIERUNEK")
    private String kierunek;

    /**
     * Default constructor
     */
    public PozycjaWycieczki(){}

    /**
     * Parameterized constructor used to create PozycjaWycieczki and initialize all of its fields
     * @param dataRozpoczecia date of the start of the Wycieczka
     * @param dataZakonczenia date of the end of the Wycieczka
     * @param kierunek direction of the Wycieczka
     */
    public PozycjaWycieczki(Date dataRozpoczecia, Date dataZakonczenia, String kierunek){}
}
