<%@ page import="com.jestgit.egot.trasa.TrasaDTO" %>
<%@ page import="com.jestgit.egot.trasa.Trasa" %>
<%@ page import="com.jestgit.egot.punkt.Punkt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../js/modyfikuj.js"></script>
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">WYLOGUJ</a>
                <a href="#" onclick="anulujModyfikuj();return false;">ANULUJ</a>
                <a href="#" onclick="submitModyfikuj(<%= ((TrasaDTO) request.getAttribute("trasaDto")).getNumerTrasy() %>);return false;">ZAPISZ</a>
            </div>
        </div>

        <div id="underTopBar">
            <div id="menu">
                <div>
                    <h3 id="menuTitle" onclick="toMenu(); return false;">MENU</h3>
                </div>
                <div><a href="http://localhost:8080/dodaj">Dodawanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Modyfikacja tras</a></div>
                <div><a href="http://localhost:8080/usun">Usuwanie tras</a></div>
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
                        Float odleglosc = (Float) request.getAttribute("odleglosc");
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    %>
                    <form:input path="id" name="idTrasa" class="notDisplay"></form:input>
                    <div id="trasaNazwaDiv">
                        <%= punktKoncowy.getNazwaPunktu() + " z " + punktPoczatkowy.getNazwaPunktu()%>
                    </div>
                    <div id="grupaGorskaDiv">
                        <form:label path="nazwaGrupy" cssClass="boldC">Grupa górska</form:label>
                        <form:select path="nazwaGrupy">
                            <form:option value="Tatry Wysokie">Tatry Wysokie</form:option>
                            <form:option value="Góry Izerskie">Góry Izerskie</form:option>
                            <form:option value="Pogórze Izerskie">Pogórze Izerskie</form:option>
                            <form:option value="Karkonosze">Karkonosze</form:option>
                            <form:option value="Podtatrze">Podtatrze</form:option>
                            <form:option value="Zapadne Tatry">Zapadne Tatry</form:option>
                            <form:option value="Rudawy Janowickie">Rudawy Janowickie</form:option>
                            <form:option value="Beskid Wyspowy">Beskid Wyspowy</form:option>
                            <form:option value="Spisz i Pieniny">Spisz i Pieniny</form:option>
                            <form:option value="Góry Kamienne">Góry Kamienne</form:option>
                            <form:option value="Góry Bardzkie">Góry Bardzkie</form:option>
                        </form:select>
                        <form:errors path="nazwaGrupy"></form:errors>
                    </div>
                    <div id="punktPoczatkowyDiv">
                        <form:label path="punktPoczatkowy" cssClass="boldC">Punkt początkowy</form:label>
                        <form:select path="punktPoczatkowy">

                            <% ArrayList<Punkt> punkty = (ArrayList<Punkt>) request.getAttribute("punkty"); %>

                            <% for (Punkt punkt: punkty) { %>

                            <form:option value="<%= punkt.getIdPunktu() %>" > <%= punkt.getNazwaPunktu() %> </form:option>

                            <% } %>

                        </form:select><span id="punktError" class="hidden">Punkt początkowy i końcowy są takie same!</span>
                    </div>
                    <div id="punktKoncowyDiv">
                        <form:label path="punktKoncowy" cssClass="boldC">Punkt końcowy</form:label>
                        <form:select path="punktKoncowy">

                            <% ArrayList<Punkt> punkty = (ArrayList<Punkt>) request.getAttribute("punkty"); %>

                            <% for (Punkt punkt: punkty) { %>

                            <form:option value="<%= punkt.getIdPunktu() %>"> <%= punkt.getNazwaPunktu() %> </form:option>

                            <% } %>

                        </form:select><span id="srogiError" class=" <%= request.getAttribute("srogiError") == null ? "hidden" : "formError" %> "> Trasa już istnieje!</span>
                    </div>
                    <div id="punktyDiv">
                        <form:label path="punktyZaTrase" cssClass="boldC">Punkty</form:label>
                        <form:input type="text" name="punkty" path="punktyZaTrase"/>
                        <form:errors path="punktyZaTrase" cssClass="formError"></form:errors>
                    </div>

                    <div id="dlugoscDiv">
                        <label for="dlugosc" class="boldC">Długość</label>
                        <input type="text" name="dlugosc" id="dlugosc" disabled="true" value="<%= decimalFormat.format(odleglosc) + " km" %>"/>
                    </div>

                    <div id="roznicaDiv">
                        <label for="roznica" class="boldC">Różnica wysokości</label>
                        <input type="text" name="roznica" id="roznica" disabled="true" value="<%= decimalFormat.format(punktKoncowy.getWysokoscNPM() - punktPoczatkowy.getWysokoscNPM()) + " m.n.p.m" %>"/>
                    </div>

                    <div id="opisDiv">
                        <form:label path="opis" cssClass="boldC">Opis</form:label>
                        <form:textarea name="opis" path="opis" cols="60" rows="5"></form:textarea>
                    </div>
                </form:form>
            </div>
        </div>

    </div>

</body>

</html>