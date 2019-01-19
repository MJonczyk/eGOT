package com.jestgit.egot.trasa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrasaRepository extends JpaRepository<Trasa, Long> {
    @Query(value = "SELECT t FROM Trasa t INNER JOIN t.GrupaGorskanazwaGrupy gr INNER JOIN gr.nazwaRegionu r " +
                   "INNER JOIN t.punktPoczatkowy pp INNER JOIN t.punktKoncowy pk " +
                   "WHERE (gr.nazwaGrupy = :grupa AND :grupa IS NOT NULL OR :grupa IS NULL) AND (r.nazwaRegionu = :region AND :region IS NOT NULL " +
                   "OR :region IS NULL) AND (pp.nazwaPunktu = :poczatek OR :poczatek = '') " +
                   "AND (pk.nazwaPunktu = :koniec OR :koniec = '')")
    List<Trasa> search(@Param("grupa")String grupa, @Param("region")String region,
                       @Param("poczatek")String punktPoczatkowy, @Param("koniec")String punktKoncowy);

    @Query(value = "SELECT t FROM Trasa t INNER JOIN t.GrupaGorskanazwaGrupy gr INNER JOIN gr.nazwaRegionu r " +
                   "INNER JOIN t.punktPoczatkowy pp INNER JOIN t.punktKoncowy pk " +
                   "WHERE gr.nazwaGrupy LIKE %:phrase% OR r.nazwaRegionu LIKE %:phrase% " +
                   "OR pp.nazwaPunktu LIKE %:phrase% OR pk.nazwaPunktu LIKE %:phrase%")
    List<Trasa> searchPhrase(@Param("phrase")String phrase);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Trasa t WHERE t.numerTrasy = ?1")
    void deleteTrasaByNumerTrasy(@Param("numerTrasy") Long numerTrasy);
}
