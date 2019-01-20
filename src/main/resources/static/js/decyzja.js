function submitDecyzja(nrWycieczki) {
    window.alert("aa");
    var r = confirm("Czy na pewno chcesz zapisać decyzję?");
    if(r == false){
        return;
    }
    else {
        document.getElementById('decyzjaForm').submit();
    }
};

window.addEventListener("load", function (ev) {
    var d = document.getElementById('zatwierdzenie');
    if(d.innerText == "TAK") {
        document.getElementById('zatwierdzenie').classList.add('greenText');
    }
    else {
        document.getElementById('zatwierdzenie').classList.add('redText');
    }
});

function toMenu() {
    window.location.replace('http://localhost:8080/index');
};