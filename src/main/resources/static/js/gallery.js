var writeBtn = document.getElementById('write-btn');
var detailBtn = document.querySelector('.detail-btn');

var openWriteModal = function(){
    $('.ui.modal.write').modal('show');
};

var openDetailModal = function(){
    $('.ui.modal.detail').modal('show');
};

writeBtn.addEventListener('click', openWriteModal);
detailBtn.addEventListener('click', openDetailModal);