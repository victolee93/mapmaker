var writeBtn = document.querySelector('#board-write-btn');
var updateBtn = document.querySelector('#board-update-btn');

var writeSubmitBtn = document.querySelector('#write-submit-btn');
var writeCancelBtn = document.querySelector('#write-cancel-btn');

var updateSubmitBtn = document.querySelector('#update-submit-btn');
var updateCancelBtn = document.querySelector('#update-cancel-btn');

/*
 * 함수 정의
 */
var openWriteModal = function() {
    $('.ui.modal.write').modal('show');
};

var openUpdateModal = function() {
    $('.ui.modal.update').modal('show');

    document.querySelector('[name="title"]').value = title;
    document.querySelector('[name="content"]').value = content;
};

var writeFormSubmit = function() {
    document.querySelector('#write-form').submit();
};

var writeFormCancel = function() {
    $('.ui.modal.write').modal('hide');
};

var updateSubmit = function() {
    document.querySelector('#update-form').submit();
};

var updateCancel = function() {
    $('.ui.modal.update').modal('hide');
};


/*
 * 이벤트 등록
 */
if (writeBtn) {
    writeBtn.addEventListener('click', openWriteModal);
}

if (updateBtn) {
    updateBtn.addEventListener('click', openUpdateModal);
}

if (writeSubmitBtn) {
    writeSubmitBtn.addEventListener('click', writeFormSubmit);
}

if (writeCancelBtn) {
    writeCancelBtn.addEventListener('click', writeFormCancel);
}

if (updateSubmitBtn) {
    updateSubmitBtn.addEventListener('click', updateSubmit);
}

if (updateCancelBtn) {
    updateCancelBtn.addEventListener('click', updateCancel);
}