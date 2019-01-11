package com.jestgit.egot.decyzja;

import com.jestgit.egot.przodownik.Przodownik;
import com.jestgit.egot.wycieczka.Wycieczka;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    public Decyzja(){}

    public Decyzja(Wycieczka wycieczka, Przodownik numerLegitymacji, String czyZatwierdzona, Date data, String uzasadnienie){
        this.numerWycieczki = wycieczka;
        this.PrzodowniknumerLegitymacji = numerLegitymacji;
        this.czyZatwierdzona = czyZatwierdzona;
        this.data = data;
        this.uzasadnienie = uzasadnienie;
    }

}
