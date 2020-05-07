var statistical = statistical || {};

statistical.url = 0;
statistical.backgroundColor = [
    'rgba(255, 99, 132, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    'rgba(75, 192, 192, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(255, 159, 64, 0.2)',
    'rgba(188,187,55,0.2)',
    'rgba(108,143,240,0.2)',
    'rgba(240,57,84,0.2)',
    'rgba(223,49,240,0.2)'
];
statistical.borderColor = [
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    'rgba(75, 192, 192, 1)',
    'rgba(153, 102, 255, 1)',
    'rgba(255, 159, 64, 1)',
    'rgba(188,187,55,1)',
    'rgba(108,143,240,1)',
    'rgba(240,57,84,1)',
    'rgba(223,49,240,1)'
];
statistical.location = $("#locationOfChart");
statistical.chartCanvas = $("#statisticalChart");
statistical.chartObject = statistical.chartObject || {};
statistical.canvasValue = function () {
    // statistical.location.html("<canvas id=\"statisticalChart\" width=\"100%\" height=\"50\"></canvas>");
    if (statistical.chartObject instanceof Chart)
        statistical.chartObject.destroy();
};

statistical.dateRange = {
    begin:"",
    end:""
}

statistical.setDateRange = function(){
    var dateBegin = $("#beginDate"),
        dateEnd = $("#endDate");
    if (dateBegin.valid() && dateEnd.valid()){
        statistical.dateRange.begin = dateBegin.val();
        statistical.dateRange.end = dateEnd.val();
        return true;
    }
    return false;
}
statistical.getDate = function(dateNumber){
    var day = new Date(dateNumber);
    return day.getDate() + "/" + (day.getMonth() + 1) + "/" + day.getFullYear();
}
statistical.failAction = function () {
    // $("#locationOfChart").html("<p>Không Có Thống Kê</p>")
    $("#messengerErrorModal").modal("show");
}
statistical.setValidate = function(){

    $("#formStatistical").validate();
    $("#beginDate").rules("add",
        {
            required: true,
            dateSelect: true,
            messages:{
                required: "Bạn phải nhập ngày",
                dateSelect: "Ngày Được Chọn Phải Là Ngày Hôm Nay Hoặc Trước Đó"
            }
        }
        );
    $("#endDate").rules("add",
        {
            required: true,
            dateSelect: true,
            endStatisticalDate: $("#beginDate"),
            messages:{
                required: "Bạn phải nhập ngày",
                dateSelect: "Ngày Được Chọn Phải Là Ngày Hôm Nay Hoặc Trước Đó",
                endStatisticalDate: "Ngày Kết Thúc Phải Lớn Hơn Hoặc Bằng Ngày Bắt Đầu"
            }
        }
        );
}
statistical.getDateRangeText = function(){
    return "Từ " + statistical.dateRange.begin + " Đến " + statistical.dateRange.end;
}
statistical.symptomText = " Triệu Chứng";
statistical.symptomChartText = "Thống Kê Số Người Có Cùng Số Lượng Triệu Chứng ";
statistical.getSymptom = function () {
    if (statistical.setDateRange()){
        statistical.canvasValue();
        $.ajax(
            {
                url: urlRoot + "statistical_symptom/amount_people",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                // data:JSON.stringify(dateRange)
                data:JSON.stringify(statistical.dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [],
                    background = [],
                    border = [],
                    colorCount = 0;
                $.each(data,function (index,value) {
                    label.push(value.numberSymptom + statistical.symptomText);
                    dataValue.push(value.amountEntry);
                    background.push(statistical.backgroundColor[colorCount]);
                    border.push(statistical.borderColor[colorCount]);
                    colorCount ++;
                });

                var cxt = statistical.chartCanvas[0].getContext('2d');
                var chartSet = {
                    type: "pie",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "#" + statistical.symptomText,
                            data: dataValue,
                            backgroundColor: background,
                            borderColor: border,
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        },
                        title:{
                            display: true,
                            text: statistical.symptomChartText + statistical.getDateRangeText()
                        },
                        onClick: statistical.getSymptomList
                    }
                }
                statistical.chartObject = new Chart( cxt, chartSet);
                staticPart.changeSizeOfMain();
            }
        ).fail(
            function () {
                statistical.failAction();
            }
        );
    }

}

