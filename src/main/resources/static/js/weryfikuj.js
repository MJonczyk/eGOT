
function submitWeryfikuj(nrWycieczki) {
    onclick=document.getElementById('weryfikujForm').submit();
    cos = document.getElementById('isAccepted1').checked;
    window.location.replace('http://localhost:8080/weryfikuj/' + nrWycieczki + '/' + cos);
};

window.addEventListener("load", function (ev) {
   addCheckBoxListener();
});

function addCheckBoxListener(){
    var checkbox = document.getElementById('isAccepted1');
    checkbox.addEventListener( 'change', function() {
        if(this.checked) {
            document.getElementById('isAcceptedLabel').classList.remove('redLabel');
            document.getElementById('isAcceptedLabel').classList.add('greenLabel');
            document.getElementById('isAcceptedLabel').innerText = "Zaakceptowana";
        } else {
            document.getElementById('isAcceptedLabel').classList.remove('greenLabel');
            document.getElementById('isAcceptedLabel').classList.add('redLabel');
            document.getElementById('isAcceptedLabel').innerText = "Odrzucona";
        }
    });
};

function toMenu() {
    window.location.replace('http://localhost:8080/index');
};