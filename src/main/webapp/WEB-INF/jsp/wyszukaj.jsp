<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../css/base.css">
    <link rel="stylesheet" type="text/css" href="../css/wyszukaj.css">
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">WYLOGUJ</a>
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

            <div id="central">
                <form:form method="get" modelAttribute="trasaSearchDto" action="http://localhost:8080/wyniki" id="wyszukajForm">
                    <div><h2 id="formTitle">Wyszukiwarka tras</h2></div>
                    <div id="searchDiv">
                        <input type="image" src="../images/lupa.png" class="lupa" alt="Submit">
                        <form:input type="text" path="searchPhrase" id="searchInput"/>
                    </div>
                    <div id="regionDiv">
                        <form:label path="nazwaRegionu">Region</form:label>
                        <form:select path="nazwaRegionu">
                            <option disabled selected value > -- Wybierz region-- </option>
                            <form:option value="Tatry i Podtatrze">Tatry i Podtatrze</form:option>
                            <form:option value="Tatry Słowackie">Tatry Słowackie</form:option>
                            <form:option value="Beskidy Zachodnie">Beskidy Zachodnie</form:option>
                            <form:option value="Beskidy Wschodnie">Beskidy Wschodnie</form:option>
                            <form:option value="Góry Świętokrzyskie">Góry Świętokrzyskie</form:option>
                            <form:option value="Sudety">Sudety</form:option>
                            <form:option value="Słowacja">Słowacja</form:option>
                        </form:select>
                    </div>
                    <div id="grupaGorskaDiv">
                        <form:label path="nazwaGrupy">Grupa górska</form:label>
                        <form:select path="nazwaGrupy" id="grupaGorskaSelect">
                            <option disabled selected value > -- Wybierz grupę-- </option>
                            <form:option value="Tatry Wysokie">Tatry Wysokie</form:option>
                            <form:option value="Góry Izerskie">Góry Izerskie</form:option>
                            <form:option value="Pogórze Izerskie">Pogórze Izerskie</form:option>
                            <form:option value="Karkonosze">Karkonosze</form:option>
                            <form:option value="Beskid Śląski">Beskid Śląski</form:option>
                            <form:option value="Góry Stołowe, Wzgórza Lewińskie">Góry Stołowe, Wzgórza Lewińskie</form:option>
                        </form:select>
                    </div>
                    <div id="punktPoczatkowyDiv">
                        <form:label path="punktPoczatkowy">Punkt początkowy</form:label>
                        <form:input type="text" name="punktPoczatkowy" path="punktPoczatkowy"/>
                    </div>
                    <div id="punktKoncowyDiv">
                        <form:label path="punktKoncowy">Punkt końcowy</form:label>
                        <form:input type="text" name="punktKoncowy" path="punktKoncowy"/>
                    </div>
                    <div id="punktyMinDiv">
                        <form:label path="punktyMin">Min. punktów</form:label>
                        <form:input type="text" name="punktyMin" path="punktyMin"/>
                    </div>
                    <div id="punktyMaxDiv">
                        <form:label path="punktyMax">Max. punktów</form:label>
                        <form:input type="text" name="punktyMax" path="punktyMax"/>
                    </div>
                </form:form>
            </div>
        </div>

    </div>

</body>

</html>