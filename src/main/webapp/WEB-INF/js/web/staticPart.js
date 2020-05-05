var staticPart = staticPart || {};
staticPart.header = $("#header");
staticPart.footer = $("#footer");
staticPart.main = $("#main");
staticPart.inside = $("#inside");
staticPart.changeLocationMain = function(){
    staticPart.main.css("top", staticPart.header.height());
    staticPart.main.css("bottom", staticPart.footer.height());
}
staticPart.changeSizeOfMain = function(){
    var sizeOfHeightAndFoot = staticPart.header.height() + staticPart.footer.height(),
        windowHeight = $(window).height() - sizeOfHeightAndFoot,
        insideHeight = staticPart.inside.height();
    if (windowHeight>insideHeight){
        staticPart.main.css("min-height", windowHeight);
    }
    else {
        staticPart.main.css("min-height", insideHeight + sizeOfHeightAndFoot);
    }
}
staticPart.validateMethod = function () {
    jQuery.validator.addMethod("endStatisticalDate",
        function (value, element, params) {
            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value) >= new Date($(params).val());
            }
            return isNaN(value) && isNaN($(params).val())
                || (Number(value) >= Number($(params).val()));
        },"Ngày Kết Thúc Phải Lớn Hơn Hoặc Bằng Ngày Bắt Đầu");
    jQuery.validator.addMethod("dateSelect",
        function (value, element) {
            var today = new Date();
            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value) <= today;
            }
            return isNaN(value) || (Number(value) > Number(today));
        },"Ngày Được Chọn Phải Là Ngày Hôm Nay Hoặc Trước Đó");
}