/*$(document).ready(function() {*/
    $('#btn_submit').click(function() {
        const title_value = document.getElementById('title').value.trim();
        alert(title_value);
        if(title_value == ""){
            alert('제목을 입력해주세요');
            $('title').focus();
            return false;
        } else {
            $('#append').val($('#test').html());
            $("form_write").submit();
        }
    });
/*
});*/
