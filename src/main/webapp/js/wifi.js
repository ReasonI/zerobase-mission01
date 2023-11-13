function getWifi() {
    let lat = document.getElementById("lat").value;
    let lnt = document.getElementById("lnt").value;
    if (lat === "0.0" || lnt === "0.0") {
        alert("위치 정보를 입력한 후에 조회해 주세요.");
        return false;
    }else {
        return true;
    }
}
function getLocation() {
    navigator.geolocation.getCurrentPosition(function (pos) {
            var latitude = pos.coords.latitude;
            var longitude = pos.coords.longitude;
            document.getElementById("lat").value = latitude;
            document.getElementById("lnt").value = longitude;
        }
    )
}