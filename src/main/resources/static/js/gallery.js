
galleryObj = {
    init : () => {
        galleryObj.writeModalEventRegister();

        galleryObj.detailModalEventRegister();

        galleryObj.fileUpload();

        galleryObj.submit();
    },
    
    /*
     * 글쓰기 모달 이벤트 등록
     */
    writeModalEventRegister : () => {
        const writeHiddenElement = document.querySelector("#write-hidden");

        // show
        document.querySelector("#write-btn").addEventListener('click', ()=> {
            writeHiddenElement.id = '';
        });

        // hidden
        document.querySelector("#write-cancel-btn").addEventListener('click', ()=> {
            writeHiddenElement.id = 'write-hidden';
        });

        document.querySelector("#write-modal-overlay").addEventListener('click', ()=> {
            writeHiddenElement.id = 'write-hidden';
        });
    },

    /*
     * 디테일 모달 이벤트 등록
     */
    detailModalEventRegister : () => {
        const detailHiddenElement = document.querySelector("#detail-hidden");

        // show
        document.querySelectorAll(".detail-btn").forEach((item) => {
            item.addEventListener('click', ()=> {
                detailHiddenElement.id = '';

                let galley_id = item.getAttribute('value');
                let url = 'http://localhost:8080/gallery/' + galley_id;
                ajaxUtil.call(url)
                    .then( (res) => {
                        document.querySelector("#gallery-info-title").innerText = res.title;
                        document.querySelector("#gallery-info-desc").innerText = res.content;
                        document.querySelector("#detail-img").src = res.filePath;
                    });
            });
        });

        // hidden
        document.querySelector("#detail-cancel-btn").addEventListener('click', ()=> {
            detailHiddenElement.id = 'detail-hidden';
        });

        document.querySelector("#detail-modal-overlay").addEventListener('click', ()=> {
            detailHiddenElement.id = 'detail-hidden';
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
