
const libMap = kakao.maps;

let map = null;

/*
 * DOM ready
 */
document.addEventListener("DOMContentLoaded", function() {
    // 지도 초기화
    map = mapInit();

    // 최신 여행정보 click 이벤트 등록
    RegisterClickEventforTravel();

    // 마킹 표시
    displayMarkers(map);
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


/*
 *  유저의 모든 마커를 지도에 표시한다.
 */
const displayMarkers = function(map) {
    for (let item of positions) {
        let position = new libMap.LatLng(item['longitude'], item['latitude']);
        let marker = new libMap.Marker({
            position: position
        });
        marker.setMap(map);

        // click event
        (function(marker) {
            libMap.event.addListener(marker, 'click', function() {
                getTravelInfoByAjax(item['id']);
            });
        })(marker);
    }
};

/*
 *  Ajax 호출로 해당 마커의 여행 정보를 가져온다.
 */
const getTravelInfoByAjax = function(id) {
    let xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            viewTravelInfo(JSON.parse(this.responseText));
        }
    };

    xhr.open('GET', 'http://localhost:8080/mymap/travel/' + id, true);
    xhr.send();
};

/*
 *  여행정보를 DOM에 할당하여 display
 */
const viewTravelInfo = function(jsonInfo) {
    let contentLeftElement = document.getElementById('map-content-left');
    let contentRightElement = document.getElementById('map-content-right');
    let contentFooterElement = document.getElementById('map-content-footer');
    contentLeftElement.style.display = "inline-block";
    contentRightElement.style.display = "inline-block";
    contentFooterElement.style.display = "inline-block";


    let titleElement = document.getElementById('travel-title');
    let periodElement = document.getElementById('travel-period');
    let descriptionElement = document.getElementById('travel-description');
    let foodInfoElement = document.getElementById('travel-foodInfo');
    let placeInfoElement = document.getElementById('travel-placeInfo');
    let lodgingInfoElement = document.getElementById('travel-lodgingInfo');
    let transportInfoElement = document.getElementById('travel-transportInfo');
    let costElement = document.getElementById('travel-cost');
    let totalReviewElement = document.getElementById('travel-totalReview');
    let memoElement = document.getElementById('travel-memo');
    let openStatusElement = document.getElementById('travel-openStatus');

    titleElement.innerText = jsonInfo.title;
    periodElement.innerText = jsonInfo.periodStartDate + " ~ " + jsonInfo.periodEndDate;
    descriptionElement.innerText = jsonInfo.description;
    foodInfoElement.innerText = jsonInfo.foodInfo;
    placeInfoElement.innerText = jsonInfo.placeInfo;
    lodgingInfoElement.innerText = jsonInfo.lodgingInfo;
    transportInfoElement.innerText = jsonInfo.transportInfo;
    costElement.innerText = jsonInfo.cost;
    totalReviewElement.innerText = jsonInfo.totalReview;
    memoElement.innerText = jsonInfo.memo;
    openStatusElement.innerText = jsonInfo.openStatus;
};

const RegisterClickEventforTravel = function() {
    let travelNodes = document.getElementsByClassName('travel-list');
    let travelCount = travelNodes.length;

    if (travelCount === 0) {
        return;
    }

    for (let i = 0; i < travelCount; i++) {
        let travelNode = travelNodes.item(i);
        travelNode.addEventListener('click', function() {
            let travelId = travelNode.getAttribute('data-id');
            getTravelInfoByAjax(travelId);
        });
    }
};
