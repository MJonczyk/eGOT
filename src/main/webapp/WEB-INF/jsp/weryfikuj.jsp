<%@ page import="com.jestgit.egot.wycieczka.Wycieczka" %>
<%@ page import="com.jestgit.egot.pozycjawycieczki.PozycjaWycieczki" %>
<%@ page import="java.util.ArrayList" %>
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
                <div><a href="http://localhost:8080/modyfikuj">Modyfikacja tras</a></div>
                <div><a href="#">Usuwanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Przeglądanie tras</a></div>
                <div><a href="http://localhost:8080/wyszukaj">Wyszukiwanie tras</a></div>
                <div><a href="http://localhost:8080/weryfikuj">Weryfikacja wycieczek</a></div>
            </div>

            <div id="central">
                    <div><h2 id="formTitle">Weryfikacja wycieczki</h2></div>
                    <%
                        Wycieczka wycieczka = (Wycieczka) request.getAttribute("wycieczka");
                        ArrayList<PozycjaWycieczki> pozycjeWycieczki = (ArrayList<PozycjaWycieczki>) request.getAttribute("pozycjeWycieczki");
                    %>

                    <div id="wycieczkaTable">
                        <table>
                            <tr>
                                <td>Wycieczka nr</td>
                                <td><%= wycieczka.getNumerWycieczki()%></td>
                            </tr>
                            <tr>
                                <td>Data odbycia</td>
                                <td><%= wycieczka.getDataOdbycia()%></td>
                            </tr>
                            <tr>
                                <td>Data zakończenia</td>
                                <td><%= wycieczka.getDataZakonczenia()%></td>
                            </tr>
                            <tr>
                                <td>Punkty</td>
                                <td><%= wycieczka.getPunkty()%></td>
                            </tr>
                            <tr>
                                <td>Długość</td>
                                <td><%= wycieczka.getDlugosc()%></td>
                            </tr>
                            <tr>
                                <td>Opiekun</td>
                                <td><%= wycieczka.getOpiekun()%></td>
                            </tr>
                        </table>
                    </div>

                    <div id="opisDiv">
                        <label for="opisTextArea">Opis</label>
                        <textarea contenteditable="false" name="opis" id="opisTextArea" cols="60" rows="5">
                            <%= wycieczka.getOpis()%>
                        </textarea>
                    </div>

                    <div id="pozycjeWycieczkiDiv">
                        <%
                            for(PozycjaWycieczki pozycjaWycieczki: pozycjeWycieczki){
                        %>
                                <div class="pozycjaWycieczki">
                                    <%= "Trasa nr " + pozycjaWycieczki.getNumerTrasy()%>
                                </div>
                        <%
                            }
                        %>
                    </div>

            </div>
        </div>

    </div>

</body>

</html>