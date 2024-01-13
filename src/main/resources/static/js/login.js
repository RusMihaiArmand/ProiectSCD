function login(username, pass) {
    const loginInfo = {
        username: username,
        password: pass
    };
    const loginInfo2 = JSON.stringify(loginInfo);
    sendRequest('POST','login',loginInfo2,responseHandler);
}


function responseHandler(jqXHR, status, data)
{
    if(jqXHR.status === 200)
    {
        localStorage.setItem('bearerToken', data.token);
        window.location.href = 'map.html';
    }
    else
    {
        alert('LOGIN ERROR - INVALID CREDENTIALS');
    }

}

