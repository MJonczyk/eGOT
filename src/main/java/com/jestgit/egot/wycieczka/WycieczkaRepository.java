package com.jestgit.egot.wycieczka;

import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface WycieczkaRepository extends JpaRepository<Wycieczka, Long> {

    @Query(value = "SELECT pw FROM PozycjaWycieczki pw WHERE pw.numerWycieczki.numerWycieczki = ?1")
    ArrayList<PozycjaWycieczki> findAllByNumerWycieczki(Long numerWycieczki);

}
