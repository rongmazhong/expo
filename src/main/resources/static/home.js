//初始化
$(document).ready(function () {
    homeMethod.init();
    // homeMethod.setTime();
});

var homeMethod = {
    init: function () {
        layui.use('laydate', function () {
            layuiMethods = layui.laydate;
        });
        var nowDate = new Date;
        var year = nowDate.getFullYear();//获取当前年
        $('#nian').html(year);
        var month = nowDate.getMonth() + 1;//获取当前月
        $('#yue').html(month);
        var today = nowDate.getDate();//获取当前日
        $('#ri').html(today);
        $.ajax({
            type: "get",
            async: true,
            data: "",
            url: '/getTime',
            contentType: JSON,
            success: function (result, status, xhr) {
                console.log(result);
                var mydate = result.toString().split("-");
                $('#mnian').html(mydate[0]);
                $('#myue').html(mydate[1]);
                $('#mri').html(mydate[2]);
                // 设置差时
                var curtainTime = ((Date.parse(new Date(result)))/1000/3600/24 + 1).toFixed(0);
                // var nowTime = Date.parse(year + '-' + month + '-' + today);
                var nowTime = ((Date.parse(new Date()))/1000/3600/24 + 1).toFixed(0);
                if (curtainTime == nowTime) {
                    $('#dao').html('0');
                    return;
                } else {
                    // var curtainTime = Date.parse(new Date(mydate));
                    // var nowTime = Date.parse(new Date());
                    // var getDate = Math.abs(parseInt((curtainTime - nowTime) / 1000 / 3600 / 24) + 1).toFixed(0);
                    var getDate = curtainTime-nowTime;
                    $('#dao').html(getDate);
                }
            },
            error: function (xhr, status, error) {
                $('#mnian').html(nowDate.getFullYear()); //获取当前年
                $('#myue').html(nowDate.getMonth() + 1); //获取当前月
                $('#mri').html(nowDate.getDate()); //获取当前日
                $('#dao').html('0');
                window.console.log("获取时间失败，请检查。")
            }
        });
    },
    cloneScreen: function(){
        var browserName=navigator.appName;
        alert(browserName);
        if (browserName=="Netscape") {
            window.open('','_self','');
            window.close();
        } else {
            window.close();
        }
    },
    //全屏
    fullScreen: function () {
        // var elem = document.getElementById("content");
        var elem = $(document.body);
        homeMethod.requestMyFullScreen(elem);
    },
    requestMyFullScreen: function (element) {
        // 判断各种浏览器，找到正确的方法
        var requestMethod = element.fullscreenEnabled  || //W3C
            element.webkitIsFullScreen  ||    //Chrome等
            element.mozRequestFullScreen || //FireFox
            element.msFullscreenEnabled ||
        window.fullScreen(); //IE11

        if (requestMethod) {
            requestMethod.call(element);
        }
        else if (typeof window.ActiveXObject !== "undefined") { //for Internet Explorer
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript !== null) {
                wscript.SendKeys("{F11}");
            }
        }
    },
//时间判断
    booleanTime: function (result) {
        var endTime = Date.parse(new Date(result));
        var nowTime = Date.parse(new Date());
        if ((parseInt((endTime - nowTime) / 1000 / 3600 / 24)) < 0) {
            return false;
        } else {
            return true;
        }
    },

    //设置时间
    setTime: function () {
        var date = $('#mnian').html() + '-' + $('#myue').html() + '-' + $('#mri').html();
        var html = homeMethod.openHtml(date);
        var setOverDate = bootbox.confirm({
            title: '设置',
            message: html,
            className: 'openToSet',
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> 取消'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> 确认'
                }
            },
            callback: function (result) {
                if (result) {
                    //todo 时间校验
                    var getMyTime = $('#expo-date').val();
                    var backCheck = homeMethod.booleanTime(getMyTime);
                    var Mytime = {
                        "callTime": getMyTime
                    };
                    if (backCheck) {
                        $.ajax({
                            type: "POST",
                            async: true,
                            data: JSON.stringify(Mytime),
                            url: '/setTime',
                            contentType: 'application/json;charset=utf-8',
                            success: function (result, status, xhr) {
                                alert("设置成功!");
                                location.reload();
                            },
                            error: function (xhr, status, error) {
                                window.console.log("设置时间失败，请检查。")
                            }
                        });
                    } else {
                        // alert("时间设置有误，请重新设置!");
                        return backCheck;
                    }
                }
            }
        });
        $('#expo-date').removeAttr('lay-key');
        layuiMethods.render({
            elem: '#expo-date',
            min: new Date().toString()
        });
    },
//弹窗页面
    openHtml: function (date) {
        if (!date) {
            date = new Date;
        }
        var html = '<form id="setOverDate"><div><dl><dt class="">请设置开幕时间:</dt><div class="setdiv"></div>';
        html += '<dd><label><input type="text" value="' + date + '" id="expo-date" class="my-input layui-input" lay-key="1"></label>';
        html += '</dd></dl></div></form>';
        return html;
    },

};
