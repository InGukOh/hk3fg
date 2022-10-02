var input_score = document.getElementById("game_score");

window.addEventListener('message', function(e) {
    console.log('메세지 받음');
    var score_DATA = e.data;

    console.log('e : ' + score_DATA["childData"]); //게임 점수 받기

    if(e.data.childData != null){
        alert("받음받음받음 받음 받음!!!!!!!!!!!!!")
        input_score.value = score_DATA["childData"];
    }
});