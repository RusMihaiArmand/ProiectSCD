function createTerminal(idT, nameT) {
    const info = {
        id: idT,
        terminalName: nameT
    };

    const info2 = JSON.stringify(info);

    console.log(info2);
    sendRequest('POST', 'terminals', info2, responseHandler);
}

function deleteTerminal(id) {
    sendRequest('DELETE', 'terminals/' + id, null, responseHandler);
}


function responseHandler( jqXHR, status, data)
{

    if(jqXHR.status === 201)
    {
        alert('TERMINAL CREATED SUCCESSFULLY');
    }
    else
    {
        if(jqXHR.status === 204)
        {
            alert('TERMINAL HAS BEEN DELETED');
        }
        else
        {
            alert('ERROR - CANNOT PROCESS REQUEST');
        }
    }

}

