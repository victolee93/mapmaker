
mapDetailObj = {
    init: () => {
        // display 초기화
        mapDetailObj.setDisplay();
    },

    /*
     * 카드 개수에 따라 컨테이너의 height를 조정
     */
    setDisplay : () => {
        let originHeight = document.querySelector('#map-search-container').offsetHeight;
        let cardRowCount = Math.ceil(document.querySelectorAll('.card').length / 3);

        // 새로운 높이 = 원래 높이 + 카드라인수 * 250px
        let newHeight = (originHeight + (cardRowCount * 300)) + "px";
        document.querySelector('#map-search-container').style.height = newHeight;
    },
};

mapDetailObj.init();