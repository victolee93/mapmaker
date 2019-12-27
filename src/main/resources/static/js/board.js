
boardObj = {
    
    init : () => {
        boardObj.modalEventRegister();

        boardObj.like();

        boardObj.submit();
    },

    /*
     * 모달 이벤트 등록
     */
    modalEventRegister : () => {
        const writeHiddenElement = document.querySelector(".hidden");

        // modal show
        document.querySelector("#write-btn").addEventListener("click", () => {
            writeHiddenElement.classList.remove('hidden');

            if (validatorObj.isEmpty(title) === false && validatorObj.isEmpty(content) === false) {
                document.querySelector('[name="title"]').value = title;
                document.querySelector('[name="content"]').value = content;
            }
        });

        // modal hidden
        document.querySelector("#cancel-btn").addEventListener('click', ()=> {
            writeHiddenElement.classList.add('hidden');
        });
        document.querySelector(".modal_overlay").addEventListener('click', ()=> {
            writeHiddenElement.classList.add('hidden');
        });
    },

    /*
     * 좋아요 버튼
     */
    like : () => {
        let likeBtnElement = document.querySelector("#like-btn");

        if (likeBtnElement) {
         likeBtnElement.addEventListener('click', (event)=> {
             if (event.target.classList.contains('checked')) {
                 alert('이미 좋아요를 눌렀습니다.')
                 return false;
             }

             let board_id = event.target.getAttribute('value');
             let url = 'http://localhost:8080/board/' + board_id + '/like';
             ajaxUtil.GETCall(url)
                 .then( () => {
                     event.target.classList.remove('outline');
                     event.target.classList.add('checked');
                 });
         });
        }
    },

    /*
     * form 전송
     */
    submit : () => {
        document.querySelector("#submit-btn").addEventListener("click", () => {
            this.closest('form').submit();
        })
    }
};

boardObj.init();