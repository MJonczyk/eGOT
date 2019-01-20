package com.jestgit.egot.pozycjawycieczki;

import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.wycieczka.Wycieczka;

import java.io.Serializable;

/**
 * A class used to model composite key of PozycjaWycieczki
 * @author Michał Jończyk
 * @version 1.2
 */
public class PozycjaWycieczkiKey implements Serializable {
    private Wycieczka numerWycieczki;
    private Trasa numerTrasy;
}
