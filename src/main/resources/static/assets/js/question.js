// index.html 로드 이후에 1번 문제 요청
window.onload=function(){
    $.ajax({
        type: 'GET',
        url: '/api/v1/question/' + 1,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
    }).done(function(data) {
        document.getElementById("question_content").innerHTML = data['content'];
    }).fail(function(){
    });
}


// select box 이벤트 설정
let question_selectbox = document.querySelector("#question_select");
    question_selectbox.addEventListener('change', function(event){
        $.ajax({
            type: 'GET',
            url: '/api/v1/question/' + this.value,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(data) {
            document.getElementById("question_content").innerHTML = data['content'];
        }).fail(function(){
        });
});