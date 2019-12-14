
const signupObj = {
    init : () => {
        document.querySelector('#submit-btn').addEventListener('click', (event) => {
            if (validatorObj.passwordConfirm() === false) {
                event.preventDefault();
                alert('비밀번호 재확인이 일치하지 않습니다.');
                return false;
            }

            this.closest("form").submit();
        })
    }
};

const validatorObj = {
    passwordConfirm : () => {
        let passwd = document.querySelector('input[name=password]');
        let passwdConfirm = document.querySelector('input[name=password-confirm]');

        if (passwd.value != passwdConfirm.value) {
            passwd.value = '';
            passwdConfirm.value = '';
            return false;
        }

        return true;
    }
};

signupObj.init();