<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>eGOT</title>
    <meta name="keywords" content="egot, got, gory, odznaka, turysta, przewodnik, przodownik">
    <meta name="description" content="Strona poswiecona gorskiej odznace turystycznej">
    <link rel="stylesheet" type="text/css" href="../css/base.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <script src="../js/index.js"></script>
</head>

<body>
    <div id="container">
        <div id="topBar">
            <div id="titleDiv">
                <h1 id="appTitle">eGOT</h1>
            </div>

            <div id="buttons">
                <a href="#">WYLOGUJ</a>
                <a id="dalejA" href="#" onclick="sendAJAX()">DALEJ</a>
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
                <h1 id="h1Position">Witaj na stronie eGOT!</h1>
                <div id="indexOpisDiv">
                    <p>
                        Górska Odznaka Turystyczna Polskiego Towarzystwa Turystyczno-Krajoznawczego nie jest zwykłą odznaką sprawnościową, czy krajoznawczą, jakich wiele funkcjonuje czy to w samym PTTK, czy w innych organizacjach. Ta jej osobliwa szczególność wynika z wielu przyczyn, z których na pierwszym miejscu trzeba wymienić swoistość środowiska górskiego, tak odmiennego od tego, z czym spotykamy się na nizinach. "Góry są całe jakąś baśnią świata" - powiada poeta i bez wątpienia ma rację. Góry są baśniowe naturalnym pięknem ich przyrody ożywionej i nieożywionej, oraz pięknem kultury regionalnej, jaka się u ich stóp, a także w nich samych, rozwinęła. Ale jak każda baśń, także i one kryją w sobie nadto elementy grozy, z tym że jest ona wcale niebaśniowa, a całkiem realna, o czym dowodnie poświadczają kroniki TOPR i GOPR. Góry bowiem, nawet te najniższe, pogardliwie nieraz określane jako "kapuściane", potrafią być niebywale groźne dla kogoś, kto ich nie zna, kto nie wie, jak się po nich poruszać, lub nie umie podjąć decyzji, czy można w nie wyjść, czy też raczej zatrzymać się na dłużej w schronisku. Jednym słowem, chcąc właściwie chodzić po górach, trzeba się tego po prostu nauczyć.
                    </p>
                </div>
            </div>
        </div>

    </div>

</body>

</html>