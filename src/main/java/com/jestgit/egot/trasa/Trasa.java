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
    private Long PrzewodniknumerLegitymacji;

    Trasa(String grupa, String punktPoczatkowy, String punktKoncowy, String punktyZaTrase, String opis, String rodzajTrasy, Long id, int flaga) {
        this.GrupaGorskanazwaGrupy = grupa;
        this.punktPoczatkowy = punktPoczatkowy;
        this.punktKoncowy = punktKoncowy;
        this.punktyZaTrase = punktyZaTrase;
        this.opis = opis;
        this.rodzajTrasy = rodzajTrasy;
        if(flaga == 0)
            this.TurystaidTurysty = id;
        else
            this.PrzewodniknumerLegitymacji = id;
    }

    public Long getNumerTrasy() {
        return numerTrasy;
    }

    public String getGrupaGorskanazwaGrupy() {
        return GrupaGorskanazwaGrupy;
    }

    public String getPunktPoczatkowy() {
        return punktPoczatkowy;
    }

    public String getPunktKoncowy() {
        return punktKoncowy;
    }

    public String getPunktyZaTrase() {
        return punktyZaTrase;
    }

    public String getOpis() {
        return opis;
    }

    public String getRodzajTrasy() {
        return rodzajTrasy;
    }

    public Long getTurystaidTurysty() {
        return TurystaidTurysty;
    }

    public Long getPrzewodniknumerLegitymacji() {
        return PrzewodniknumerLegitymacji;
    }
}
