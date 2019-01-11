package com.jestgit.egot.Region;

import com.jestgit.egot.grupa.Grupa;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Region")
public class Region {

    @Column(name = "NAZWAREGIONU")
    @Id
    private String nazwaRegionu;

    @Column(name = "INFORMACJEDODATKOWE")
    private  String informacje;

    @OneToMany(mappedBy = "nazwaRegionu")
    private Set<Grupa> grupy;

    public Region(){}

    public Region(String nazwa, String informacje){
        nazwaRegionu = nazwa;
        this.informacje = informacje;
    }
}
