const urlRoot = 'http://localhost:8080/';
const vietnamId = 1;
const flyTypeId = "1";
const phoneReg = /(09|01[2|6|8|9])+([0-9]{8})\b/g;

$(document).ready(
    function () {
        $(".select_search").select2(
            {
                closeOnSelect:false
            }
        );

        staticPart.changeLocationMain();
        staticPart.changeSizeOfMain();
        staticPart.validateMethod();
    }
);

$(window).resize(
    function () {
        staticPart.changeLocationMain();
        staticPart.changeSizeOfMain();
    }
);
staticPart.inside.resize(
    function () {
        staticPart.changeSizeOfMain();
    }
);

