package com.jestgit.egot.punkt;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Punkt")
public class Punkt {
    @Column(name = "IDPUNKTU")
    @Id
    @GeneratedValue
    private Long idPunktu;

    @Column(name = "NAZWAPUNKTU")
    private String nazwaPunktu;

    @Column(name = "DLUGOSCGEOGRAFICZNA")
    private Float dlugoscGeograficzna;

    @Column(name = "SZEROKOSCGEOGRAFICZNA")
    private Float szerokoscGeograficzna;

    @Column(name = "WYSOKOSCNPM")
    private Float wysokoscNPM;

    public Punkt(){}

    public Punkt(String nazwaPunktu, Float dlugoscGeograficzna, Float szerokoscGeograficzna, Float wysokoscNPM) {
        this.nazwaPunktu = nazwaPunktu;
        this.dlugoscGeograficzna = dlugoscGeograficzna;
        this.szerokoscGeograficzna = szerokoscGeograficzna;
        this.wysokoscNPM = wysokoscNPM;
    }
}
