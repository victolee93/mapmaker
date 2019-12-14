
const signupObj = {
    init : () => {
        document.querySelector('#submit-btn').addEventListener('click', (event) => {
            // 유효성 체크
            if (signupObj.validationCheck() === false) {
                event.preventDefault();
                return false;
            }

            this.closest('form').submit();
        })
    },

    /*
     * 입력값에 대한 유효성을 체크한다.
     */
    validationCheck : () => {
        // 1. 필수 값 체크
        let email = document.querySelector('input[name=email]').value;
        let isEmailEmpty = validatorObj.isEmpty(email);

        let nickname = document.querySelector('input[name=nickname]').value;
        let isNicknameEmpty = validatorObj.isEmpty(nickname);

        if (isEmailEmpty === true || isNicknameEmpty === true) {
            if (isEmailEmpty === true) {
                alert('이메일을 입력해주세요.');
            } else if (isNicknameEmpty === true) {
                alert('닉네임을 입력해주세요.');
            }
            return false;
        }

        // 2. 비밀번호 재확인 체크
        let passwd = document.querySelector('input[name=password]');
        let passwdConfirm = document.querySelector('input[name=password-confirm]');
        if (validatorObj.passwordConfirm(passwd.value, passwdConfirm.value) === false) {
            passwd.value = '';
            passwdConfirm.value = '';

            alert('비밀번호 재확인이 일치하지 않습니다.');
            return false;
        }

        return true;
    }
};

signupObj.init();