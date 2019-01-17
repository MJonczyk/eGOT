$(document).ready(function() {
    $('.tableRow').click(function () {
        window.location = $(this).attr('href');
        return false;
    });
});

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