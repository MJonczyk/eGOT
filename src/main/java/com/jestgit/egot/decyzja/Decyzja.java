package com.jestgit.egot.decyzja;

import com.jestgit.egot.przodownik.Przodownik;
import com.jestgit.egot.wycieczka.Wycieczka;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * A class used to model Decyzja data from the database
 * @author Michał Jończyk
 * @version 1.2
 */
@Data
@Entity
@Table(name = "Decyzja")
public class Decyzja {

    @Column(name = "NUMERDECYZJI")
    @Id
    @GeneratedValue
    private Long numerDecyzji;

    @OneToOne
    @JoinColumn(name = "WycieczkanumerWycieczki")
    private Wycieczka numerWycieczki;

    @ManyToOne
    @JoinColumn(name = "PrzodowniknumerLegitymacji")
    private Przodownik PrzodowniknumerLegitymacji;

    @Column(name = "CZYZATWIERDZONA")
    private String czyZatwierdzona;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA")
    private Date data;

    @Column(name = "UZASADNIENIE")
    private String uzasadnienie;

    /**
     * Default constructor
     */
    public Decyzja(){}

    /**
     * Parameterized constructor used to create Decyzja and initialize all of its fields
     * @param wycieczka wycieczka this decyzja belongs to
     * @param numerLegitymacji Id of the Przodownik who accepted this Decyzja
     * @param czyZatwierdzona result of made decision
     * @param data date of the Decyzja acceptation
     * @param uzasadnienie description of the Decyzja
     */
    public Decyzja(Wycieczka wycieczka, Przodownik numerLegitymacji, String czyZatwierdzona, Date data, String uzasadnienie){
        this.numerWycieczki = wycieczka;
        this.PrzodowniknumerLegitymacji = numerLegitymacji;
        this.czyZatwierdzona = czyZatwierdzona;
        this.data = data;
        this.uzasadnienie = uzasadnienie;
    }

}
