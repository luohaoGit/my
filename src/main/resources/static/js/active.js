/**
 * Created by kuangshanshan1 on 17/11/21.
 */
Array.prototype.contains = function (obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
};
!(function(){
    if(openId === ''){
        alert("用户验证失败");
        return;
    }

    if(user !== 'null'){
        user = JSON.parse(user);
    }

    if(hall !== 'null'){
        hall = JSON.parse(hall);
        $("body").empty();
        var html = '<div class="hall">您已绑定:' + hall.name + '</div>';
        html += '<br/><br/><div class="tips">如需咨询、作文投稿，可以点击公众号左下角留言按键，拍照、录音、留言都可以，我们客服人员将尽快回复您。</div>';
        $("body").append(html);
        return;
    }

    var hallList = [];
    $.ajax({
        url: urlConfig.url + '1124',
        type: 'get',
        dataType: 'json',
        success: function(res){
            var arr = [1125, 1224];
            res = $(res).map(function(index, item){
               if(arr.contains(item.id)){
                   return item;
               }
            });
            res = convert(res);
            if(res.success){
                handleAjax(res, '#picker01', function(value){
                    $.ajax({
                        url: urlConfig.url + value,
                        type: 'get',
                        dataType: 'json',
                        success: function(res){
                            res = convert(res);
                            if(res.success){
                                handleAjax(res, '#picker02', function(value){
                                    $.ajax({
                                        url: urlConfig.hallUrl + value,
                                        type: 'get',
                                        dataType: 'json',
                                        success: function(res){
                                            hallList = res;
                                            res = convert(res);
                                            if(res.success){
                                                handleAjax(res, '#picker03', function (v) {
                                                    hallId = v;
                                                    if(hallList.length > 0){
                                                        $(hallList).forEach(function(item, index, array){
                                                            if(item.id == v){
                                                                if(item.type == 1) {
                                                                    $("#userAddLi").hide();
                                                                }else if(item.type == 2){
                                                                    $("#userAddLi").show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                })
                                            }
                                        }
                                    })
                                })
                            }
                        }
                    })
                })
            }
        }
    });

    function convert(res) {
        var obj = {
            "success": true,
            "list": {}
        };

        $(res).forEach(function(item, index, array){
            obj.list[item.id] = item.name;
        });

        return obj;
    }

    function handleAjax(res, id, fn){
        var list = res.list,values = Object.keys(list), displayValues = [];
        values.forEach(function(item){
            displayValues.push(list[item])
        });
        $(id).picker({
            toolbarTemplate: '<header class="bar bar-nav">\
                                      <button class="button button-link pull-left"></button>\
                                      <button class="button button-link pull-right close-picker">确定</button>\
                                      <h1 class="title">标题</h1>\
                                      </header>',
            cols: [
                {
                    textAlign: 'center',
                    displayValues: displayValues,
                    values: values
                }
            ],
            onClose: function(arg){
                var value = arg.value[0];
                //areaId.push(value);
                fn&&fn(value);
            },
            formatValue: function (picker, value, displayValue){
                var input = this.input;
                setTimeout(function(){
                    $('#'+input.id).val(displayValue[0])
                },0)
            }
        });
    }

    $("#confirm").on('click', function(e){
        if(hallId === ''){
            alert("请选择营业厅");
            return;
        }
        $.ajax({
            url: urlConfig.relationUrl,
            type: 'post',
            dataType: 'json',
            data: {
                openId: openId,
                hallId: hallId
            },
            success: function (res) {
                alert(res.msg);
            }
        });

        var userInfo = {};
        userInfo.userId = user.id;
        userInfo.recipients = $('#userName').val().trim();
        userInfo.telephone = $('#userPhone').val().trim();
        userInfo.address = $('#userAdd').val().trim();
        $.ajax({
            url: urlPrefix + "user/addr",
            type: 'post',
            data: userInfo,
            success: function (res) {

            }
        });
    });

})();