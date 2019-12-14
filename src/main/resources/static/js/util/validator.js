
const validatorObj = {
    passwordConfirm : (passwd, passwdConfirm) => {
        return passwd === passwdConfirm;
    },

    isEmpty : (val) => {
        return (val === null || val === '');
    }
};