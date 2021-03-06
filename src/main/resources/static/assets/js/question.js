// index.html 로드 이후에 1번 문제 요청
window.onload=function(){
    $.ajax({
        type: 'GET',
        url: '/api/v1/question/' + 1,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
    }).done(function(data) {
        document.getElementById("question_content").innerHTML = data['question'];
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
            // 채점결과 삭제
            let grading_results = document.getElementById('grading_results');
            grading_results.innerHTML = "";

            document.getElementById("question_content").innerHTML = data['question'];
        }).fail(function(){
        });
});

// 채점
let grading_btn = document.querySelector("#grading");
grading_btn.addEventListener("click", function(event){
    let question_selectbox = document.getElementById("question_select");
    let question_number = question_selectbox.options[question_selectbox.selectedIndex].value;

    $.ajax({
        type: 'GET',
        url: '/api/v1/question/marking/' + question_number,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
    }).done(function(data) {
        let grading_results = document.getElementById('grading_results');
        
        if(data['results'] == "correct"){
            grading_results.innerHTML = "정답";
        }else{
            grading_results.innerHTML = "오답";
        }
        
    }).fail(function(){
    });
});
