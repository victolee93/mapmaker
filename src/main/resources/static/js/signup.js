
const signupObj = {
    init : () => {
        document.querySelector('#submit-btn').addEventListener('click', (event) => {
            let passwd = document.querySelector('input[name=password]');
            let passwdConfirm = document.querySelector('input[name=password-confirm]');

            // 비밀번호 재확인 실패
            if (validatorObj.passwordConfirm(passwd.value, passwdConfirm.value) === false) {
                passwd.value = '';
                passwdConfirm.value = '';

                alert('비밀번호 재확인이 일치하지 않습니다.');
                event.preventDefault();
                return false;
            }

            this.closest("form").submit();
        })
    }
};

signupObj.init();