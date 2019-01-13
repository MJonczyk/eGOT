<%@ page contentType="text/html; charset=UTF-8" %>

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
                <div><a href="http://localhost:8080/wyswietl">Modyfikacja tras</a></div>
                <div><a href="#">Usuwanie tras</a></div>
                <div><a href="http://localhost:8080/wyswietl">Przeglądanie tras</a></div>
                <div><a href="http://localhost:8080/wyszukaj">Wyszukiwanie tras</a></div>
                <div><a href="http://localhost:8080/weryfikuj">Weryfikacja wycieczek</a></div>
            </div>

            <div id="central">
                <form>
                    <div><h2 id="formTitle">Wyszukiwarka tras</h2></div>
                    <div id="searchDiv">
                        <span id="span"><a href="http://localhost:8080/wyswietl"><img src="../images/lupa.png" class="lupa"></a></span>
                        <input type="text" name="search" id="searchInput">
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
                    <div id="punktyMinDiv">
                        <label for="punktyMinInput">Min. punktów</label>
                        <input type="text" name="punktyMin" id="punktyMinInput">
                    </div>
                    <div id="punktyMaxDiv">
                        <label for="punktyMaxInput">Max. punktów</label>
                        <input type="text" name="punktyMax" id="punktyMaxInput">
                    </div>
                </form>
            </div>
        </div>

    </div>

</body>

</html>