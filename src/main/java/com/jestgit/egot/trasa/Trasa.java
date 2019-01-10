package com.jestgit.egot.trasa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Trasa {

    private @Id @GeneratedValue Long numerTrasy;
    private String GrupaGorskanazwaGrupy;
    private String punktPoczatkowy;
    private String punktKoncowy;
    private String punktyZaTrase;
    private String opis;
    private String rodzajTrasy;
    private Long TurystaidTurysty;
    private String PrzewodniknumerLegitymacji;

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
