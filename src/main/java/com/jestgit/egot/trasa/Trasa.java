package com.jestgit.egot.trasa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Trasa")
public class Trasa {

    @Column(name = "NUMERTRASY")
    @Id
    @GeneratedValue
    private Long numerTrasy;

    @Column(name = "GRUPAGORSKANAZWAGRUPY")
    private String GrupaGorskanazwaGrupy;

    @Column(name = "PUNKTPOCZATKOWY")
    private String punktPoczatkowy;

    @Column(name = "PUNKTKONCOWY")
    private String punktKoncowy;

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

    Trasa(){}

    Trasa(String grupa, String punktPoczatkowy, String punktKoncowy, String punktyZaTrase, String opis, String rodzajTrasy, Object id, int flaga) {
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
