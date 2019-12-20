
galleryObj = {
    init : () => {
        galleryObj.modalEventRegister()
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
    }
};

galleryObj.init();
