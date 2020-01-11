
mapObj = {
    init : () => {
        mapObj.like();
    },

    /*
     * 좋아요 버튼
     */
    like : () => {
        let likeBtnElement = document.querySelectorAll(".like-btn");

        if (likeBtnElement) {
            likeBtnElement.forEach((item) => {
                item.addEventListener('click', (event)=> {
                    event.stopPropagation();

                    if (item.classList.contains('checked')) {
                        alert('이미 좋아요를 눌렀습니다.');
                        return false;
                    }

                    let travelId = event.target.getAttribute('value');
                    let url = 'http://localhost:8080/map/' + travelId + '/like';
                    ajaxUtil.GETCall(url)
                        .then( () => {
                            item.classList.remove('outline');
                            item.classList.add('checked');

                            // 현재 좋아요 + 1
                            item.nextElementSibling.innerText = parseInt(item.nextElementSibling.innerText) + 1;
                        });
                });
            })
        }
    },
};

mapObj.init();