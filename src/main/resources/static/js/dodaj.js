function submitDodaj() {
    var selectPP = document.getElementById('punktPoczatkowy');
    var selectPK = document.getElementById('punktKoncowy');
    var srogiError = document.getElementById('srogiError');
    // srogiError.innerText = "Trasa " + selectPP.innerText + " - " + selectPK.innerText + " już istnieje!";
    if(selectPP.value == selectPK.value){
        document.getElementById('punktError').classList.remove("hidden");
        document.getElementById('punktError').classList.add("formError");

        document.getElementById('srogiError').classList.remove("formError");
        document.getElementById('srogiError').classList.add("hidden");
    }
    else {
        document.getElementById('punktError').classList.remove("formError");
        document.getElementById('punktError').classList.add("hidden");

        var r = confirm("Czy na pewno chcesz dodać trasę?");
        if(r == false){
            return;
        }
        else {
            document.getElementById('dodajForm').submit();
        }
    }
}

function anulujDodaj() {
    var r = confirm("Czy na pewno chcesz anulować?");
    if(r == false){
        return;
    }
    else {
        window.location.replace('http://localhost:8080/wyswietl#back');
    }
}

function toMenu() {
    window.location.replace('http://localhost:8080/index');
};