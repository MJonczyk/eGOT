function requestNotificationPermission() {
    Notification.requestPermission();
}

window.addEventListener('load', function (ev) {
    requestNotificationPermission();
})

function toMenu() {
    window.location.replace('http://localhost:8080/index');
};

var aktualnosci = ["Cos", "Cos innego", "Cos jeszcze"];
var licznik = 0;

// $(document).ready(function () {
//     $("#dalejA").click(function (e) {
//         e.preventDefault();
//         $('#indexOpisDiv').innerText = aktualnosci[licznik % aktualnosci.length];
//         licznik++;
//     });
// });


var myRequest = new XMLHttpRequest();
// myRequest.open('GET', '../aktualnosci/ak1.html');

myRequest.onreadystatechange = function (ev) {
    if(myRequest.readyState === 4) {
        document.getElementById('indexOpisDiv').innerHTML = myRequest.responseText;
    }
};

function sendAJAX() {
    licznik++;
    myRequest.open('GET', '../aktualnosci/ak' + (licznik) + '.html');
    licznik = (licznik % 3);
    myRequest.send();
}