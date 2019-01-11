package com.jestgit.egot.pozycjawycieczki;

import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.wycieczka.Wycieczka;

import java.io.Serializable;

public class PozycjaWycieczkiKey implements Serializable {
    private Wycieczka numerWycieczki;
    private Trasa numerTrasy;
}
