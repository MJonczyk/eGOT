function requestNotificationPermission() {
    Notification.requestPermission();
}

window.addEventListener('load', function (ev) {
    requestNotificationPermission();
})