
galleryObj = {
    init : () => {
        galleryObj.modalEventRegister();

        galleryObj.submit();
    },
    
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

    submit : () => {
        document.querySelector("#submit-btn").addEventListener("click", () => {
            this.closest('form').submit();
        })
    }
};

galleryObj.init();
