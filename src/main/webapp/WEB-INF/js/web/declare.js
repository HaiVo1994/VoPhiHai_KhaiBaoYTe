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
            url: urlRoot + "location/province/" + vietnamId ,
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

declare.checkPassport = function () {
    var infoInput = $("#person-info"),
        passport = $("#person_legalDocument").val(),
        messengerLocation = $("#passport_messenger"),
        check = true;
    $.ajax(
        {
            url: urlRoot + "person/check/" + passport ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        }
    ).done(
        function (data) {
            messengerLocation.html("<p>Mã " + passport + "Đã Có Trong Hệ Thống </p>")
            $("#person_name").val(data.name);
            $("#person_birthYear").val(data.birthYear);
            $("#person_birthYear").find("option[value=" + data.birthYear + "]").attr('selected','selected');
            $("#person_gender").val(data.gender);
            $("#person_gender").find("option[value=" + data.gender + "]").attr('selected','selected');
            $("#person_nationality").val(data.Nationality.id);
            $("#person_nationality").find("option[value=" + data.Nationality.id + "]").attr('selected','selected');
        }
    ).fail(
        function () {
            messengerLocation.html("<p>Mã " + passport + "Chưa Có Trong Hệ Thống </p>");
        }
    );
    return check;
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
// declare.checkDataEntered = function(checkPassport){
//     var check = true,
//         messengerError = "<h4>Vui lòng</h4>",
//         foundPassport = false;
//     if($("#entry_gate").val()==""){
//         messengerError += "<p>Chọn cửa khẩu</p>";
//         check = false;
//     }
//     var passport = $("#person_legalDocument").val();
//     if (passport==null){
//         messengerError += "<p>Nhập số hộ chiếu hoặc giấy thông hành hợp pháp khác</p>";
//         check = false;
//     }
//     else {
//         if (!checkPassport){
//             foundPassport = declare.checkPassport(passport,$("#passport_messenger"));
//         }
//         else {
//             foundPassport = true;
//         }
//     }
//     if (!foundPassport){
//         if ($("#person_name").val() === ""){
//             messengerError += "<p>Nhập họ tên</p>";
//             check = false;
//         }
//         if ($("#person_birthYear").val() === ""){
//             messengerError += "<p>Chọn năm sinh</p>";
//             check = false;
//         }
//         if ($("#person_gender").val() === ""){
//             messengerError += "<p>Chọn giới tính</p>";
//             check = false;
//         }
//         if ($("#person_nationality").val() === ""){
//             messengerError += "<p>Chọn quốc tịch</p>";
//             check = false;
//         }
//     }
//
//     var typeTransport = $("#transportType").val();
//     if (typeTransport == flyTypeId){
//         if ($("#transport_transportationNo").val()==null){
//             messengerError += "<p>Nhập số hiệu của máy bay</p>";
//             check = false;
//         }
//         if ($("#entry_seatNo").val()==null){
//             messengerError += "<p>Nhập số ghế của bạn trên máy bay</p>";
//             check = false;
//         }
//     }
//
//     if ($("#entry_departureDate").val() == ""){
//         messengerError += "<p>Nhập ngày khởi hành</p>";
//         check = false;
//     }
//     if ($("#entry_departureDate").val() == ""){
//         messengerError += "<p>Nhập ngày nhập cảnh</p>";
//         check = false;
//     }
//
//     if ($("#entry_provinceDeparture").val() == ""){
//         messengerError += "<p>Chọn địa điểm khởi hành</p>";
//         check = false;
//     }
//     if ($("#national_destination").val() == ""){
//         messengerError += "<p>Chọn địa điểm nơi đến </p>";
//         check = false;
//     }
//
//     if ($("#entry_placeTravel").val() == ""){
//         messengerError += "<p>Nhập những nơi bạn đã đi qua gần đây </p>";
//         check = false;
//     }
//     if ($("#contact_Location").val() == ""){
//         messengerError += "<p>Nhập địa chỉ liên lạc của bạn tại Việt Nam </p>";
//         check = false;
//     }
//     else {
//         if ($("#contact_address").val() == ""){
//             messengerError += "<p>Nhập địa chỉ liên lạc của bạn tại Việt Nam </p>";
//             check = false;
//         }
//     }
//     if ($("#contact_phone").val() == ""){
//         messengerError += "<p>Nhập số điện thoại của bạn tại Việt Nam </p>";
//         check = false;
//     }
//
//     if (value.getStatuses()==null){
//         messengerError += "<p>Hoàn thành khảo sát sức khỏe </p>";
//         check = false;
//     }
//     if (value.getHistoryOfExposure()==null){
//         messengerError += "<p>Hoàn thành khảo sát lây nhiễm </p>";
//         check = false;
//     }
//
//     if (!check){
//         $("#modal_messenger_error").html(messengerError);
//         $("#messengerErrorModal").modal("show");
//     }
//     return check;
// }

declare.setValidate = function(){
    $("#formDeclare").validate();
    $("#person_legalDocument").rules("add",
        {
            required: true,
            maxlength: 15,
            minlength: 9,
            messages:{
                required: "Nhập số hộ chiếu hoặc giấy thông hành hợp pháp khác",
                maxlength: "Quá dài, bạn chắc bạn đang nhập thông tin thông hành chứ?",
                minlength: "Bạn đang nhập số nhà phải không? Vì nó ngắn quá"
            }
        }
        );
    $("#person_name").rules("add",
        {
            required:true,
            maxlength: 200,
            messages: {
                required: "Bạn phải nhập tên của mình",
                maxlength:"Tên của bạn quá dài, vui lòng rút gọn lại"
            }
        }
        );
    $("#transport_transportationNo").rules("add",
        {
            required: true,
            maxlength: 72,
            messages: {
                required: "Vui lòng nhập mã chuyến bay của bạn",
                maxlength: "Hãy xem lại mã số chuyến bay"
            }
        }
        );
    $("#entry_seatNo").rules("add",
        {
            required:true,
           maxlength:32,
           messages:{
               required: "Vui lòng nhập số ghế của bạn",
               maxlength: "Hãy nhập đúng số ghế của bạn"
           }
        });

    // jQuery.validator.addMethod("startDate",
    //     function (value, element, params) {
    //         if (!/Invalid|NaN/.test(new Date(value))) {
    //             return new Date(value) <= new Date($(params).val());
    //         }
    //         return isNaN(value) && isNaN($(params).val())
    //             || (Number(value) <= Number($(params).val()));
    //     },"Ngày xuất phát phải trước hoặc giống ngày nhập cảnh");
    $("#entry_departureDate").rules("add",
        {
            required:true,
            dateSelect:true,
            messages:{
                required: "Bạn phải nhập ngày xuất phát",
                dateSelect: "Ngày Xuất Phát Phải Là Ngày Hôm Nay Hoặc Trước Đó"
            }
        });

    $("#entry_immigrationDate").rules("add",
        {
            required:true,
            dateSelect: true,
            endStatisticalDate: $("#entry_departureDate"),
            messages:{
                required: "Bạn phải nhập ngày nhập cảnh",
                dateSelect: "Ngày nhập cảnh phải là ngày hôm nay hoặc trước đó",
                endStatisticalDate: "Ngày Nhập Cảnh Phải Sau Hoặc Giống Với Ngày Xuất Phát"
            }
        }
        );
    $("#contact_address").rules("add",
        {
            required: true,
            maxlength: 240,
            messages:{
                required: "Để tiện liên lạc, vui lòng nhập địa chỉ",
                maxlength: "Vui lòng rút gọn địa chỉ"
            }
        }
        );
    jQuery.validator.addMethod("phoneNumber",
        function (value, element) {
            return phoneReg.test(value);
        },"Vui lòng nhập số điện thoại ở Việt Nam");
    $("#contact_phone").rules("add",
        {
            required: true,
            number: true,
            minlength: 10,
            maxlength: 11,
            // phoneNumber: true,
            messages:{
                required: "Vui Lòng Nhập Số Điện Thoại",
                number: "Số điện thoại chỉ chứa số",
                minlength: "Số điện thoại chỉ có 10-11 số",
                maxlength: "Số điện thoại chỉ có 10-11 số",
                // phoneNumber: "Vui lòng nhập số điện thoại ở Việt Nam"
            }
        }
        );
    $("#contact_email").rules("add",
        {
            // required: true,
            email:true,
            messages:{
                // required: "Vui lòng nhập email",
                email: "Email sai, vui lòng nhập lại"
            }
        }
        );

}
declare.transportTypeChange = function(){
    var transportNo = $("#transport_transportationNo"),
        seatNo = $("#entry_seatNo");
    if ($("#transportType").val() === flyTypeId) {
        transportNo.rules("add", "required");
        seatNo.rules("add", "required");
    }
    else {
        transportNo.rules("remove", "required");
        seatNo.rules("remove", "required");
    }
}
declare.dataValidate = function(){
    var formSelect = $("#formDeclare");

    if (formSelect.valid()) {
        var messengerError = "Vui Lòng";
        var check =true;
        if (value.getStatuses() == null) {
            messengerError += "<p>Hoàn thành khảo sát sức khỏe </p>";
            check = false;
        }
        if (value.getHistoryOfExposure() == null) {
            messengerError += "<p>Hoàn thành khảo sát lây nhiễm </p>";
            check = false;
        }

        if (!check) {
            $("#modal_messenger_error").html(messengerError);
            $("#messengerErrorModal").modal("show");
        }
        else {
            // declare.createPerSon();
            declare.sendDeclare();
        }
    } else {
        $("#modal_messenger_error").html("Thông Tin Thiếu");
        $("#messengerErrorModal").modal("show");
    }
}
declare.sendDeclare = function () {
    var declareData = value.getDeclare();
    $.ajax({
        url: urlRoot + "declare/sendDeclare",
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(declareData)
    }).done(
        function (data) {
            console.log(data.messenger);
            $("#messengerSuccess").modal("show");
        }
    ).fail(
        function (data) {
            console.log(data.messenger);
        }
    );
}
// declare.createPerSon = function () {
//     var person = value.getPerson();
//     $.ajax({
//         url: urlRoot + "declare/create_person/" + person.Nationality.id,
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify(person)
//     }).done(
//         function (data) {
//             var idPerson = data.passport + "";
//             declare.createContact(idPerson);
//             declare.createTransport(idPerson);
//             console.log(idPerson);
//         }
//     );
// }
//
// declare.createContact = function (idPerson) {
//     var contact = value.getContact();
//     contact.person = idPerson;
//     $.ajax({
//         url: urlRoot + "declare/create_contact",
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify(contact)
//     }).done();
// }
//
// declare.createTransport = function(idPerson){
//     var transport = value.getTransport();
//     $.ajax({
//         url: urlRoot + "declare/create_transport",
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify(transport)
//     }).done(
//         function (data) {
//             declare.createEntry(data.idTransport ,idPerson);
//             console.log(data.idTransport);
//         }
//     );
// }
// declare.createEntry = function (idTransport, idPerson) {
//     var entry = value.getEntry(idTransport, idPerson);
//     $.ajax({
//         url: urlRoot + "declare/create_entry",
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify(entry)
//     }).done(
//         function (data) {
//             var idEntry = data.entryid;
//             console.log(data.messenger);
//             declare.entryStatus(idEntry);
//             declare.entryExposure(idEntry);
//             // $("#messengerResult").html("Bạn Đã Hoàn Thành Việc Khai Báo Y Tế");
//             $("#messengerSuccess").modal("show");
//         }
//     );
// }
//
// declare.entryStatus = function (idEntry) {
//     var listStatus = value.getStatuses();
//     $.ajax({
//         url: urlRoot + "declare/entry_symptom/" + idEntry,
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify(listStatus)
//     }).always(
//         function () {
//             console.log("Khai Báo Sức Khỏe Hoàn Tất");
//         }
//     );
// }
// declare.entryExposure = function (idEntry) {
//     var historyOfExposure = value.getHistoryOfExposure();
//     $.ajax({
//         url: urlRoot + "declare/entry_exposure/" + idEntry,
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify(historyOfExposure)
//     }).always(
//         function () {
//             console.log("Khai Báo Tiếp Xúc Hoàn Tất");
//         }
//     );
// }