package com.jestgit.egot.przodownik;

import com.jestgit.egot.decyzja.Decyzja;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Przodownik")
public class Przodownik {

    @Column(name = "NUMERLEGITYMACJI")
    @Id
    private String numerLegitymacji;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMIE")
    private String imie;

    @Column(name = "NAZWISKO")
    private String nazwisko;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATAURODZENIA")
    private Date dataUrodzenia;

    @Column(name = "PLEC")
    private String plec;

    @Column(name = "TELEFON")
    private String telefon;

    @OneToMany(mappedBy = "PrzodowniknumerLegitymacji")
    private Set<Decyzja> decyzje;

    public Przodownik(){}
}
