var declare = declare || {};
declare.getProvince = function(nationalId, provinceSelect) {
    $.ajax(
        {
            url: urlRoot + "location/province/" + nationalId ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(function (data) {
        provinceSelect.html("");
        $.each(data,
            function (index, province) {
                provinceSelect.append(
                    "<option value='" + province.id +"'>" + province.name +"</option>"
                );

            });
    });
}
declare.getProvinceForLocation = function(provinceSelect, districtSelect, wardSelect) {
    $.ajax(
        {
            url: urlRoot + "location/province/" + vietNamId ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(function (data) {
        provinceSelect.html("");
        $.each(data,
            function (index, province) {
                provinceSelect.append(
                    "<option value='" + province.id +"'>" + province.name +"</option>"
                );
            });
            declare.getDistrictForLocation(provinceSelect.val(), districtSelect, wardSelect);

    });
}
declare.getDistrictForLocation = function(provinceId, districtSelect, wardSelect){
    $.ajax(
        {
            url: urlRoot + "location/district/" + provinceId ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(function (data) {
        districtSelect.html("");
        $.each(data,
            function (index, province) {
                districtSelect.append(
                    "<option value='" + province.id +"'>" + province.name +"</option>"
                );
            });
        declare.getWard(districtSelect.val(), wardSelect);
    });
}

declare.getDistrict = function(provinceId, districtSelect) {
    $.ajax(
        {
            url: urlRoot + "/location/district/" + provinceId ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(function (data) {
        districtSelect.html("");
        $.each(data,
            function (index, province) {
                districtSelect.append(
                    "<option value='" + province.id +"'>" + province.name +"</option>"
                );

            });
    });
}

declare.getWard = function(districtId, wardSelect) {
    $.ajax(
        {
            url: urlRoot + "location/ward/" + districtId ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(function (data) {
        wardSelect.html("");
        $.each(data,
            function (index, province) {
                wardSelect.append(
                    "<option value='" + province.id +"'>" + province.name +"</option>"
                );

            });
    });
}

declare.checkPassport = function (passport, infoInput) {
    $.ajax(
        {
            url: urlRoot + "person/check/" + passport ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(
        function (data) {
            infoInput.hide();
        }
    ).fail(
        function () {
            infoInput.show();
        }
    );
}

// declare.getTransportNo = function (transportNo, transportNoInput) {
//     $.ajax(
//         {
//             url: urlRoot + "transport/getByType/" + transportNo ,
//             method: 'GET',
//             dataType: 'json',
//             contentType: 'application/json'
//         }
//     ).done(
//         function (data) {
//             transportNoInput.html();
//             $.each(data,
//                 function (index, valueTransportNo) {
//                     transportNoInput.append(
//                         "<option value='" + valueTransportNo.name +"'>" + valueTransportNo.name +"</option>"
//                     );
//                 });
//         }
//     );
// }

declare.createPerSon = function () {
    var person = value.getPerson();
    $.ajax({
        url: urlRoot + "declare/create_person/" + person.Nationality.id,
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(person)
    }).done(
        function (data) {
            var idPerson = data.passport;
            declare.createContact(idPerson);
            declare.createTransport(idPerson);
            console.log(idPerson);
        }
    );
}

declare.createContact = function (idPerson) {
    var contact = value.getContact();
    contact.person = idPerson;
    $.ajax({
        url: urlRoot + "declare/create_contact",
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(contact)
    }).done();
}

declare.createTransport = function(idPerson){
    var transport = value.getTransport();
    $.ajax({
        url: urlRoot + "declare/create_transport",
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(transport)
    }).done(
        function (data) {
            declare.createEntry(data.idTransport ,idPerson);
            console.log(data.idTransport);
        }
    );
}
declare.createEntry = function (idTransport, idPerson) {
    var entry = value.getEntry(idTransport, idPerson);
    $.ajax({
        url: urlRoot + "declare/create_entry",
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entry)
    }).done(
        function (data) {
            var idEntry = data.entryid;
            console.log(data.messenger);
            declare.entryStatus(idEntry);
            declare.entryExposure(idEntry);
        }
    );
}

declare.entryStatus = function (idEntry) {
    var listStatus = value.getStatuses();
    $.ajax({
        url: urlRoot + "declare/entry_symptom/" + idEntry,
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(listStatus)
    }).always(
        function () {
            console.log("Khai Báo Sức Khỏe Hoàn Tất");
        }
    );
}
declare.entryExposure = function (idEntry) {
    var historyOfExposure = value.getHistoryOfExposure();
    $.ajax({
        url: urlRoot + "declare/entry_exposure/" + idEntry,
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(historyOfExposure)
    }).always(
        function () {
            console.log("Khai Báo Tiếp Xúc Hoàn Tất");
        }
    );
}