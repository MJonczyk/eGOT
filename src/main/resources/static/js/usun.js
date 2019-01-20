function submitUsun(nrWycieczki) {
    var r = confirm("Czy na pewno chcesz usunąć trasę?");
    if(r == false){
        return;
    }
    else {
        document.getElementById('modyfikujForm').submit();
    }
}

function anulujUsun() {
    var r = confirm("Czy na pewno chcesz anulować?");
    if(r == false){
        return;
    }
    else {
        window.location.replace('http://localhost:8080/usun#back');
    }
}

function toMenu() {
    window.location.replace('http://localhost:8080/index');
};