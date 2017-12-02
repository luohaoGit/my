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
                    users += '<li class="uu"> \
                    <label class="label-checkbox item-content"> \
                    <input type="radio" name="my-radio" /> \
                    <div class="item-media"><i class="icon icon-form-checkbox uu"></i></div> \
                    <div class="item-inner"> \
                    <div class="item-title-row"> \
                    <div class="item-title uid" user-id="' + item.id + '">' + item.wxnickname + '</div> \
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
        var div = $(e.target).find('div .uid');
        if(div && div.length > 0) {
            var userId = div.attr('user-id');
            curAddr.userId = userId;
            $.ajax({
                url: urlPrefix + "user/addr/" + userId,
                type: 'get',
                dataType: 'json',
                success: function (res) {
                    $('#userName').val('');
                    $('#userPhone').val('');
                    $('#userAdd').val('');
                    if (res != null) {
                        curAddr = res;
                        $('#userName').val(res.recipients);
                        $('#userPhone').val(res.telephone);
                        $('#userAdd').val(res.address);
                    }
                }
            });
        }
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

    $('#confirm2').on('click', function(){
        var subTel = $('#subTel').val();
        var subCode = $('#subCode').val();
        $.ajax({
            url: urlPrefix + "user/check",
            type: 'post',
            data: {
                subTel: subTel,
                subCode: subCode,
                employeeId: employeeId,
                hallId: hall.id
            },
            success: function(res){
                if(res){
                    alert("发放成功");
                }else{
                    alert("发放失败");
                }
            }
        });
    });

})();