
const libMap = kakao.maps;

let map = null;             // 초기화된 kakao 지도
let markers = [];           // 마커 객체들
let markerPositions = [];   // 마커의 위도, 경도

/*
 * DOM ready
 */
document.addEventListener("DOMContentLoaded", function() {
    // 마킹 추가
    map = mapInit();  // 지도 초기화
    libMap.event.addListener(map, 'click', function(mouseEvent) {
        addMark(mouseEvent);
    });

    // 마킹 초기화 
    document.getElementById('reset-btn').addEventListener('click', function() {
        hideMarkers();
    });

    // submit
    document.getElementById('write-btn').addEventListener('click', function() {
        document.getElementById('maker-positions').setAttribute('value', markerPositions);
        document.forms["making-form"].submit();
    });
});


/*
 *  지도를 초기화한다. ( 지도의 중심, 레벨 등 )
 */
const mapInit = function() {
    let container = document.getElementById('map'); // 지도를 담을 영역

    // 지도 생성
    let options = {
        center: new libMap.LatLng(37.531425, 127.006561),
        level: 8
    };
    return new libMap.Map(container, options);
};


/*
 *  마킹을 추가하고, 지도에 노출한다
 */
const addMark = function(mouseEvent) {
    let latlng = mouseEvent.latLng; // 위도, 경도

    // 마커 객체를 지도에 표시
    let marker = new libMap.Marker({
        position: latlng
    });
    marker.setMap(map);

    markers.push(marker);
    markerPositions.push(latlng);
};


/*
 *  지도에서 모든 마커를 제거한다.
 */
const hideMarkers = function() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    
    markers = [];
    markerPositions = [];
};