function getPositions() {
    let criteria = new Criteria();

    sendRequest("GET", "positions?" + $.param(criteria), null, responseHandler);
}

function Criteria() {

    let idTerminal = $('#terminalID').val().trim();
    if (idTerminal.length > 0) {
        this.idTerminal = idTerminal;
    }

    let startDate = $('#startDate').val().trim();
    if (startDate.length > 0) {
        this.startDate = startDate;
    }

    let endDate = $('#endDate').val().trim();
    if (endDate.length > 0) {
        this.endDate = endDate;
    }
}


function responseHandler( jqXHR, status, data)
{

    if(jqXHR.status === 200)
    {
        for (let i=0; i < data.length; i++) {
            addStaticMarker({
                lat: data[i].latitude,
                lng: data[i].longitude
            });
        }
    }
    else
    {
        alert('ERROR - ERROR CODE ' + jqXHR.status);
    }

}



