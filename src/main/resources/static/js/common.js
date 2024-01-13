function sendRequest(type, resource, data, responseHandler) {
    let logInToken =localStorage.getItem('bearerToken');
    jQuery.ajax({
        type: type,
        url: "http://localhost:8081/" + resource,
        data: data,
        dataType: "json",
        accepts: "application/json",
        contentType: "application/json",
        headers:{"Authorization" : "Bearer " + logInToken},


        success: function (data, status, jqXHR) {
            responseHandler(jqXHR,status,data);
        },
        error: function (jqXHR, status, error) {
            responseHandler(jqXHR,status,error);
        }


    });
}

function goToPage(url) {
    $(location).attr('href', url);
}