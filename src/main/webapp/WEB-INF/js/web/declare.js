class Declare {
    getProvince(nationalId, provinceSelect) {
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
    getProvinceForLocation(provinceSelect, districtSelect, wardSelect) {
        var thisClass = this;
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
                thisClass.getDistrictForLocation(provinceSelect.val(), districtSelect, wardSelect);

        });
    }
    getDistrictForLocation(provinceId, districtSelect, wardSelect){
        var thisClass = this;
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
            thisClass.getWard(districtSelect.val(), wardSelect);
        });
    }

    getDistrict(provinceId, districtSelect) {
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

    getWard(districtId, wardSelect) {
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
}
