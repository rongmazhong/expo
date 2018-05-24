$(document).ready(function(){
    homeMethod.add();


})

var homeMethod={
    add: function () {
        $("#set-parames").on('click', function(){
            layer.msg('hello');
        });
    }
};