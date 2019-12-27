
const mymapObj = {
    map : null,
    
    init : () => {
        // 지도 초기화
        mymapObj.map = mymapObj.mapInit();

        // 마킹 표시
        mymapObj.displayMarkers(mymapObj.map);

        // 최신 여행정보 click 이벤트 등록
        mymapObj.registerClickEventForTravel();
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

    /*
     *  유저의 모든 마커를 지도에 표시한다.
     *  마커 클릭 시, 비동기로 여행정보를 가져와서 DOM에 뿌려줌
     */
    displayMarkers : (map) => {
        for (let item of positions) {
            let position = new kakao.maps.LatLng(item['longitude'], item['latitude']);
            let marker = new kakao.maps.Marker({
                position: position
            });
            marker.setMap(map);

            // click event
            ( marker =>  {
                let url = 'http://localhost:8080/mymap/travel/marker/' + item['id'];

                kakao.maps.event.addListener(marker, 'click', () => {
                    ajaxUtil.GETCall(url)
                        .then( (res) => {
                            mymapObj.displayTravelInfo(res)
                        });
                });
            })( marker );
        }
    },


    /*
     * 여행정보 click 이벤트
     * 비동기로 여행정보를 가져와서 DOM에 뿌려줌
     */
    registerClickEventForTravel : () => {
        let travelNodes = document.getElementsByClassName('travel-list');
    
        for (let travelNode of travelNodes) {
            let travelId = travelNode.getAttribute('data-id');
    
            travelNode.addEventListener('click', () => {
                let url = 'http://localhost:8080/mymap/travel/' + travelId;
                ajaxUtil.GETCall(url)
                    .then( (res) => {
                        mymapObj.displayTravelInfo(res)
                    });
            });
        }
    },

    
    /*
     *  여행정보를 DOM에 display
     */
    displayTravelInfo : (jsonInfo) => {
        // 1. modal에 노출되는 데이터 할당
        let contentLeftElement = document.querySelector('#map-content-left');
        let contentRightElement = document.querySelector('#map-content-right');
        let contentFooterElement = document.querySelector('#map-content-footer');
        contentLeftElement.style.display = "inline-block";
        contentRightElement.style.display = "inline-block";
        contentFooterElement.style.display = "inline-block";

        document.querySelector('#travel-title').innerText = jsonInfo.title;
        document.querySelector('#travel-period').innerText = jsonInfo.periodStartDate + " ~ " + jsonInfo.periodEndDate;
        document.querySelector('#travel-description').innerText = jsonInfo.description;
        document.querySelector('#travel-foodInfo').innerText = jsonInfo.foodInfo;
        document.querySelector('#travel-placeInfo').innerText = jsonInfo.placeInfo;
        document.querySelector('#travel-lodgingInfo').innerText = jsonInfo.lodgingInfo;
        document.querySelector('#travel-transportInfo').innerText = jsonInfo.transportInfo;
        document.querySelector('#travel-cost').innerText = jsonInfo.cost;
        document.querySelector('#travel-totalReview').innerText = jsonInfo.totalReview;
        document.querySelector('#travel-memo').innerText = jsonInfo.memo;
        document.querySelector('#travel-openStatus').innerText = jsonInfo.openStatus;
        document.querySelector('#img-preview').src = jsonInfo.filePath;

        // 2. modal show
        const hiddenElement = document.querySelector("#hidden");
        hiddenElement.id = '';

        // 3. modal hidden 이벤트 등록
        document.querySelector("#cancel-btn").addEventListener('click', ()=> {
            hiddenElement.id = 'hidden';
        });
        document.querySelector("#modal-overlay").addEventListener('click', ()=> {
            hiddenElement.id = 'hidden';
        });
    },
    
};

mymapObj.init();
