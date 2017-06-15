<#import "../../config/config.ftl" as config>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>部门管理</title>
    <@config.bootCSS></@config.bootCSS>
    <@config.bootSwitchCSS></@config.bootSwitchCSS>
    <@config.bootValidatorCSS></@config.bootValidatorCSS>
    <@config.layerCSS></@config.layerCSS>
    <@config.bootSwitchCSS></@config.bootSwitchCSS>
    <@config.baseCSS></@config.baseCSS>

</head>

<body>

<div class="container  mag-top">
    <form class="form-horizontal" role="form" id="deptForm">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入部门名称" name="name">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">部门电话</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入部门电话" name="phone">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门领导</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入部门领导" name="leader">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">领导电话</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入领导电话" name="leaderMobile">
            </div>
        </div>

        <div class="form-group">

            <label for="inputEmail3" class="col-sm-2 control-label">领导职位</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入领导职位" name="leaderPosition">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">部门编号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入部门编号" value="${code!''}"
                       readonly="readonly" name="code">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门地址</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入部门地址" name="address">
            </div>
        </div>

        <input type="hidden" name="parentCode" value="${parentCode!''}">
    </form>

    <div class="submit">
        <button type="button" class="btn btn-success" onclick="doSubmit();">提交</button>
        &nbsp; &nbsp; &nbsp; &nbsp;
        <button type="button" class="btn btn-success" onclick="doReset()">重置</button>
    </div>
</div>



<@config.jqueryJS></@config.jqueryJS>
<@config.bootJS></@config.bootJS>
<@config.bootValidatorJS></@config.bootValidatorJS>
<@config.bootValidatorJSLANG></@config.bootValidatorJSLANG>
<@config.bootSwitchJS></@config.bootSwitchJS>
<@config.jqueryFormJS></@config.jqueryFormJS>
<@config.bootSwitchJS></@config.bootSwitchJS>
<@config.layerJS></@config.layerJS>
<script type="application/javascript">
    layui.use(['layer', 'form'], function () {
        layer = layui.layer
    });

    $(function () {
        $('#deptForm').bootstrapValidator({
            container: 'tooltip',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: '部门名称不能为空',
                    validators: {
                        notEmpty: {
                            message: '部门名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '部门名称长度必须在6到18位之间'
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: '部门电话不能为空'
                        }
                    }
                },
                address: {
                    validators: {
                        notEmpty: {
                            message: "部门地址不能为空"
                        }
                    }
                }
            }
        });

    });


    function doSubmit() {
        var bootstrapValidator = $("#deptForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            $("#deptForm").ajaxSubmit({
                url: "${base}/sys/dept/add.json",
                type: 'post',
                success: function (resp) {
                    if (resp.code = "0000") {
                        layer.msg("新增部门成功", function () {
                            parent.refresh();
                        });
                    } else {

                    }
                },
                error: function () {

                }
            })
        }
    }

    function doReset() {
        $('#deptForm').data('bootstrapValidator').resetForm(true);
        $('#deptForm').find("input").each(function (index) {
            if (!$(this).attr("readonly")) {
                $(this).val("");
            }
        })
    }

</script>

</body>