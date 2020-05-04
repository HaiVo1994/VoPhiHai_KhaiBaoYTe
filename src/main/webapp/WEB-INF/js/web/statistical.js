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

statistical.getSymptom = function () {
    $("#locationOfChart").html("<canvas id=\"statisticalChart\" width=\"100%\" height=\"40\"></canvas>");
    var dateBegin = $("#beginDate").val(),
        dateEnd = $("#endDate").val();
    if (dateBegin!=="" && dateEnd!==""){
        var dateRange = {
            begin:dateBegin,
            end:dateEnd
        }
        $.ajax(
            {
                url: urlRoot + "statistical_symptom/amount_people",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [],
                    background = [],
                    border = [],
                    colorCount = 0;
                $.each(data,function (index,value) {
                    label.push(value.numberSymptom + " Triệu Chứng");
                    dataValue.push(value.amountEntry);
                    background.push(statistical.backgroundColor[colorCount]);
                    border.push(statistical.borderColor[colorCount]);
                    colorCount ++;
                });
                // console.log(label);
                // console.log(dataValue);
                var cxt = $("#statisticalChart")[0].getContext('2d');
                var chartSet = {
                    type: "bar",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "# Triệu Chứng",
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
                        }
                    }
                }
                var barChart = new Chart( cxt, chartSet);
            }
        );
    }

}
statistical.getByTypeSymptom = function () {
    $("#locationOfChart").html("<canvas id=\"statisticalChart\" width=\"100%\" height=\"40\"></canvas>");

    var dateBegin = $("#beginDate").val(),
        dateEnd = $("#endDate").val();
    if (dateBegin!=="" && dateEnd!==""){
        var dateRange = {
            begin:dateBegin,
            end:dateEnd
        }
        $.ajax(
            {
                url: urlRoot + "statistical_symptom/symptomType",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(dateRange)
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
                // console.log(label);
                // console.log(dataValue);
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
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                }
                var barChart = new Chart( cxt, chartSet);
            }
        );
    }

}

statistical.getExposure = function () {
    $("#locationOfChart").html("<canvas id=\"statisticalChart\" width=\"100%\" height=\"40\"></canvas>");
    var dateBegin = $("#beginDate").val(),
        dateEnd = $("#endDate").val();
    if (dateBegin!=="" && dateEnd!==""){
        var dateRange = {
            begin:dateBegin,
            end:dateEnd
        }
        $.ajax(
            {
                url: urlRoot + "statistical_exposure/amount_people",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(dateRange)
            }
        ).done(
            function (data) {
                var label = [], dataValue = [],
                    background = [],
                    border = [],
                    colorCount = 0;
                $.each(data,function (index,value) {
                    label.push(value.numberSymptom + " Nguồn");
                    dataValue.push(value.amountEntry);
                    background.push(statistical.backgroundColor[colorCount]);
                    border.push(statistical.borderColor[colorCount]);
                    colorCount ++;
                });
                // console.log(label);
                // console.log(dataValue);
                var cxt = $("#statisticalChart")[0].getContext('2d');
                var chartSet = {
                    type: "bar",
                    data: {
                        labels: label,
                        datasets:[{
                            label: "# Triệu Chứng",
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
                        }
                    }
                }
                var barChart = new Chart( cxt, chartSet);
            }
        );
    }

}
statistical.getByTypeExposure = function () {
    $("#locationOfChart").html("<canvas id=\"statisticalChart\" width=\"100%\" height=\"40\"></canvas>");

    var dateBegin = $("#beginDate").val(),
        dateEnd = $("#endDate").val();
    if (dateBegin!=="" && dateEnd!==""){
        var dateRange = {
            begin:dateBegin,
            end:dateEnd
        }
        $.ajax(
            {
                url: urlRoot + "statistical_exposure/exposureType",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(dateRange)
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
                // console.log(label);
                // console.log(dataValue);
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
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                }
                var barChart = new Chart( cxt, chartSet);
            }
        );
    }
}

statistical.getEntry = function () {
    $("#locationOfChart").html("<canvas id=\"statisticalChart\" width=\"100%\" height=\"40\"></canvas>");
    var dateBegin = $("#beginDate").val(),
        dateEnd = $("#endDate").val();
    if (dateBegin!=="" && dateEnd!=="") {
        var dateRange = {
            begin: dateBegin,
            end: dateEnd
        }
        $.ajax(
            {
                url: urlRoot + "statistical_entry/amount_people",
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(dateRange)
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
                            fillColor: 'rgba(37, 147, 127, 0.5)',
                            strokeColor: 'rgba(37, 147, 127, 0.6)',
                            pointColor: 'rgba(37, 147, 127, 1)',
                            backgroundColor: 'rgba(37, 147, 127, 0.1)',
                            borderWidth: 1,
                            data: dataValue
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero:true
                                }
                            }]
                        }
                    }
                }
                var barChart = new Chart( cxt, chartSet);
            }
        );
    }
}
statistical.getDate = function(dateNumber){
    var day = new Date(dateNumber);
    return day.getDate() + "/" + day.getMonth() + "/" + day.getFullYear();
}