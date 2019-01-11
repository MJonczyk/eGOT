package com.jestgit.egot.ksiazeczka;

import com.jestgit.egot.turysta.Turysta;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name ="Ksiazeczka")
public class Ksiazeczka {

    @Column(name = "NUMERKSIAZECZKI")
    @Id
    @GeneratedValue
    private Long numerKsiazeczki;

    @OneToOne
    @JoinColumn(name = "TurystaidTurysty")
    private Turysta idTurysty;

    @Column(name = "ZDOBYTEPUNKTY")
    private Float zdobytePunkty;

    @Temporal(TemporalType.DATE)
    @Column(name = "STARTCYKLU")
    private Date startCyklu;

    public Ksiazeczka(){}

    public Ksiazeczka(Turysta turysta, Float punkty, Date startCyklu){
        this.idTurysty = turysta;
        this.zdobytePunkty = punkty;
        this.startCyklu = startCyklu;
    }
}
