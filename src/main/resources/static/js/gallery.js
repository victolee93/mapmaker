
galleryObj = {
    init : () => {
        galleryObj.modalEventRegister();

        galleryObj.fileUpload();

        galleryObj.submit();
    },
    
    /*
     * 모달 이벤트 등록
     */
    modalEventRegister : () => {
        const writeHiddenElement = document.querySelector(".write_hidden");

        // show
        document.querySelector("#write-btn").addEventListener('click', ()=> {
            writeHiddenElement.classList.remove('write_hidden');
        });

        // hidden
        document.querySelector("#cancel-btn").addEventListener('click', ()=> {
            writeHiddenElement.classList.add('write_hidden');
        });
        document.querySelector(".modal_overlay").addEventListener('click', ()=> {
            writeHiddenElement.classList.add('write_hidden');
        });
    },

    /*
     * 파일 업로드 처리
     */
    fileUpload : () => {
        document.querySelector("#ex_filename").addEventListener("change", (event) => {
            let filename = event.target.files[0].name;;
            document.querySelector("#upload-name").value = filename;
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

galleryObj.init();
