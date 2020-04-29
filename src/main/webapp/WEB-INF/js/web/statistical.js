var statistical = statistical || {};

statistical.url = 0;
statistical.backgroundColor = [
    'rgba(255, 99, 132, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    'rgba(75, 192, 192, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(255, 159, 64, 0.2)'
];
statistical.borderColor = [
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    'rgba(75, 192, 192, 1)',
    'rgba(153, 102, 255, 1)',
    'rgba(255, 159, 64, 1)'
];

statistical.getSymptom = function () {
    var dateBegin = $("#beginDate").val(),
        dateEnd = $("#endDate").val();
    if (dateBegin!=="" && dateEnd!==""){
        var dateRange = {
            begin:dateBegin,
            end:dateEnd
        }
        $.ajax(
            {
                url: urlRoot + "statistical/symptom",
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
                    label.push(value.numberSymptom);
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