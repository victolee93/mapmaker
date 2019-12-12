
ajaxUtil = {
    // ajax call
    call : async function(url) {
        let response = await fetch(url);
        return response.json();
    }
};