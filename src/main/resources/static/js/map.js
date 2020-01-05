
mapObj = {
    map : null,

    init : () => {
        // 지도 초기화
        mapObj.map = mapObj.mapInit();

        // 마킹 표시
        mapObj.displayMarkers(mapObj.map);
    },

    /*
     *  지도를 초기화한다. ( 지도의 중심, 레벨 등 )
     */
    mapInit : () => {
        let container = document.querySelector('#small-map'); //지도를 담을 영역의 DOM 레퍼런스

        // 지도 생성
        let options = {
            center: new kakao.maps.LatLng(37.531425, 127.006561),
            level: 8
        };
        return new kakao.maps.Map(container, options);
    },

    displayMarkers : (map) => {
        for (let item of positions) {
            let position = new kakao.maps.LatLng(item['longitude'], item['latitude']);
            let marker = new kakao.maps.Marker({
                position: position
            });
            marker.setMap(map);
        }
    },
};

mapObj.init();