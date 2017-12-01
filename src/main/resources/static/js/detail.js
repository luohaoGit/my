/**
 * Created by kuangshanshan1 on 17/11/21.
 */
!(function(){

    $.ajax({
        url: urlPrefix + "hall/users/" + hall.id,
        type: 'get',
        dataType: 'json',
        success: function(res){
            if(res && res.length > 0){
                var users = '';
                res.forEach(function(item){
                    users += '<li> \
                    <label class="label-checkbox item-content"> \
                    <input type="radio" class="uu" name="my-radio" value="' + item.id + '"/> \
                    <div class="item-media"><i class="icon icon-form-checkbox"></i></div> \
                    <div class="item-inner"> \
                    <div class="item-title-row"> \
                    <div class="item-title">' + item.wxnickname + '</div> \
                    </div> \
                    </div> \
                    </label> \
                    </li>';
                });

                $('#users').empty().append(users);
            }
        }
    });

    var curAddr = {};
    $('#users').on('click', '.uu', function(e){
        var userId = e.target.value;
        curAddr.userId = userId;
        $.ajax({
            url: urlPrefix + "user/addr/" + userId,
            type: 'get',
            dataType: 'json',
            success: function(res){
                $('#userName').val('');
                $('#userPhone').val('');
                $('#userAdd').val('');
                if(res != null){
                    curAddr = res;
                    $('#userName').val(res.recipients);
                    $('#userPhone').val(res.telephone);
                    $('#userAdd').val(res.address);
                }
            }
        });
    });

    $('#confirm1').on('click', function(){
        curAddr.recipients = $('#userName').val().trim();
        curAddr.telephone = $('#userPhone').val().trim();
        curAddr.address = $('#userAdd').val().trim();
        $.ajax({
            url: urlPrefix + "user/addr",
            type: 'post',
            data: curAddr,
            success: function(res){
                if(res == 1){
                    alert('保存成功');
                }
            }
        });
    });

})();