
galleryObj = {
    init : () => {
        galleryObj.writeModalEventRegister();

        galleryObj.detailModalEventRegister();

        galleryObj.like();

        galleryObj.fileUpload();

        galleryObj.gallerySubmit();

        galleryObj.galleryReplySubmit();
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
                ajaxUtil.GETCall(url)
                    .then( (res) => {
                        document.querySelector("#gallery-info-desc").innerText = res.content;
                        document.querySelector("#detail-img").src = res.filePath;
                        // 댓글 등록시 호출되는 URL
                        document.querySelector("#reply-url").value = '/gallery/' + galley_id + '/comment';

                        // 댓글 리스트
                        let commentBoxElement = document.querySelector("#comment-box");
                        res.galleryCommentEntityList.forEach( (reply) => {
                            let replyContentDiv = galleryObj.createReplyContent(reply);
                            commentBoxElement.appendChild(replyContentDiv);
                        });
                    });
            });
        });

        document.querySelector("#detail-modal-overlay").addEventListener('click', ()=> {
            detailHiddenElement.id = 'detail-hidden';

            let commentBoxElement = document.querySelector("#comment-box");
            commentBoxElement.innerText = '';   // 댓글 목록 초기화
        });
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
                        alert('이미 좋아요를 눌렀습니다.')
                        return false;
                    }

                    let galley_id = event.target.getAttribute('value');
                    let url = 'http://localhost:8080/gallery/' + galley_id + '/like';
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
     * 갤러리 글쓰기 form 전송
     */
    gallerySubmit : () => {
        document.querySelector("#submit-btn").addEventListener("click", () => {
            this.closest('form').submit();
        })
    },

    /*
     * 댓글 form 전송
     */
    galleryReplySubmit : () => {
        document.querySelector("#comment-write-btn").addEventListener("click", (e) => {
            let url = document.querySelector("#reply-url").value;
            let contentTextAreaElement = document.querySelector("#reply-text > textarea[name=content]");
            let csrfTokenElement = document.querySelector("#csrf_token");

            let formData = new FormData();
            formData.append('content', contentTextAreaElement.value);
            formData.append(csrfTokenElement.name, csrfTokenElement.value);

            ajaxUtil.POSTCall(url, formData)
                .then((res) => {
                    // textarea 초기화
                    contentTextAreaElement.value = '';

                    // 댓글 내용 엘리먼트 작성하여 추가
                    let replyContentDiv = galleryObj.createReplyContent(res);
                    document.querySelector("#comment-box").appendChild(replyContentDiv);
                });
        })
    },

    /*
     * 댓글 element 내용 채우기
     */
    createReplyContent : (reply) => {
        let replyContent = `
            <span class="author">${reply.username}</span>
            <div class="metadata">
                <span class="date">${reply.date}</span>
            </div>
    
            <div class="text">
                ${reply.content}
            </div>
            `

        let replyContentDiv = document.createElement('div');
        replyContentDiv.setAttribute("class", "content");
        replyContentDiv.innerHTML = replyContent;

        return replyContentDiv;
    }
};

galleryObj.init();
