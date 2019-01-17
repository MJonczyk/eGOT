package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import com.jestgit.egot.punkt.Punkt;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Entity
@Table(name = "Trasa")
public class Trasa {

    @Column(name = "NUMERTRASY")
    @Id
    @GeneratedValue
    private Long numerTrasy;

    @ManyToOne
    @JoinColumn(name = "GrupaGorskanazwaGrupy")
    private Grupa GrupaGorskanazwaGrupy;

    @ManyToOne
    @JoinColumn(name = "PUNKTPOCZATKOWY")
    private Punkt punktPoczatkowy;

    @ManyToOne
    @JoinColumn(name = "PUNKTKONCOWY")
    private Punkt punktKoncowy;

    @Column(name = "PUNKTYZATRASE")
    private String punktyZaTrase;

    @Column(name = "OPIS")
    private String opis;

    @Column(name = "RODZAJTRASY")
    private String rodzajTrasy;

    @Column(name = "TURYSTAIDTURYSTY")
    private Long TurystaidTurysty;

    @Column(name = "PRZEWODNIKNUMERLEGITYMACJI")
    private String PrzewodniknumerLegitymacji;

    @OneToMany(mappedBy = "numerTrasy")
    private Set<PozycjaWycieczki> pozycje;

    public Trasa(){}

    public Trasa(Grupa grupa, Punkt punktPoczatkowy, Punkt punktKoncowy, String punktyZaTrase, String opis, String rodzajTrasy, Object id, int flaga) {
        this.GrupaGorskanazwaGrupy = grupa;
        this.punktPoczatkowy = punktPoczatkowy;
        this.punktKoncowy = punktKoncowy;
        this.punktyZaTrase = punktyZaTrase;
        this.opis = opis;
        this.rodzajTrasy = rodzajTrasy;
        if(flaga == 0)
            this.TurystaidTurysty = (Long)id;
        else
            this.PrzewodniknumerLegitymacji = (String)id;
    }
}