statistical.pageSize = 5;
statistical.modalForListPeople = $("#listPerson");
statistical.modalForListPeople_tittle = $("#listPerson_tittle");
statistical.tableHtml = "<table class='table'" +
    "   <thead>" +
    "       <tr>" +
    "           <th scope='col'>Tên</th>" +
    "           <th scope='col'>Ngày Nhập Cảnh</th>\n" +
    "           <th scope='col'><a href='Chi Tiết'></th>\n" +
    "       </tr>" +
    "   </thead>" +
    "   <tbody id='tableForListPerson_body'>" +
    "   </tbody>" +
    "</table>";
statistical.getSymptomList = function(event){
    var activePoints = statistical.chartObject.getElementsAtEvent(event),
        firstPoint = activePoints[0],
        label = statistical.chartObject.data.labels[firstPoint._index].replace(statistical.symptomText, "");
    // var value = statistical.chartObject.data.datasets[firstPoint._datasetIndex].data[firstPoint._index];
    statistical.tableLocation.html(statistical.tableHtml);
    statistical.modalForListPeople_tittle.html(
        "<h3>Danh Sách Những Người Có " + label + statistical.symptomText + "</h3>"
    );
    statistical.modalForListPeople_tittle.append("<h4>" + statistical.getDateRangeText() +"</h4>")
    statistical.getDataSymptomList(0, label);
    statistical.modalForListPeople.modal("show");
}
statistical.tableLocation = $("#tableForListPerson");
statistical.pagingLocation = $("#pagingForTable");
statistical.pagingVoid = "<li><a class='page-link' tabindex='-1'>.</li>";
statistical.drawTable = function(personList){
    var tableBody = $("#tableForListPerson_body");
    tableBody.html("");
    $.each(personList,
        function (index, person) {
            tableBody.append(
                "<tr>" +
                "   <td>" + person.name + "</td>" +
                "   <td>" + statistical.getDate(person.date) + "</td>" +
                "   <td><a href='/viewDeclare/" + person.idPerson +"' class='btn btn-info'>Chi Tiết</a></td>" +
                "</tr>"
            );
        }
    );
}
statistical.getDataSymptomList = function(page,amount){
    $.ajax(
        {
            url: urlRoot + "statistical_symptom/amount_people/" + page + "/" + statistical.pageSize,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            // data:JSON.stringify(dateRange)
            data:JSON.stringify({
                begin:statistical.dateRange.begin,
                end:statistical.dateRange.end,
                amount: "" + amount
            })
        }
    ).done(
        function (data) {
            statistical.drawTable(data.listPeople);
            var previousPage = data.page - 1;
            if (previousPage >= 0){
                statistical.pagingLocation.html(
                    "<li>" +
                    "   <a class='page-item page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataSymptomList(" + previousPage + "," + amount + ");'>" +
                    "Trang Trước</a>" +
                    "</li>"
                );
            }
            else {
                statistical.pagingLocation.html(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1' >" +
                    "Trang Trước</a>" +
                    "</li>");
            }
            var pagingStart = data.page - 3,
                pagingEnd = data.page + 3;
            if (pagingStart>0){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            else {
                pagingStart = 0;
            }
            if (pagingEnd>data.pageAmount){
                pagingEnd = data.pageAmount;
            }
            for (var i=pagingStart; i<pagingEnd; i++){
                if (i==data.page){
                    statistical.pagingLocation.append(
                        "<li class='page-item active'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataSymptomList(" + i + "," + amount + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }
                else {
                    statistical.pagingLocation.append(
                        "<li class='page-item'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataSymptomList(" + i + "," + amount + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }

            }
            if (pagingEnd!==data.pageAmount){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            var nextPage = data.page + 1;
            if (nextPage<data.pageAmount){
                statistical.pagingLocation.append(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataSymptomList(" + nextPage + "," + amount + ");'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
            else{
                statistical.pagingLocation.append(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
        }
    );
}

statistical.typeSymptomChartText = "Thống Kê Theo Loại Triệu Chứng Từ ";
statistical.getByTypeSymptom = function () {
    if (statistical.setDateRange()){
        statistical.canvasValue();
        $.ajax(
            {
                url: urlRoot + "statistical_symptom/symptomType",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                data:JSON.stringify(statistical.dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [],
                    background = [],
                    border = [],
                    colorCount = 0;
                $.each(data,function (index,value) {
                    label.push(value.tittle);
                    dataValue.push(value.count);
                    background.push(statistical.backgroundColor[colorCount]);
                    border.push(statistical.borderColor[colorCount]);
                    colorCount ++;
                });

                var cxt = $("#statisticalChart")[0].getContext('2d');
                var chartSet = {
                    type: "bar",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "# Người ",
                            data: dataValue,
                            backgroundColor: background,
                            borderColor: border,
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    stepSize: 1
                                }
                            }]
                        },
                        title:{
                            display: true,
                            text: statistical.typeSymptomChartText + statistical.getDateRangeText()
                        },
                        onClick: statistical.getTypeSymptomList
                    }
                }
                statistical.chartObject = new Chart( cxt, chartSet);
                staticPart.changeSizeOfMain();
            }
        ).fail(
            function () {
                statistical.failAction();
            }
        );
    }
}
statistical.symptomName = "";
statistical.getTypeSymptomList = function(event){
    var activePoints = statistical.chartObject.getElementsAtEvent(event),
        firstPoint = activePoints[0];
    statistical.symptomName = statistical.chartObject.data.labels[firstPoint._index];

    statistical.tableLocation.html(statistical.tableHtml);
    statistical.modalForListPeople_tittle.html("<h3>Danh Sách Những Người Bị " + statistical.symptomName + "</h3>");
    statistical.modalForListPeople_tittle.append("<h4>" + statistical.getDateRangeText() +"</h4>")
    statistical.getDataTypeSymptomList(0);
    statistical.modalForListPeople.modal("show");
}
statistical.getDataTypeSymptomList = function(page){
    $.ajax(
        {
            url: urlRoot + "statistical_symptom/symptomType/" + page + "/" + statistical.pageSize,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            // data:JSON.stringify(dateRange)
            data:JSON.stringify({
                begin:statistical.dateRange.begin,
                end:statistical.dateRange.end,
                symptom: statistical.symptomName
            })
        }
    ).done(
        function (data) {
            statistical.drawTable(data.content);
            var previousPage = data.pageable.pageNumber - 1;
            if (previousPage >= 0){
                statistical.pagingLocation.html(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataTypeSymptomList(" + previousPage + ");'>" +
                    "Trang Trước</a>" +
                    "</li>"
                );
            }
            else {
                statistical.pagingLocation.html(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1' >" +
                    "Trang Trước</a>" +
                    "</li>");
            }
            var pagingStart = data.pageable.pageNumber - 3,
                pagingEnd = data.pageable.pageNumber + 3;
            if (pagingStart>0){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            else {
                pagingStart = 0;
            }
            if (pagingEnd>data.totalPages){
                pagingEnd = data.totalPages;
            }
            for (var i=pagingStart; i<pagingEnd; i++){
                if (i==data.pageable.pageNumber){
                    statistical.pagingLocation.append(
                        "<li class='page-item active'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataTypeSymptomList(" + i + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }
                else {
                    statistical.pagingLocation.append(
                        "<li class='page-item'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataTypeSymptomList(" + i + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }

            }
            if (pagingEnd!==data.totalPages){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            var nextPage = data.pageable.pageNumber + 1;
            if (nextPage<data.totalPages){
                statistical.pagingLocation.append(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataTypeSymptomList(" + nextPage + ");'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
            else{
                statistical.pagingLocation.append(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
        }
    );
}

statistical.exposureText = " Nguồn";
statistical.exposureChartText = "Thống Kê Số Người Có Cùng Số Nguồn Bệnh Đã Tiếp Xúc Từ ";
statistical.getExposure = function () {
    if (statistical.setDateRange()){
        statistical.canvasValue();
        $.ajax(
            {
                url: urlRoot + "statistical_exposure/amount_people",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                data:JSON.stringify(statistical.dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [],
                    background = [],
                    border = [],
                    colorCount = 0;
                $.each(data,function (index,value) {
                    label.push(value.numberSymptom + statistical.exposureText);
                    dataValue.push(value.amountEntry);
                    background.push(statistical.backgroundColor[colorCount]);
                    border.push(statistical.borderColor[colorCount]);
                    colorCount ++;
                });

                var cxt = $("#statisticalChart")[0].getContext('2d');
                var chartSet = {
                    type: "pie",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "# Tiếp Xúc",
                            data: dataValue,
                            backgroundColor: background,
                            borderColor: border,
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        },
                        title:{
                            display: true,
                            text: statistical.exposureChartText + statistical.getDateRangeText()
                        },
                        onClick: statistical.getExposureList
                    }
                }
                statistical.chartObject = new Chart( cxt, chartSet);
                staticPart.changeSizeOfMain();
            }
        ).fail(
            function () {
                statistical.failAction();
            }
        );
    }

}
statistical.getExposureList = function(event){
    var activePoints = statistical.chartObject.getElementsAtEvent(event),
        firstPoint = activePoints[0],
        label = statistical.chartObject.data.labels[firstPoint._index].replace(statistical.exposureText, "");
    // var value = statistical.chartObject.data.datasets[firstPoint._datasetIndex].data[firstPoint._index];
    statistical.tableLocation.html(statistical.tableHtml);
    statistical.modalForListPeople_tittle.html(
        "<h3>Danh Sách Những Người Từng Tiếp Xúc Với " + label + statistical.exposureText + "</h3>"
    );
    statistical.modalForListPeople_tittle.append("<h4>" + statistical.getDateRangeText() +"</h4>")
    statistical.getDataExposureList(0, label);
    statistical.modalForListPeople.modal("show");
}
statistical.getDataExposureList = function(page, amount){
    $.ajax(
        {
            url: urlRoot + "statistical_exposure/amount_people/" + page + "/" + statistical.pageSize,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            // data:JSON.stringify(dateRange)
            data:JSON.stringify({
                begin:statistical.dateRange.begin,
                end:statistical.dateRange.end,
                amount: "" + amount
            })
        }
    ).done(
        function (data) {
            statistical.drawTable(data.listPeople)
            var previousPage = data.page - 1;
            if (previousPage >= 0){
                statistical.pagingLocation.html(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataExposureList(" + previousPage + ", " + amount + ");'>" +
                    "Trang Trước</a>" +
                    "</li>"
                );
            }
            else {
                statistical.pagingLocation.html(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1' >" +
                    "Trang Trước</a>" +
                    "</li>");
            }
            var pagingStart = data.page - 3,
                pagingEnd = data.page + 3;
            if (pagingStart>0){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            else {
                pagingStart = 0;
            }
            if (pagingEnd>data.pageAmount){
                pagingEnd = data.pageAmount;
            }
            for (var i=pagingStart; i<pagingEnd; i++){
                if (i==data.page){
                    statistical.pagingLocation.append(
                        "<li class='page-item active'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataExposureList(" + i + ", " + amount + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }
                else {
                    statistical.pagingLocation.append(
                        "<li class='page-item'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataExposureList(" + i + ", " + amount + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }

            }
            if (pagingEnd!==data.pageAmount){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            var nextPage = data.page + 1;
            if (nextPage<data.pageAmount){
                statistical.pagingLocation.append(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataExposureList(" + nextPage + ", " + amount + ");'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
            else{
                statistical.pagingLocation.append(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
        }
    );
}

statistical.exposureTypeChartText = "Thống Kê Số Người Từng Tiếp Xúc Với Các Loại Nguồn Bệnh";
statistical.getByTypeExposure = function () {
    if (statistical.setDateRange()){
        statistical.canvasValue();
        $.ajax(
            {
                url: urlRoot + "statistical_exposure/exposureType",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                data:JSON.stringify(statistical.dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [],
                    background = [],
                    border = [],
                    colorCount = 0;
                $.each(data,function (index,value) {
                    label.push(value.tittle);
                    dataValue.push(value.count);
                    background.push(statistical.backgroundColor[colorCount]);
                    border.push(statistical.borderColor[colorCount]);
                    colorCount ++;
                });

                var cxt = $("#statisticalChart")[0].getContext('2d');
                var chartSet = {
                    type: "bar",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "# Người ",
                            data: dataValue,
                            backgroundColor: background,
                            borderColor: border,
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    stepSize: 1
                                }
                            }]
                        },
                        title:{
                            display: true,
                            text: statistical.exposureTypeChartText + statistical.getDateRangeText()
                        },
                        onClick: statistical.getTypeExposureList
                    }
                }
                statistical.chartObject = new Chart( cxt, chartSet);
                staticPart.changeSizeOfMain();
            }
        ).fail(
            function () {
                statistical.failAction();
            }
        );
    }
}
statistical.exposureName = "";
statistical.getTypeExposureList = function(event){
    var activePoints = statistical.chartObject.getElementsAtEvent(event),
        firstPoint = activePoints[0];
    statistical.exposureName = statistical.chartObject.data.labels[firstPoint._index];

    statistical.tableLocation.html(statistical.tableHtml);
    statistical.modalForListPeople_tittle.html("<h3>Danh Sách Những Người Từng Tiếp Xúc Với</h3>");
    statistical.modalForListPeople_tittle.append("<h4>" + statistical.exposureName +"</h4>")
    statistical.modalForListPeople_tittle.append("<h4>" + statistical.getDateRangeText() +"</h4>")
    statistical.getDataTypeExposureList(0);
    statistical.modalForListPeople.modal("show");
}
statistical.getDataTypeExposureList = function(page){
    $.ajax(
        {
            url: urlRoot + "statistical_exposure/exposureType/" + page + "/" + statistical.pageSize,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            // data:JSON.stringify(dateRange)
            data:JSON.stringify({
                begin:statistical.dateRange.begin,
                end:statistical.dateRange.end,
                exposure: statistical.exposureName
            })
        }
    ).done(
        function (data) {
            statistical.drawTable(data.content);
            var previousPage = data.pageable.pageNumber - 1;
            if (previousPage >= 0){
                statistical.pagingLocation.html(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataTypeExposureList(" + previousPage + ");'>" +
                    "Trang Trước</a>" +
                    "</li>"
                );
            }
            else {
                statistical.pagingLocation.html(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1' >" +
                    "Trang Trước</a>" +
                    "</li>");
            }
            var pagingStart = data.pageable.pageNumber - 3,
                pagingEnd = data.pageable.pageNumber + 3;
            if (pagingStart>0){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            else {
                pagingStart = 0;
            }
            if (pagingEnd>data.totalPages){
                pagingEnd = data.totalPages;
            }
            for (var i=pagingStart; i<pagingEnd; i++){
                if (i==data.pageable.pageNumber){
                    statistical.pagingLocation.append(
                        "<li class='page-item active'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataTypeExposureList(" + i + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }
                else {
                    statistical.pagingLocation.append(
                        "<li class='page-item'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataTypeExposureList(" + i + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }

            }
            if (pagingEnd!==data.totalPages){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            var nextPage = data.pageable.pageNumber + 1;
            if (nextPage<data.totalPages){
                statistical.pagingLocation.append(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataTypeExposureList(" + nextPage + ");'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
            else{
                statistical.pagingLocation.append(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
        }
    );
}

statistical.entryChartText = "Thống Kê Số Người Đã Nhập Cảnh Từ ";
statistical.getEntry = function () {
    if (statistical.setDateRange()){
        statistical.canvasValue();
        $.ajax(
            {
                url: urlRoot + "statistical_entry/amount_people",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                data:JSON.stringify(statistical.dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [];
                $.each(data,function (index,value) {
                    label.push(statistical.getDate(value.entryDate));
                    dataValue.push(value.value);
                });
                var cxt = $("#statisticalChart")[0].getContext('2d');
                var chartSet = {
                    type: "line",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "# Người ",
                            fillColor: 'rgba(86,229,229,0.43)',
                            strokeColor: 'rgba(37, 147, 127, 0.6)',
                            pointBackgroundColor: 'rgba(227,15,15,0.68)',
                            pointBorderColor: 'rgba(227,57,15,0.94)',
                            backgroundColor: 'rgba(87,208,187,0.4)',
                            borderColor: 'rgba(37, 147, 127, 0.8)',
                            borderWidth: 1,
                            data: dataValue
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero:true,
                                    stepSize: 1
                                }
                            }]
                        },
                        title:{
                            display: true,
                            text: statistical.entryChartText + statistical.getDateRangeText()
                        },
                        onClick: statistical.getEntryList
                    }
                }
                statistical.chartObject = new Chart( cxt, chartSet);
                staticPart.changeSizeOfMain();
                $()
            }
        ).fail(
            function () {
                statistical.failAction();
            }
        );
    }
}
statistical.getEntryList = function(event){
    statistical.tableLocation.html(statistical.tableHtml);
    statistical.modalForListPeople_tittle.html("<h3>Danh Sách Những Người Nhập Cảnh</h3>");
    statistical.modalForListPeople_tittle.append("<h4>" + statistical.getDateRangeText() +"</h4>")
    statistical.getDataEntryList(0);
    statistical.modalForListPeople.modal("show");
}
statistical.getDataEntryList = function(page){
    $.ajax(
        {
            url: urlRoot + "statistical_entry/listPeople/" + page + "/" + statistical.pageSize,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            // data:JSON.stringify(dateRange)
            data:JSON.stringify({
                begin:statistical.dateRange.begin,
                end:statistical.dateRange.end
            })
        }
    ).done(
        function (data) {
            statistical.drawTable(data.content);
            var previousPage = data.pageable.pageNumber - 1;
            if (previousPage >= 0){
                statistical.pagingLocation.html(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataEntryList(" + previousPage + ");'>" +
                    "Trang Trước</a>" +
                    "</li>"
                );
            }
            else {
                statistical.pagingLocation.html(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1' >" +
                    "Trang Trước</a>" +
                    "</li>");
            }
            var pagingStart = data.pageable.pageNumber - 3,
                pagingEnd = data.pageable.pageNumber + 3;
            if (pagingStart>0){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            else {
                pagingStart = 0;
            }
            if (pagingEnd>data.totalPages){
                pagingEnd = data.totalPages;
            }
            for (var i=pagingStart; i<pagingEnd; i++){
                if (i==data.pageable.pageNumber){
                    statistical.pagingLocation.append(
                        "<li class='page-item active'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataEntryList(" + i + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }
                else {
                    statistical.pagingLocation.append(
                        "<li class='page-item'>" +
                        "<a class='page-link' tabindex='-1' " +
                        "onclick='statistical.getDataEntryList(" + i + ");'>" +
                        (i+1) + "</a>" +
                        "</li>");
                }

            }
            if (pagingEnd!==data.totalPages){
                statistical.pagingLocation.append(statistical.pagingVoid);
            }
            var nextPage = data.pageable.pageNumber + 1;
            if (nextPage<data.totalPages){
                statistical.pagingLocation.append(
                    "<li class='page-item'>" +
                    "   <a class='page-link' tabindex='-1' " +
                    "       onclick='statistical.getDataEntryList(" + nextPage + ");'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
            else{
                statistical.pagingLocation.append(
                    "<li class='page-item disabled'>" +
                    "   <a class='page-link' tabindex='-1'>" +
                    "Trang Sau</a>" +
                    "</li>"
                );
            }
        }
    );
}

