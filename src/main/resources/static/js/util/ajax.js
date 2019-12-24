
ajaxUtil = {
    // GET 요청
    GETCall : async (url) => {
        let response = await fetch(url);
        return response.json();
    },

    // POST 요청
    POSTCall : async (url, formData) => {
        let response = await fetch(url, {
            body: formData,
            method: "POST",
        });

        return response.json();
    },
};