
// 빈 값 체크
var isEmpty = function (value) {
    if (value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
        return true
    } else {
        return false
    }
};


// 유튜브 비디오 id 추출
function youtube_parser(url) {
    var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
    var match = url.match(regExp);
    return (match && match[7].length == 11) ? match[7] : false;
}


// playlist - index = 현재 재생위치
var videoIds = [];
var index = 0;
var option;
var onLoad;
var yPlayer;


function loadYouTubeApi() {
    var tag = document.createElement('script');
    tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    if (option == "Sequen")
        videoID = videoIds[0];
    else
        videoID = videoIds[Math.floor(Math.random() * videoIds.length)];
}


function onYouTubeIframeAPIReady() {
    yPlayer = new YT.Player('player', {

        height: '400',
        width: '600',
        videoId: videoID,
        playerVars: {'disablekb': 1, 'controls': 1, 'rel': 0},

        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange,
            'onError': onPlayerError
        }
    });
}


function onPlayerError(event) {

    if (event.data == 150) {
        yPlayer.stopVideo();
        onPlayerStateChange_excute();
    }
}


function onPlayerReady(event) {

    yPlayer.setPlaybackRate(1);
    event.target.setVolume(70);
    yPlayer.playVideo();
}


function onPlayerStateChange(event) {

    if (event.data == YT.PlayerState.ENDED) {
        setTimeout(onPlayerStateChange_excute, onLoad);
    }
}


function onPlayerStateChange_excute() {

    index++;
    if (option == "Sequen") {
        if (isEmpty(videoIds[index]) == true)
            index = 0;
        videoID = videoIds[index];
    } else {
        videoID = videoIds[Math.floor(Math.random() * videoIds.length)];
    }
    yPlayer.loadVideoById(videoID);

}

function execute_func() {

    // 정상적인 url만 필터하기
    var tmp_url = [];
    videoIds = [];
    $("[name^='tb']").each(function () {
        tmp_url.push($(this).val());
    });

    for (var i = 0; i < tmp_url.length; i++) {
        if (isEmpty(tmp_url[i]) == false) {
            var str_tmp = youtube_parser(tmp_url[i]);

            var url = 'https://www.youtube.com/watch?v=' + str_tmp;

            $.getJSON('https://noembed.com/embed',
                {format: 'json', url: url}, function (data) {
                    console.log(data.title);
                });
            // https://stackoverflow.com/questions/30084140/youtube-video-title-with-api-v3-without-api-key


            if (str_tmp != false)
                videoIds.push(str_tmp);
        }
    }

    if (isEmpty(videoIds) == false) {
        if (isEmpty(yPlayer) == false) {

            yPlayer.stopVideo();
            yPlayer.destroy();
            yPlayer = null;
            index = 0;


            console.log(yPlayer)
            onYouTubeIframeAPIReady();
        }
        loadYouTubeApi();
    }
}


$('#Seq_Execute').click(function () {

    option = "Sequen";
    execute_func();
});


$('#Ran_Execute').click(function () {

    option = "Random";
    execute_func();
});


$('#vol_up').click(function () {

    if (isEmpty(yPlayer) == false) {
        var cur_vol = yPlayer.getVolume();
        if (cur_vol < 100) yPlayer.setVolume(cur_vol + 10);
    }
});


$('#vol_down').click(function () {

    if (isEmpty(yPlayer) == false) {
        var cur_vol = yPlayer.getVolume();
        if (cur_vol > 0) yPlayer.setVolume(cur_vol - 10);
    }
});


$('#Next_ms').click(function () {
    if (isEmpty(yPlayer) == false) {

        yPlayer.seekTo(yPlayer.getDuration());
    }
});

$('#Previous_ms').click(function () {

    if (isEmpty(yPlayer) == false) {
        if (option == "Sequen") {
            if (index > 0) {
                yPlayer.stopVideo();
                yPlayer.destroy();
                yPlayer = null;
                index--;
                videoID = videoIds[index];
                onYouTubeIframeAPIReady();
                loadYouTubeApi();

            } else {
                alert("이전 곡이 없습니다.");
            }
        } else {
            alert("무작위 재생은 해당 기능을 지원하지 않습니다.");
        }
    }
});

$('#after_play').change(function () {

    onLoad = $(this).val();
});