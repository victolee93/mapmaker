
boardObj = {
    
    init : () => {
        boardObj.modalEventRegister();

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
     * form 전송
     */
    submit : () => {
        document.querySelector("#submit-btn").addEventListener("click", () => {
            this.closest('form').submit();
        })
    }
};

boardObj.init();