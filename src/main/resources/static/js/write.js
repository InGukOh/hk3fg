/*$(document).ready(function() {*/
/*document.write("<script type=”text/javascript” src=”http://jsgetip.appspot.com\"><"+"/script>");
alert( ip() );*/
    $('#btn_submit').click(function() {
        const title_value = document.getElementById('title').value.trim();
        const writer_anonymousUser_value = document.getElementById('writer_anonymousUser').value.trim();
        const writer_anonymousUser_IP_value = document.getElementById('anonymous_IP').value.trim();
        alert(title_value);
        alert(writer_anonymousUser_value + "(" + writer_anonymousUser_IP_value + ")");
        if(title_value == ""){
            alert('제목을 입력해주세요');
            $('title').focus();
            return false;
        } else if(writer_anonymousUser_value == ""){
            alert('작성자명을 입력해주세요')
        } else if(writer_anonymousUser_value != ""){
           $('#writer').val(writer_anonymousUser_value + "(" + writer_anonymousUser_IP_value + ")")
        }
        $('#append').val($('#test').html());
    });
    function form_submit(){
        $('#append').val($('#test').html());
        $("form_write").submit();
    }
/*
});*/
