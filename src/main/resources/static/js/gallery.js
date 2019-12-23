
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
        let reader = new FileReader();

        reader.onload = (readerEvent) => {
            document.querySelector("#img-preview").setAttribute('src', readerEvent.target.result);
        };

        document.querySelector("#ex_filename").addEventListener("change", (changeEvent) => {
            let imgFile = changeEvent.target.files[0];
            document.querySelector("#upload-name").value = imgFile.name;

            reader.readAsDataURL(imgFile);
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
