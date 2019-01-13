<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.jestgit.egot.wycieczka.Wycieczka" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jestgit.egot.decyzja.DecyzjaDTO" %>
<%@ page import="com.jestgit.egot.WeryfikujDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../css/base.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
                <a href="javascript:{}" onclick="document.getElementById('weryfikujForm').submit();">ZAPISZ</a>
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
                <div><a href="http://localhost:8080/weryfikuj">Weryfikacja wycieczek</a></div>
            </div>

            <div id="central">
                    <div id="titleTDiv">
                        <div id="titleTitleDiv">
                            <h2 id="formTitle">Decyzja</h2>
                        </div>
                    </div>
                    <%
                        DecyzjaDTO decyzjaDTO = (DecyzjaDTO) request.getAttribute("decyzjaDto");
                    %>

                    <div id="wycieczkaTable">
                        <table>
                            <tr>
                                <td>Wycieczka nr</td>
                                <td><%= decyzjaDTO.getNumerWycieczki()%></td>
                            </tr>
                            <tr>
                                <td>Decyzja nr</td>
                                <td><%= request.getAttribute("nextDecisionNumber")%></td>
                            </tr>
                            <tr>
                                <td>Data decyzji</td>
                                <td><%= decyzjaDTO.getData().toString()%></td>
                            </tr>
                            <tr>
                                <td>Czy zatwierdzona</td>
                                <td><%= decyzjaDTO.getCzyZatwierdzona()%></td>
                            </tr>
                        </table>
                    </div>

                    <div id="opisDiv">
                        <label for="opisTextArea">Uzasadnienie</label>
                        <textarea contenteditable="false" name="opis" id="opisTextArea" cols="60" rows="5">
                            <%= decyzjaDTO.getUzasadnienie()%>
                        </textarea>
                    </div>

            </div>
        </div>

    </div>

</body>

</html>