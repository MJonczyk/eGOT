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
    <script src="../js/wyswietl.js"></script>
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">WYLOGUJ</a>
                <a href="#">ANULUJ</a>
                <a href="javascript:{}" onclick="document.getElementById('weryfikujForm').submit();
                                        cos = document.getElementById('isAccepted').checked;
                                        window.location.replace('http://localhost:8080/weryfikuj/<%= ((WycieczkaDTO) request.getAttribute("wycieczkaDto")).getNumerWycieczki() %>/' + cos);">ZAPISZ</a>
            </div>
        </div>

        <div id="underTopBar">
            <div id="menu">
                <div>
                    <h3 id="menuTitle">MENU</h3>
                </div>
                <div><a href="http://localhost:8080/dodaj">Dodawanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Modyfikacja tras</a></div>
                <div><a href="#">Usuwanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Przeglądanie tras</a></div>
                <div><a href="http://localhost:8080/wyszukaj">Wyszukiwanie tras</a></div>
                <div><a href="http://localhost:8080/wycieczki">Weryfikacja wycieczek</a></div>
            </div>

            <div id="central">
                    <div id="titleTDiv">
                        <div id="titleTitleDiv">
                            <h2 id="formTitle">Weryfikacja wycieczki</h2>
                        </div>

                        <div id="checkboxDiv">
                            <form:form id="weryfikujForm" method="post" modelAttribute="weryfikujDTO">
                                <form:checkbox path="isAccepted" id="isAccepted" name="isAccepted" ></form:checkbox>
                                <form:label path="isAccepted" id="isAcceptedCLabel">Akceptuj</form:label>
                            </form:form>
                        </div>
                    </div>
                    <%
                        WycieczkaDTO wycieczka = (WycieczkaDTO) request.getAttribute("wycieczkaDto");
                        ArrayList<PozycjaWycieczki> pozycjeWycieczki = (ArrayList<PozycjaWycieczki>) request.getAttribute("pozycjeWycieczki");
                        Float punktyZaWycieczke = (Float) request.getAttribute("punktyZaWycieczke");
                        Float dlugoscWycieczki = (Float) request.getAttribute("dlugoscWycieczki");
                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        DecimalFormat df = new DecimalFormat("#.##");
                        df.setRoundingMode(RoundingMode.HALF_UP);
                    %>
                <div id="wycieczkaIPozycjeDiv">
                    <div id="wycieczkaTable">
                        <table>
                            <tr>
                                <td>Wycieczka nr</td>
                                <td><%= wycieczka.getNumerWycieczki()%></td>
                            </tr>
                            <tr>
                                <td>Data rozpoczęcia</td>
                                <td><%= formatter.format(wycieczka.getDataOdbycia()) %></td>
                            </tr>
                            <tr>
                                <td>Data zakończenia</td>
                                <td><%= formatter.format(wycieczka.getDataZakonczenia()) %></td>
                            </tr>
                            <tr>
                                <td>Punkty</td>
                                <td><%= punktyZaWycieczke %></td>
                            </tr>
                            <tr>
                                <td>Długość</td>
                                <td><%= df.format(dlugoscWycieczki) + " [km]"%></td>
                            </tr>
                            <tr>
                                <td>Opiekun</td>
                                <td><%= wycieczka.getOpiekun() == null ? "-" : wycieczka.getOpiekun() %></td>
                            </tr>
                        </table>
                    </div>

                    <div id="pozycjeWycieczkiDiv">
                        <ol id="pozycjeWycieczkiList">
                            <%
                                for(PozycjaWycieczki pozycjaWycieczki : pozycjeWycieczki){
                            %>
                            <li>
                                <%= "Trasa nr " + pozycjaWycieczki.getNumerTrasy().getNumerTrasy() + " " + formatter.format(pozycjaWycieczki.getDataRozpoczecia()) +
                                        " - " + formatter.format(pozycjaWycieczki.getDataZakonczenia()) + " "
                                        + (pozycjaWycieczki.getKierunek().equals("G") ? pozycjaWycieczki.getNumerTrasy().getPunktyZaTrase().split("/")[0] :
                                        pozycjaWycieczki.getNumerTrasy().getPunktyZaTrase().split("/")[1]) + " pkt " %>
                            </li>
                            <%
                                }
                            %>
                        </ol>
                    </div>

                </div>


                    <div id="opisDiv">
                        <label for="opisTextArea">Opis</label><br>
                        <textarea contenteditable="false" name="opis" id="opisTextArea" cols="60" rows="5" disabled="true">
                            <%= wycieczka.getOpis()%>
                        </textarea>
                    </div>

            </div>
        </div>

    </div>

</body>

</html>