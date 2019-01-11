<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
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
                <form id="dodajForm">
                    <div><h2 id="formTitle">Formularz modyfikacji danych trasy punktowanej</h2></div>
                    <div id="trasaNazwaDiv">
                        Bacówka na jamnej z Suchej Góry
                    </div>
                    <div id="regionDiv">
                        <label for="regionSelect">Region</label>
                        <select id="regionSelect">
                            <option value="volvo">Sudety</option>
                            <option value="saab">Pokarpacie</option>
                            <option value="mercedes">Costam</option>
                            <option value="audi">Elo</option>
                        </select>
                    </div>
                    <div id="grupaGorskaDiv">
                        <label for="grupaGorskaSelect">Grupa górska</label>
                        <select id="grupaGorskaSelect">
                            <option value="volvo">Sudety</option>
                            <option value="saab">Pokarpacie</option>
                            <option value="mercedes">Costam</option>
                            <option value="audi">Elo</option>
                        </select>
                    </div>
                    <div id="punktPoczatkowyDiv">
                        <label for="punktPoczatkowyInput">Punkt początkowy</label>
                        <input type="text" name="punktPoczatkowy" id="punktPoczatkowyInput">
                    </div>
                    <div id="punktKoncowyDiv">
                        <label for="punktKoncowyInput">Punkt końcowy</label>
                        <input type="text" name="punktKoncowy" id="punktKoncowyInput">
                    </div>
                    <div id="punktyDiv">
                        <label for="punktyInput">Punkty</label>
                        <input type="text" name="punkty" id="punktyInput">
                    </div>
                    <div id="opisDiv">
                        <label for="opisTextArea">Opis</label>
                        <textarea name="opis" id="opisTextArea" cols="60" rows="5"></textarea>
                    </div>
                    <input type="submit" id="submit-form" class="hidden" />
                </form>
            </div>
        </div>

    </div>

</body>

</html>