package com.jestgit.egot.grupa;

import com.jestgit.egot.region.Region;
import com.jestgit.egot.trasa.Trasa;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "GrupaGorska")
public class Grupa {

    @Column(name ="NAZWAGRUPY")
    @Id
    private String nazwaGrupy;

    @ManyToOne()
    @JoinColumn(name = "nazwaRegionu")
    private Region nazwaRegionu;

    @Column(name = "SYMBOLGRUPY")
    private String symbolGrupy;

    @OneToMany(mappedBy = "GrupaGorskanazwaGrupy")
    private Set<Trasa> trasy;

    public Grupa(){}

    public Grupa(String nazwa, Region nazwaRegionu, String symbol){
        this.nazwaGrupy = nazwa;
        this.nazwaRegionu = nazwaRegionu;
        this.symbolGrupy = symbol;
    }

}
