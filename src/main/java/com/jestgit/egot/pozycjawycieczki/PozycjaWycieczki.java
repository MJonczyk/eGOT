package com.jestgit.egot.pozycjawycieczki;

import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.wycieczka.Wycieczka;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    public PozycjaWycieczki(){}

    public PozycjaWycieczki(Date dataRozpoczecia, Date dataZakonczenia, String kierunek){}
}
