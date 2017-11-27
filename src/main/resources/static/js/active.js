/**
 * Created by kuangshanshan1 on 17/11/21.
 */
!(function(){
    var areaId = [];
    $.ajax({
        url: urlConfig.url + '1124',
        type: 'get',
        dataType: 'json',
        success: function(res){
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
                                            res = convert(res);
                                            if(res.success){
                                                handleAjax(res, '#picker03')
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
        })
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
                areaId.push(value);
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

})()