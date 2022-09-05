const btn_input_comment = document.getElementById("btn_input_comment");
const input_comment = document.getElementById("comment");
const form_comment = document.getElementById("form_comment");
btn_input_comment.onclick = function(){
    if(input_comment.value.trim() == ''){
        alert('댓글이 입력되지 않았습니다.');
        return false;
    }
    alert('작동가능');

    form_comment.submit();

}