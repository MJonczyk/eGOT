<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.jestgit.egot.wycieczka.Wycieczka" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jestgit.egot.decyzja.DecyzjaDTO" %>
<%@ page import="com.jestgit.egot.WeryfikujDTO" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../../css/base.css">
    <link rel="stylesheet" type="text/css" href="../../css/decyzja.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../js/decyzja.js"></script>
    <script>
        window.addEventListener("load", function (ev) {
            var d = document.getElementById('zatwierdzenie');
            if(d.innerText == "TAK") {
                document.getElementById('zatwierdzenie').classList.add('greenText');
            }
            else {
                document.getElementById('zatwierdzenie').classList.add('redText');
            }
        });
    </script>
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">WYLOGUJ</a>
                <a href="#"  onclick=" var r = confirm('Czy na pewno chcesz anulować?');
                                            if(r == false){
                                                    return;
                                             }
                                            else {
                                                window.location.replace('http://localhost:8080/wycieczki#anulowano');
                                            } " >ANULUJ</a>
                <a href="#" onclick=" var r = confirm('Czy na pewno chcesz zapisać decyzję?');
                                            if(r == false){
                                                    return;
                                             }
                                            else {
                                                document.getElementById('decyzjaForm').submit();
                                            } ">ZAPISZ</a>
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
                            <h2 id="formTitle">Decyzja</h2>
                        </div>
                    </div>
                    <%
                        DecyzjaDTO decyzjaDTO = (DecyzjaDTO) request.getAttribute("decyzjaDto");
                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    %>
                <form:form id="decyzjaForm" method="post" modelAttribute="decyzjaDto">
                    <div id="wycieczkaTable">
                        <table>
                            <tr>
                                <td class="boldC">Wycieczka nr</td>
                                <td><%= decyzjaDTO.getNumerWycieczki()%></td>
                                <form:input type="text" name="numerWycieczki" path="numerWycieczki" cssClass="hidden"/>
                            </tr>
                            <tr>
                                <td class="boldC">Decyzja nr</td>
                                <td><%= request.getAttribute("nextDecisionNumber")%></td>
                                <form:input type="text" name="numerDecyzji" path="numerDecyzji" cssClass="hidden"/>
                            </tr>
                            <tr>
                                <td class="boldC">Data decyzji</td>
                                <td><%= formatter.format(decyzjaDTO.getData()) %></td>
                            </tr>
                            <tr>
                                <td class="boldC">Czy zatwierdzona</td>
                                <td id="zatwierdzenie"><%= Boolean.parseBoolean(decyzjaDTO.getCzyZatwierdzona()) ? "TAK" : "NIE" %></td>
                                <form:input type="text" name="czyZatwierdzona" path="czyZatwierdzona" cssClass="hidden"/>
                            </tr>
                        </table>
                    </div>

                    <div id="opisDiv">
                        <form:label path="uzasadnienie" class="boldC">Uzasadnienie</form:label><br>
                        <form:textarea path="uzasadnienie" placeholder="Uzasadnij decyzję..." contenteditable="false" name="opis" id="opisTextArea" cols="60" rows="5"></form:textarea>
                        <form:errors path="uzasadnienie" cssClass="formError"></form:errors>
                    </div>
            </form:form>
            </div>
        </div>
    </div>

</body>

</html>