
const loginObj = {
    init : () => {
        document.querySelector('#login-btn').addEventListener('click', (event) => {
            let userName = document.querySelector('input[name=username]').value;
            let isUserNameEmpty = validatorObj.isEmpty(userName);

            let passwd = document.querySelector('input[name=password]').value;
            let isPasswdEmpty = validatorObj.isEmpty(passwd);

            // 이메일 또는 비밀번호가 빈 값일 경우
            if (isUserNameEmpty === true ||  isPasswdEmpty === true) {
                if (isUserNameEmpty === true) {
                    alert('이메일을 입력해주세요.');
                } else if (isPasswdEmpty === true) {
                    alert('비밀번호를 입력해주세요.');
                }
                event.preventDefault();
                return false;
            }

            event.target.closest('form').submit();
        })
    }
};


loginObj.init();