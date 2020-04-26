var value = value || {};
value.getEntry = function(transportId, personId) {
    transportId += "";
    personId += "";
    var placeTravel = "",
        inputPlaceList = $("#entry_placeTravel").val(),
        size = inputPlaceList.length;
    for (var i = 0; i< size; i++){
        placeTravel += inputPlaceList[i];
        if (i<size-1){
            placeTravel += ", ";
        }
    }
    return {
        "gate": $("#entry_gate").val(),
        "transport": transportId,
        "seatNo": $("#entry_seatNo").val(),
        "departureDate": $("#entry_departureDate").val(),
        "immigrationDate": $("#entry_immigrationDate").val(),
        "placeTravel": placeTravel,
        "provinceDeparture": $("#entry_provinceDeparture").val(),
        "provinceDestination": $("#entry_provinceDestination").val(),
        "person": personId,
        "contactHelpDeclare": null,
        "statuses": null,
        "listCure": $("#entry_listCure").val(),
        "historyOfExposures": null
    };
}
value.getPerson = function () {
    return {
        "legalDocument": $("#person_legalDocument").val(),
        "name": $("#person_name").val(),
        "birthYear": $("#person_birthYear").val(),
        "gender": $("#person_gender").val(),
        "Nationality": {
            "id": $("#person_nationality").val()
        }
    };
}
value.getContact = function() {
    var namePersonEntry = $("#person_name").val();
    return {
        "name": namePersonEntry,
        "person": null,
        "location": $("#contact_Location").val(),
        "address": $("#contact_address").val(),
        "phone": $("#contact_phone").val(),
        "email": $("#contact_email").val(),
        "create_by":namePersonEntry
    };
}
value.getTransport = function() {
    return {
        "transportType": $("#transportType").val(),
        "transportationNo": $("#transport_transportationNo").val(),
        "note": $("#transport_note").val()
    };
}
value.getStatuses = function() {
    var length = 0,
        idSymptom = 0,
        checkSymptom = 0,
        status = [];
    $(".symptom_checkbox").map(
        function( index ){
            if ($(this).prop("checked")){
                idSymptom = $(this).attr("name");
                checkSymptom = $(this).val();
                status.push(
                    {
                        "symptom": idSymptom,
                        "haveSymptom": checkSymptom
                    }
                );
            }
            length = index;
        }
    );
    if ( (length + 1) !== (status.length*2)){
        console.log("Khai Báo Thiếu Tình Trạng Sức Khỏe: " + status.length + "/" + (length/2));
        return null;
    }
    else{
        console.log("Khai Báo Đủ Tình Trạng Sức Khỏe");
        return status;
    }
}
value.getHistoryOfExposure = function() {
    var length = 0,
        idExposure = 0,
        checkExposure = 0,
        history_of_exposure = [];
    $(".exposure_checkbox").map(
        function( index ){
            if ($(this).prop("checked")){
                idExposure = $(this).attr("name");
                checkExposure = $(this).val();
                history_of_exposure.push(
                    {
                        "exposure":idExposure,
                        "hasExposure": checkExposure
                    }
                );
            }
            length = index;
        }
    );
    if ( (length + 1) !== (history_of_exposure.length*2)){
        console.log("Khai Báo Thiếu Lịch Sử Phơi Nhiễm:" + history_of_exposure.length + "/" + (length/2));
        return null;
    }
    else{
        console.log("Khai Báo Đủ Lịch Sử Phơi Nhiễm");
        return history_of_exposure;
    }
}