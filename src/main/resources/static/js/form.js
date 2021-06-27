// form, reload 방지
function inputData(){
    var data = $('#form').serialize();
    $.ajax({
        url: "/home/test7_1",
        data: data,
        type:"POST",
        cache: false
    }).done(function (fragment) {
        $("#list").replaceWith(fragment);
    });

}
// 출처: https://joyhong.tistory.com/104 [옳은 길로..]