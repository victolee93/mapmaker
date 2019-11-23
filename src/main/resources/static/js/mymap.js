
const libMap = kakao.maps;

let map = null;

/*
 * DOM ready
 */
document.addEventListener("DOMContentLoaded", function() {
    // 지도 초기화
    map = mapInit();

    // 마킹 표시
    setMarker();
});

/*
 *  지도를 초기화한다. ( 지도의 중심, 레벨 등 )
 */
const mapInit = function() {
    let container = document.getElementById('small-map'); //지도를 담을 영역의 DOM 레퍼런스

    // 지도 생성
    let options = {
        center: new libMap.LatLng(37.531425, 127.006561),
        level: 8
    };
    return new libMap.Map(container, options);
};

const setMarker = function(map) {
    // 위도, 경도
    markerPositions = markerList;

    // 마커 객체를 지도에 표시
    let marker = new libMap.Marker({
        position: latLng
    });
    marker.setMap(map);
}


