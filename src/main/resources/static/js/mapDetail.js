
mapDetailObj = {
    map : null,

    init : () => {
        // display 초기화
        mapDetailObj.setDisplay();

        // 지도 초기화
        mapDetailObj.map = mapDetailObj.mapInit();

        // 마킹 표시
        mapDetailObj.displayMarkers(mapDetailObj.map);
    },

    /*
      * 댓글 개수에 따라 컨테이너의 height를 조정
      */
    setDisplay : () => {
        let originHeight = document.querySelector('#map-container').offsetHeight + 830;
        let commentCount = document.querySelectorAll('.comment').length;

        // 새로운 높이 = 원래 높이 + 댓글수 * 130px
        let newHeight = (originHeight + (commentCount * 130)) + "px";
        document.querySelector('#map-container').style.height = newHeight;
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

mapDetailObj.init();