<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jestgit.egot.trasa.Trasa" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../css/base.css">
    <link rel="stylesheet" type="text/css" href="../css/wyniki.css">
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">LOGOWANIE</a>
                <a href="#">REJESTRACJA</a>
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
                <%
                    ArrayList<Trasa> trasy =  (ArrayList<Trasa>) request.getAttribute("trasy");
                %>

                <form>
                    <div><h2 id="formTitle">Trasy</h2></div>
                    <div id="trasyTable">
                        <table id="trasyTableT">
                            <tr>
                                <th>Punkt pocz.</th>
                                <th>Punkt koń.</th>
                                <th>Punkty</th>
                                <th>Region</th>
                                <th>Grupa</th>
                            </tr>
                            <%
                                for(Trasa trasa: trasy){
                            %>
                            <tr>
                                <td><%= trasa.getPunktPoczatkowy().getNazwaPunktu() %></td>
                                <td><%= trasa.getPunktKoncowy().getNazwaPunktu() %></td>
                                <td><%= trasa.getPunktyZaTrase() %></td>
                                <td><%= trasa.getGrupaGorskanazwaGrupy().getNazwaRegionu().getNazwaRegionu()%></td>
                                <td><%= trasa.getGrupaGorskanazwaGrupy().getNazwaGrupy()%></td>
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