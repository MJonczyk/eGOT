package com.jestgit.egot.pozycjawycieczki;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface PozycjaWycieczkiRepository extends JpaRepository<PozycjaWycieczki, Long> {

    @Query(value = "SELECT pw FROM PozycjaWycieczki pw WHERE pw.numerWycieczki.numerWycieczki = ?1")
    ArrayList<PozycjaWycieczki> findAllByNumerWycieczki(Long numerWycieczki);
}
