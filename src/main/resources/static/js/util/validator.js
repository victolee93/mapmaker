
const validatorObj = {
    passwordConfirm : (passwd, passwdConfirm) => {
        return (passwd == passwdConfirm) ? true : false;
    },

    isEmpty : (val) => {
        return (val === null || val === '') ? true : false;
    }
};