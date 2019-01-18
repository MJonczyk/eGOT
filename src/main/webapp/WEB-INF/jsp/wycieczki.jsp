<%@ page import="com.jestgit.egot.wycieczka.Wycieczka" %>
<%@ page import="com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Hashtable" %>
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
    <link rel="stylesheet" type="text/css" href="../css/wyswietl.css">
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
                <a href="javascript:{}" onclick="document.getElementById('dodajForm').submit();">ZAPISZ</a>
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
                <%
                    ArrayList<Wycieczka> wycieczki = (ArrayList<Wycieczka>) request.getAttribute("wycieczki");
                    Hashtable<Long, Float> wycieczkiIDlugosci = (Hashtable<Long, Float>) request.getAttribute("wycieczkiIDlugosci");
                    Hashtable<Long, Float> wycieczkiIPunkty = (Hashtable<Long, Float>) request.getAttribute("wycieczkiIPunkty");
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.HALF_UP);
                %>

                <form>
                    <div><h2 id="formTitle">Wycieczki</h2></div>
                    <div id="trasyTable">
                        <table id="trasyTableT">
                            <tr>
                                <th>Nr wycieczki</th>
                                <th>Nr książeczki</th>
                                <th>Data rozpoczęcia</th>
                                <th>Data zakończenia</th>
                                <th>Długość [km]</th>
                                <th>Punkty</th>
                                <th>Opiekun</th>
                            </tr>
                            <%
                                for(Wycieczka wycieczka: wycieczki){
                            %>
                            <tr class="tableRow" href="http://localhost:8080/weryfikuj/<%=wycieczka.getNumerWycieczki()%>">
                                <td><%= wycieczka.getNumerWycieczki() %></td>
                                <td><%= wycieczka.getNumerKsiazeczki().getNumerKsiazeczki()%></td>
                                <td><%= formatter.format(wycieczka.getDataRozpoczecia()) %></td>
                                <td><%= formatter.format(wycieczka.getDataZakonczenia()) %></td>
                                <td><%= df.format(wycieczkiIDlugosci.get(wycieczka.getNumerWycieczki())) %></td>
                                <td><%= wycieczkiIPunkty.get(wycieczka.getNumerWycieczki()) %></td>
                                <td><%= wycieczka.getOpiekun() == null ? "-" : wycieczka.getOpiekun()%></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </form>

            </div>
        </div>

    </div>

</body>

</html>