$(document).ready(function() {
    $('.tableRow').click(function () {
        window.location = $(this).attr('href');
        return false;
    });
});

// function click() {
//     window.alert("a")
// }