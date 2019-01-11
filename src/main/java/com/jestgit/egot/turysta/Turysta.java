package com.jestgit.egot.turysta;

import com.jestgit.egot.ksiazeczka.Ksiazeczka;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Turysta")
public class Turysta {

    @Column(name = "IDTURYSTY")
    @Id
    @GeneratedValue
    private Long idTurysty;

    @Column(name = "EMAIL")
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATAURODZENIA")
    private Date dataUrodzenia;

    @Column(name = "IMIE")
    private String imie;

    @Column(name = "NAZWISKO")
    private String nazwisko;

    @Column(name = "TELEFON")
    private String telefon;

    @Column(name = "PLEC")
    private String plec;

    @Nullable
    @OneToOne(mappedBy = "idTurysty")
    private Ksiazeczka ksiazeczka;

    public Turysta(){}

    public Turysta(String email, Date dataUrodzenia, String imie, String nazwisko, String telefon, String plec){
        this.email = email;
        this.dataUrodzenia = dataUrodzenia;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.plec = plec;
    }
}
