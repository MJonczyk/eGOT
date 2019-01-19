function submitDecyzja(nrWycieczki) {
    window.alert("aa");
    var r = confirm("Czy na pewno chcesz zapisać decyzję?");
    if(r == false){
        return;
    }
    else {
        document.getElementById('decyzjaForm').submit();
    }
}