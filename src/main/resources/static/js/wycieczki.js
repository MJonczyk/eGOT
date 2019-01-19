$(document).ready(function() {
    $('.tableRow').click(function () {
        window.location = $(this).attr('href');
        return false;
    });

    if(window.location.hash == '#decyzja'){
        var n = new Notification("eGOT", {
            body : "Decyzja została zapisana w bazie danych"
        });
    }
    else if(window.location.hash == '#anulowano'){
        var n = new Notification("eGOT", {
            body : "Anulowano operację"
        });
    }
});

function zweryfikowaneOnClick() {
    window.location.replace('http://localhost:8080/wycieczki/zweryfikowane');
}

function niezweryfikowaneOnClick() {
    window.location.replace('http://localhost:8080/wycieczki/niezweryfikowane');
}

function wszystkieOnClick() {
    window.location.replace('http://localhost:8080/wycieczki');
}