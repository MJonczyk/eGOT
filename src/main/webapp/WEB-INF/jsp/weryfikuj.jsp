<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.jestgit.egot.wycieczka.Wycieczka" %>
<%@ page import="com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jestgit.egot.wycieczka.WycieczkaDTO" %>
<%@ page import="com.jestgit.egot.WeryfikujDTO" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../css/base.css">
    <link rel="stylesheet" type="text/css" href="../css/weryfikuj.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../js/weryfikuj.js"></script>
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">WYLOGUJ</a>
                <a href="http://localhost:8080/index">ANULUJ</a>
                <a href="#" onclick="submitWeryfikuj(<%= ((WycieczkaDTO) request.getAttribute("wycieczkaDto")).getNumerWycieczki() %>);return false;">DALEJ</a>
            </div>
        </div>

        <div id="underTopBar">
            <div id="menu">
                <div>
                    <h3 id="menuTitle">MENU</h3>
                </div>
                <div><a href="http://localhost:8080/dodaj">Dodawanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Modyfikacja tras</a></div>
                <div><a href="http://localhost:8080/usun">Usuwanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Przeglądanie tras</a></div>
                <div><a href="http://localhost:8080/wyszukaj">Wyszukiwanie tras</a></div>
                <div><a href="http://localhost:8080/wycieczki">Weryfikacja wycieczek</a></div>
            </div>

            <% String walidacja = (String) request.getAttribute("walidacja");
               String propozycja = (walidacja.equals("ok") || walidacja.equals("opiekun obecny na wycieczce")) ?
                                    "akceptuj" : "odrzuć";
            %>

            <div id="central">
                    <div id="titleTDiv">
                        <div id="titleTitleDiv">
                            <h2 id="formTitle">Weryfikacja wycieczki</h2>
                        </div>

                        <div id="checkboxDiv">
                            <form:form id="weryfikujForm" method="post" modelAttribute="weryfikujDTO">
                                <form:checkbox path="isAccepted" name="isAccepted" cssClass="checkboxClass"></form:checkbox>
                                <form:label path="isAccepted" id="isAcceptedLabel" cssClass="redLabel">Odrzucona</form:label>
                            </form:form>
                            <h4 id="propozycja">Propozycja:<%= " " + propozycja + (walidacja.equals("ok") ? "" : " - " + walidacja) %></h4>
                        </div>
                    </div>
                    <%
                        WycieczkaDTO wycieczka = (WycieczkaDTO) request.getAttribute("wycieczkaDto");
                        ArrayList<PozycjaWycieczki> pozycjeWycieczki = (ArrayList<PozycjaWycieczki>) request.getAttribute("pozycjeWycieczki");
                        Float punktyZaWycieczke = (Float) request.getAttribute("punktyZaWycieczke");
                        Float dlugoscWycieczki = (Float) request.getAttribute("dlugoscWycieczki");
                        Float[] dlugosci = (Float[]) request.getAttribute("dlugosci");
                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        DecimalFormat df = new DecimalFormat("#.##");
                        df.setRoundingMode(RoundingMode.HALF_UP);
                    %>
                <div id="wycieczkaIPozycjeDiv">
                    <div id="wycieczkaTable">
                        <table>
                            <tr>
                                <td class="boldC">Wycieczka nr</td>
                                <td><%= wycieczka.getNumerWycieczki()%></td>
                            </tr>
                            <tr>
                                <td class="boldC">Data rozpoczęcia</td>
                                <td><%= formatter.format(wycieczka.getDataOdbycia()) %></td>
                            </tr>
                            <tr>
                                <td class="boldC">Data zakończenia</td>
                                <td><%= formatter.format(wycieczka.getDataZakonczenia()) %></td>
                            </tr>
                            <tr>
                                <td class="boldC">Punkty</td>
                                <td><%= punktyZaWycieczke %></td>
                            </tr>
                            <tr>
                                <td class="boldC">Długość</td>
                                <td><%= df.format(dlugoscWycieczki) + " [km]"%></td>
                            </tr>
                            <tr>
                                <td class="boldC">Opiekun</td>
                                <td><%= wycieczka.getOpiekun() == null ? "-" : wycieczka.getOpiekun() %></td>
                            </tr>
                        </table>
                    </div>

                    <div id="pozycjeWycieczkiDiv">
                        <h3>Przebyte trasy</h3>
                        <table id="pozycjeWycieczkiTable">
                            <tr>
                                <th>Lp.</th>
                                <th>Start</th>
                                <th>Koniec</th>
                                <th>Data rozp.</th>
                                <th>Data zak.</th>
                                <th>Punkty</th>
                                <th>Długość</th>
                                <th>Różnica wys.</th>
                            </tr>
                            <%
                                int counter = 0;
                                for(PozycjaWycieczki pozycjaWycieczki : pozycjeWycieczki){
                                    counter++;
                            %>
                            <tr>
                                <td> <%= counter + "." %> </td>
                                <td> <%= (pozycjaWycieczki.getKierunek().equals("G")
                                        ? pozycjaWycieczki.getNumerTrasy().getPunktPoczatkowy().getNazwaPunktu()
                                        : pozycjaWycieczki.getNumerTrasy().getPunktKoncowy().getNazwaPunktu() ) %>
                                </td>
                                <td> <%= (pozycjaWycieczki.getKierunek().equals("D")
                                        ? pozycjaWycieczki.getNumerTrasy().getPunktPoczatkowy().getNazwaPunktu()
                                        : pozycjaWycieczki.getNumerTrasy().getPunktKoncowy().getNazwaPunktu() ) %>
                                </td>
                                <td>
                                    <%= formatter.format(pozycjaWycieczki.getDataRozpoczecia()) %>
                                </td>
                                <td>
                                    <%= formatter.format(pozycjaWycieczki.getDataZakonczenia()) %>
                                </td>
                                <td>
                                    <%= (pozycjaWycieczki.getKierunek().equals("G") ? pozycjaWycieczki.getNumerTrasy().getPunktyZaTrase().split("/")[0] :
                                            pozycjaWycieczki.getNumerTrasy().getPunktyZaTrase().split("/")[1]) + " pkt " %>
                                </td>
                                <td> <%= df.format(dlugosci[counter - 1]) + " km"%> </td>
                                <td> <%= Math.round(pozycjaWycieczki.getNumerTrasy().getPunktKoncowy().getWysokoscNPM() - pozycjaWycieczki.getNumerTrasy().getPunktPoczatkowy().getWysokoscNPM()) + " m.n.p.m." %> </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>

                    </div>

                </div>


                    <div id="opisDiv">
                        <label for="opisTextArea" class="boldC">Opis</label><br>
                        <textarea contenteditable="false" name="opis" id="opisTextArea" cols="60" rows="5" disabled="true"><%= wycieczka.getOpis()%></textarea>
                    </div>

            </div>
        </div>

    </div>

</body>

</html>