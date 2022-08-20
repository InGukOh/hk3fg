/*$(document).ready(function() {*/
/*document.write("<script type=”text/javascript” src=”http://jsgetip.appspot.com\"><"+"/script>");
alert( ip() );*/
    $('#btn_submit').click(function() {
        const title_value = document.getElementById('title').value.trim();
        const writer_anonymousUser_value = document.getElementById('writer_anonymousUser').value.trim();
        const writer_anonymousUser_IP_value = document.getElementById('anonymous_IP').value.trim();
        const target_form = document.getElementById('form_write');

        if(title_value == ""){
            alert('제목을 입력해주세요');
            $('title').focus();
            return false;
        } else if(writer_anonymousUser_value == ""){
            alert('작성자명을 입력해주세요');
        } else if(writer_anonymousUser_value != ""){
           $('#writer').val(writer_anonymousUser_value + "(" + writer_anonymousUser_IP_value + ")");

        }

        $('#append').val($('#test').html());

        target_form.submit();

    });

/*
});*/
