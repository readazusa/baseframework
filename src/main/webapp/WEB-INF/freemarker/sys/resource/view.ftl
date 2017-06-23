<#import "../../config/config.ftl" as config>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>权限管理</title>
<@config.bootCSS></@config.bootCSS>
<@config.bootValidatorCSS></@config.bootValidatorCSS>
<@config.baseCSS></@config.baseCSS>
<@config.layerCSS></@config.layerCSS>
</head>

<body>

<div class="container  mag-top">
    <form class="form-horizontal" role="form" id="resourceForm">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-4 control-label">权限名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入权限名称" name="name" value="${resource.name}" readonly="readonly">
            </div>

        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-4 control-label">权限编码</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入权限编码" name="code" value="${resource.code}" readonly="readonly">
            </div>
        </div>

        <input type="hidden" name="id" value="${resource.id}">

    </form>

    <#--<div class="submit">-->
        <#--<button type="button" class="btn btn-success" onclick="doSubmit();">提交</button>-->
        <#--&nbsp; &nbsp; &nbsp; &nbsp;-->
        <#--<button type="button" class="btn btn-success" onclick="doReset()">重置</button>-->
    <#--</div>-->
</div>



<@config.jqueryJS></@config.jqueryJS>
<@config.bootJS></@config.bootJS>
<@config.bootValidatorJS></@config.bootValidatorJS>
<@config.bootValidatorJSLANG></@config.bootValidatorJSLANG>
<@config.jqueryFormJS></@config.jqueryFormJS>
<@config.layerJS></@config.layerJS>
<script type="application/javascript">
    layui.use(['layer'], function () {
        layer = layui.layer
    });

    $(function () {
        $('#resourceForm').bootstrapValidator({
            container: 'tooltip',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: '权限名称不能为空',
                    validators: {
                        notEmpty: {
                            message: '权限名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '权限称长度必须在6到18位之间'
                        }
                    }
                },
                code: {
                    validators: {
                        notEmpty: {
                            message: '权限编码不能为空'
                        }
                    }
                }
            }
        });

    });


    function doSubmit() {
        var bootstrapValidator = $("#resourceForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            $("#resourceForm").ajaxSubmit({
                url: "${base}/sys/resource/update.json",
                type: 'post',
                success: function (resp) {
                    if (resp.code = "0000") {
                        layer.msg("修改权限成功", function () {
                            parent.refresh();
                        });
                    } else {
                        layer.msg("操作失败，请重新操作: "+ resp.msg)
                    }
                },
                error: function () {

                }
            })
        }
    }

    function doReset() {
        $('#resourceForm').data('bootstrapValidator').resetForm(true);
        $('#resourceForm').find("input").each(function (index) {
                    $(this).val("");
                }
        );
    }

</script>

</body>