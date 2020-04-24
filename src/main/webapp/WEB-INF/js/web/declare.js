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
