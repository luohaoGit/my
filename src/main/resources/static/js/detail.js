/**
 * Created by kuangshanshan1 on 17/11/21.
 */
!(function(){
    employee = JSON.parse(employee);
    hall = JSON.parse(hall);

    $('#hallName').html(hall.name);

    $.ajax({
        url: urlPrefix + "hall/users/" + hall.id,
        type: 'get',
        dataType: 'json',
        success: function (res) {
            alert(JSON.stringify(res))
            if (res && res.length > 0) {
                var users = '';
                res.forEach(function (item) {
                    users += '<li> \
                <label class="label-checkbox item-content" user-id="' + item.id + '"> \
                <input type="radio" name="my-radio" /> \
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
    $('#users').on('click', function (e) {
        var label = $(e.target).parent('label');
        if (label && label.length > 0) {
            var userId = label.attr('user-id');
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

    $('#confirm1').on('click', function () {
        curAddr.recipients = $('#userName').val().trim();
        curAddr.telephone = $('#userPhone').val().trim();
        curAddr.address = $('#userAdd').val().trim();
        curAddr.hallId = hall.id;
        curAddr.employeeId = employee.id;
        $.ajax({
            url: urlPrefix + "user/addr",
            type: 'post',
            data: curAddr,
            success: function (res) {
                alert(res.msg);
            }
        });
    });

    $('#confirm2').on('click', function () {
        var subTel = $('#subTel').val();
        var subCode = $('#subCode').val();
        $.ajax({
            url: urlPrefix + "user/check",
            type: 'post',
            data: {
                subTel: subTel,
                subCode: subCode,
                employeeId: employee.id,
                hallId: hall.id
            },
            success: function (res) {
                console.log(res)
                //-1：异常， 0：已发放， 1：成功， 2：用户不在所绑定的营业厅
                if (res == -1) {
                    alert("发放失败");
                } else if (res == 0) {
                    alert("本月已发放");
                } else if (res == 1) {
                    alert("发放成功");
                } else if (res == 2) {
                    alert("该客户不在其绑定的营业厅");
                } else {
                    alert("系统错误，请联系管理员");
                }
            }
        });
    });

})();