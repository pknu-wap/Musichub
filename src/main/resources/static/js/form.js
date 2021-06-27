// form, reload 방지
function inputData(){
    var data = $('#form').serialize();

    console.log(data);
    var postid = $("[name^='postId']").val();
    var link = $("[name^='link']").val();

    console.log(postid);
    console.log(link);

    var RequestListDto = {
        postId:postid,
        link:link
    };

    $.ajax({
        url: "playlist/request",
        data: data,
        type:"POST",
        cache: false
    }).done(function (fragment) {
        // $("#list").replaceWith(fragment);
    });

}
// 출처: https://joyhong.tistory.com/104 [옳은 길로..]