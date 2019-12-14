
const mapmakingObj = {
    map : null,             // 초기화된 kakao 지도
    markers : [],           // 마커 객체들
    markerPositions : [],   // 마커의 위도, 경도 정보를 담는 객체들 - form 데이터로 전달

    init : () => {
        // 지도 초기화
        mapmakingObj.map = mapmakingObj.mapInit();

        // 마킹 추가
        kakao.maps.event.addListener(mapmakingObj.map, 'click', mouseEvent => {
            mapmakingObj.addMark(mouseEvent);
        });

        // 마킹 초기화
        document.querySelector('#reset-btn').addEventListener('click', () => {
            mapmakingObj.hideMarkers();
        });

        // submit
        document.querySelector('#write-btn').addEventListener('click', (event) => {
            if (mapmakingObj.requireDataCheck() === false) {
                event.preventDefault();
                return false;
            }

            let makerPositionsElement = document.querySelector('#maker-positions')
            makerPositionsElement.setAttribute('value', JSON.stringify(mapmakingObj.markerPositions));
            this.closest('form').submit();
        });
    },

    /*
     *  마킹을 추가하고, 지도에 노출한다
     */
    addMark : mouseEvent => {
        // 위도, 경도
        let latLng = mouseEvent.latLng;

        // 마커 객체를 지도에 표시
        let marker = new kakao.maps.Marker({
            position: latLng
        });
        marker.setMap(mapmakingObj.map);
        mapmakingObj.markers.push(marker);

        // 위도 경도 정보를 form 데이터로 전송하기 위해, 객체를 만들어 markerPositions 변수에 추가
        let letLngObj = {
            "latitude" : latLng['Ga'],
            "longitude" : latLng['Ha']
        };
        mapmakingObj.markerPositions.push(letLngObj);
    },

    /*
     *  지도에서 모든 마커를 제거한다.
     */
    hideMarkers : () => {
        for (let i = 0; i < mapmakingObj.markers.length; i++) {
            mapmakingObj.markers[i].setMap(null);
        }

        mapmakingObj.markers = [];
        mapmakingObj.markerPositions = [];
    },


    /*
     *  지도를 초기화한다. ( 지도의 중심, 레벨 등 )
     */
    mapInit : () => {
        let container = document.querySelector('#map'); // 지도를 담을 영역

        // 지도 생성
        let options = {
            center: new kakao.maps.LatLng(37.531425, 127.006561),
            level: 8
        };
        return new kakao.maps.Map(container, options);
    },

    /*
     * 필수 입력 값의 유효성을 체크한다.
     */
    requireDataCheck : () => {
        let title = document.querySelector('input[name=title]').value;
        let isTitleEmpty = validatorObj.isEmpty(title);

        let description = document.querySelector('textarea[name=description]').value;
        let isDescriptionEmpty = validatorObj.isEmpty(description);

        let periodStartDate = document.querySelector('input[name=periodStartDate]').value;
        let isPeriodStartDateEmpty = validatorObj.isEmpty(periodStartDate);

        let periodEndDate = document.querySelector('input[name=periodEndDate]').value;
        let isPeriodEndDateEmpty = validatorObj.isEmpty(periodEndDate);

        // 필수 값이 빈 값인 경우
        if (isTitleEmpty === true
            || isPeriodStartDateEmpty === true
            || isPeriodEndDateEmpty === true
            || isDescriptionEmpty === true
        ) {
            if (isTitleEmpty === true) {
                alert('제목을 입력해주세요.');
            } else if (isPeriodStartDateEmpty === true || isPeriodEndDateEmpty === true) {
                alert('여행일을 입력해주세요.');
            } else if (isDescriptionEmpty === true) {
                alert('소개를 입력해주세요.');
            }
            return false;
        }

        return true;
    }
};

mapmakingObj.init();
