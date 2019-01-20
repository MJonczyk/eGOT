package com.jestgit.egot.trasa;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import com.jestgit.egot.punkt.Punkt;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static java.lang.Math.toIntExact;

/**
 * A class used to model Trasa data from the database
 * @author Michał Jończyk
 * @version 1.2
 */
@Data
@Entity
@Table(name = "Trasa")
public class Trasa {

    /**
     * The Id of a Trasa
     */
    @Column(name = "NUMERTRASY")
    @Id
    @GeneratedValue
    private Long numerTrasy;

    /**
     * Grupa a Trasa belongs to
     */
    @ManyToOne
    @JoinColumn(name = "GrupaGorskanazwaGrupy")
    private Grupa GrupaGorskanazwaGrupy;

    /**
     * Starting Punkt of a Trasa
     */
    @ManyToOne
    @JoinColumn(name = "PUNKTPOCZATKOWY")
    private Punkt punktPoczatkowy;

    /**
     * End Punkt of a Trasa
     */
    @ManyToOne
    @JoinColumn(name = "PUNKTKONCOWY")
    private Punkt punktKoncowy;

    /**
     * Number of points Turysta gets for completing a Trasa
     */
    @Column(name = "PUNKTYZATRASE")
    private String punktyZaTrase;

    /**
     * Description of a Trasa
     */
    @Column(name = "OPIS")
    private String opis;

    /**
     * Type of a Trasa, S - created by Przewodnik, W - created by Turysta
     */
    @Column(name = "RODZAJTRASY")
    private String rodzajTrasy;

    /**
     * Id of the Turysta that created this Trasa, null if Przewdonik created this Trasa
     */
    @Column(name = "TURYSTAIDTURYSTY")
    private Long TurystaidTurysty;

    /**
     * Id of the Przewodnik that created this Trasa, null if Turysta created this Trasa
     */
    @Column(name = "PRZEWODNIKNUMERLEGITYMACJI")
    private String PrzewodniknumerLegitymacji;

    /**
     * Set of Wycieczkas this Trasa belongs to
     */
    @OneToMany(mappedBy = "numerTrasy")
    private Set<PozycjaWycieczki> pozycje;

    /**
     * Default constructor
     */
    public Trasa(){}

    /**
     * Parameterized constructor used to create Trasa and initialize all of its fields
     * @param grupa Grupa this Trasa belongs to
     * @param punktPoczatkowy starting Punkt of this Trasa
     * @param punktKoncowy end Punkt of this Trasa
     * @param punktyZaTrase number of points you get for completing Trasa
     * @param opis description of this Trasa
     * @param rodzajTrasy type of this Trasa
     * @param id Id of Przewodnik or Turysta who created this Trasa
     * @param flaga signals whether this Trasa was created by Turysta or Przewodnik
     */
    public Trasa(Grupa grupa, Punkt punktPoczatkowy, Punkt punktKoncowy, String punktyZaTrase, String opis, String rodzajTrasy, Object id, int flaga) {
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

    /**
     * Method that compares other object to this Trasa and returns if the objects are equal
     * @param other object used to compare to this Trasa
     * @return true if compared objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Trasa))
            return false;
        Trasa oth = (Trasa) other;
        return this.numerTrasy.equals(oth.numerTrasy);
    }

    /**
     * Method that computes hashCode of this Trasa
     * @return unique number for this Trasa
     */
    @Override
    public int hashCode(){
        int hash = 17;
        hash = hash * 7 + toIntExact(numerTrasy);
        hash = hash * 7 + punktKoncowy.hashCode();
        hash = hash * 7 + punktPoczatkowy.hashCode();
        return hash;
    }
}
