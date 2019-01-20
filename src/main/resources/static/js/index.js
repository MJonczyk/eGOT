function requestNotificationPermission() {
    Notification.requestPermission();
}

window.addEventListener('load', function (ev) {
    requestNotificationPermission();
})

function toMenu() {
    window.location.replace('http://localhost:8080/index');
};