package com.jestgit.egot.trasa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface that provides CRUD and other basic operations on Trasa data
 * @author Michał Jończyk
 * @author Matuesz Wójcik
 * @version 1.2
 */
public interface TrasaRepository extends JpaRepository<Trasa, Long> {
    /**
     * Method that returns search results
     * @param grupa name of the Grupa searched Trasa belongs to
     * @param region name of the Region searched Trasa belongs to
     * @param punktPoczatkowy name of the starting Punkt searched Trasa starts with
     * @param punktKoncowy name of the end Punkt searched Trasa ends with
     * @param phrase phrase that is going be used to check if the Trasa data contains it
     * @return list of Trasas that meet given requirements
     */
    @Query(value = "SELECT t FROM Trasa t INNER JOIN t.GrupaGorskanazwaGrupy gr INNER JOIN gr.nazwaRegionu r " +
                   "INNER JOIN t.punktPoczatkowy pp INNER JOIN t.punktKoncowy pk " +
                   "WHERE ((gr.nazwaGrupy = :grupa AND :grupa IS NOT NULL OR :grupa IS NULL) AND (r.nazwaRegionu = :region AND :region IS NOT NULL " +
                   "OR :region IS NULL) AND (pp.nazwaPunktu = :poczatek OR :poczatek = '') " +
                   "AND (pk.nazwaPunktu = :koniec OR :koniec = '')) AND (gr.nazwaGrupy LIKE %:phrase% OR r.nazwaRegionu LIKE %:phrase% " +
                   "OR pp.nazwaPunktu LIKE %:phrase% OR pk.nazwaPunktu LIKE %:phrase%)")
    List<Trasa> search(@Param("grupa")String grupa, @Param("region")String region,
                       @Param("poczatek")String punktPoczatkowy, @Param("koniec")String punktKoncowy, @Param("phrase")String phrase);

    /**
     * Method that deletes Trasa from the database
     * @param numerTrasy Id of given Trasa
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Trasa t WHERE t.numerTrasy = ?1")
    void deleteTrasaByNumerTrasy(@Param("numerTrasy") Long numerTrasy);

    @Query(value = "SELECT t FROM Trasa t WHERE t.TurystaidTurysty IS NOT NULL")
    List<Trasa> getTrasaByTurystaidTurystyNotNull();

    @Query(value = "SELECT t FROM Trasa t WHERE t.TurystaidTurysty IS NOT NULL AND t.TurystaidTurysty = 1")
    List<Trasa> getTrasaByTurystaidTurysty1();
}
