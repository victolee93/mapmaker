
ajaxUtil = {
    call : async function(url) {
        fetch(url)
        .then(response => response.json())
        .then( response => {
                return JSON.stringify(response);
            }
        )
    }
};