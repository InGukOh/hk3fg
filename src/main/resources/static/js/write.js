    const btn_submit = document.getElementById("btn_submit");
    const title_value = document.getElementById('title');
    const target_form = document.getElementById('form_write');
    const append = document.getElementById("append");
    
    btn_submit.onclick = function (){
        if(title_value.value.trim() == ""){
            alert('제목을 입력해주세요');
            title_value.focus();
            return false;
        }
        append.value = document.getElementById("input_div_content_editable").innerHTML;
        
        if(append.value == "<p><br></p>" || append.value == ""){
            alert('내용이 입력되지 않았습니다.');
            return false;
        }

        target_form.submit();
    }


/*$('#btn_submit').click(function() {
        const title_value = document.getElementById('title');
        const target_form = document.getElementById('form_write');

        if(title_value == ""){
            alert('제목을 입력해주세요');
            title_value.focus();
            return false;
        }

        $('#append').val($('#input_div_content_editable').html());
        if($('#append').val($('#input_div_content_editable').html()).val()==""){
            alert('내용이 입력되지 않았습니다.');
            return false;
        }

        target_form.submit();

    });*/

