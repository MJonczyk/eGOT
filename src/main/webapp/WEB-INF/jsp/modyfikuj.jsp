<%@ page import="com.jestgit.egot.trasa.TrasaDTO" %>
<%@ page import="com.jestgit.egot.trasa.Trasa" %>
<%@ page import="com.jestgit.egot.punkt.Punkt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../css/base.css">
    <link rel="stylesheet" type="text/css" href="../css/modyfikuj.css">
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
                <a href="javascript:{}" onclick="document.getElementById('modyfikujForm').submit();">ZAPISZ</a>
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
                <div><a href="http://localhost:8080/wycieczki">Weryfikacja wycieczek</a></div>
            </div>

            <div id="central">
                <div><h2 id="formTitle">Modyfikuj trasę</h2></div>
                <form:form id="modyfikujForm" method="post" modelAttribute="trasaDto">
                    <%
                        TrasaDTO trasaDTO = (TrasaDTO) request.getAttribute("trasaDto");

                        Punkt punktPoczatkowy = (Punkt) request.getAttribute("punktPoczatkowy");
                        Punkt punktKoncowy = (Punkt) request.getAttribute("punktKoncowy");
                    %>
                    <form:input path="id" name="idTrasa" class="notDisplay"></form:input>
                    <div id="trasaNazwaDiv">
                        <%= punktKoncowy.getNazwaPunktu() + " z " + punktPoczatkowy.getNazwaPunktu()%>
                    </div>
                    <div id="grupaGorskaDiv">
                        <form:label path="nazwaGrupy">Grupa górska</form:label>
                        <form:select path="nazwaGrupy">
                            <form:option value="Tatry Wysokie">Tatry Wysokie</form:option>
                            <form:option value="Góry Izerskie">Góry Izerskie</form:option>
                            <form:option value="Pogórze Izerskie">Pogórze Izerskie</form:option>
                            <form:option value="Karkonosze">Karkonosze</form:option>
                        </form:select>
                        <form:errors path="nazwaGrupy"></form:errors>
                    </div>
                    <div id="punktPoczatkowyDiv">
                        <form:label path="punktPoczatkowy">Punkt początkowy</form:label>
                        <form:select path="punktPoczatkowy">

                            <% ArrayList<Punkt> punkty = (ArrayList<Punkt>) request.getAttribute("punkty"); %>

                            <% for (Punkt punkt: punkty) { %>

                            <form:option value="<%= punkt.getIdPunktu() %>" > <%= punkt.getNazwaPunktu() %> </form:option>

                            <% } %>

                        </form:select>
                    </div>
                    <div id="punktKoncowyDiv">
                        <form:label path="punktKoncowy">Punkt końcowy</form:label>
                        <form:select path="punktKoncowy">

                            <% ArrayList<Punkt> punkty = (ArrayList<Punkt>) request.getAttribute("punkty"); %>

                            <% for (Punkt punkt: punkty) { %>

                            <form:option value="<%= punkt.getIdPunktu() %>"> <%= punkt.getNazwaPunktu() %> </form:option>

                            <% } %>

                        </form:select>
                    </div>
                    <div id="punktyDiv">
                        <form:label path="punktyZaTrase">Punkty</form:label>
                        <form:input type="text" name="punkty" path="punktyZaTrase"/>
                        <form:errors path="punktyZaTrase" cssClass="formError"></form:errors>
                    </div>
                    <div id="opisDiv">
                        <form:label path="opis">Opis</form:label>
                        <form:textarea name="opis" path="opis" cols="60" rows="5"></form:textarea>
                    </div>
                </form:form>
            </div>
        </div>

    </div>

</body>

</html>