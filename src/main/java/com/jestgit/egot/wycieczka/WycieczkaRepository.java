package com.jestgit.egot.wycieczka;

import com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface WycieczkaRepository extends JpaRepository<Wycieczka, Long> {

    @Query(value = "SELECT pw FROM PozycjaWycieczki pw WHERE pw.numerWycieczki.numerWycieczki = ?1")
    ArrayList<PozycjaWycieczki> findAllByNumerWycieczki(Long numerWycieczki);

    @Query(value = "SELECT w FROM Wycieczka w WHERE w.numerWycieczki NOT IN (SELECT w.numerWycieczki FROM Wycieczka w JOIN Decyzja d  ON w.numerWycieczki = d.numerWycieczki)")
    ArrayList<Wycieczka> getAllByNiezweryfikowane();

    @Query(value = "SELECT w FROM Wycieczka w JOIN Decyzja d ON w.numerWycieczki = d.numerWycieczki")
    ArrayList<Wycieczka> getAllByZweryfikowane();
}
