$(document).ready(function() {
    $('.tableRow').click(function () {
        window.location = $(this).attr('href');
        return false;
    });

    if(window.location.hash == '#success'){
        var n = new Notification("eGOT", {
            body : "Pomyślnie zmodyfikowano trasę!",
        });
    }
    else if(window.location.hash == '#dodano'){
        var n = new Notification("eGOT", {
            body : "Pomyślnie dodano trasę!",
        });
    }
});

function submitWeryfikuj(nrWycieczki) {
    onclick=document.getElementById('weryfikujForm').submit();
    cos = document.getElementById('isAccepted').checked;
    window.location.replace('http://localhost:8080/weryfikuj/' + nrWycieczki + '/' + cos);
};

// function statecheck() {
//     var myLayer = document.getElementsByName('isAccepted');
//     var second = document.getElementsByName('_isAccepted');
//     second.onstatechange = statecheck;
//     var label = document.getElementById('isAcceptedCLabel');
//     if(myLayer.checked == true){
//         label.style.backgroundColor = "#fff";
//     } else {
//         label.style.backgroundColor = "#bbb";
//     };
//     window.alert(second.value + " " + myLayer.value)
//
// }

// function click() {
//     window.alert("a")
// }