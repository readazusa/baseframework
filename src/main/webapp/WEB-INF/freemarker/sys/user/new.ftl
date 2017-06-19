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
    <form class="form-horizontal" role="form" id="userForm">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入姓名" name="name">
            </div>
            <label for="inputEmail3" class="col-sm-2 control-label">性别</label>
            <div class="col-sm-4">
                <input id="switch-onText" type="checkbox" checked="checked" data-on-text="男" data-off-text="女"
                       value="男">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入用户名" name="username">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入密码" name="password">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">手机号码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入手机号码" name="mobile">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">办公室号码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入办公室号码" name="phone">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" value="${deptname}"
                       readonly="readonly" name="deptName" id="deptName" onclick="doChoiceDept();">
                <input type="hidden" id="deptCode" name="deptCode">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">职位</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入职位" name="position">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">是否禁用</label>
            <div class="col-sm-4">
                <input id="switch-onText" type="checkbox" checked="checked" value="0">
            </div>
        </div>

        <div class="form-group">

            <div class="col-sm-4 col-sm-offset-1">
                <label class="control-label">全部角色：</label>
                <select class="selectpicker show-tick form-control" multiple
                        data-live-search="false" style="height: 200px;" id="srcRole" ondblclick="doClickChoice(this)">
                <#list roles as role>
                    <option value="${role.id}" >${role.name}</option>
                </#list>
                </select>
            </div>
            <div class="col-sm-1 col-sm-offset-1" style="height: 200px; padding-top: 5px">
                <div class="role-exec">
                    <div>
                        <a class="btn cbtn" href="javascript:choiceOne()">&gt; </a>
                    </div>
                    <div>
                        <a class="btn cbtn" href="javascript:choiceAll()">&gt;&gt; </a>
                    </div>
                    <div>
                        <a class="btn cbtn" href="javascript:retChoiceOne()">&lt; </a>
                    </div>
                    <div>
                        <a class="btn cbtn" href="javascript:retChoiceAll()">&lt;&lt; </a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 col-sm-offset-1">
                <label class="control-label">以选择角色：</label>
                <select class="selectpicker show-tick form-control" multiple
                        data-live-search="false" style="height: 200px;" id="targetRole" ondblclick="doClickRetChoice(this)" name="roleIds">


                </select>
            </div>
        </div>

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
    layui.use(['layer'], function () {
        layer = layui.layer
    });
    var index = 0;
    $(function () {

        $('input[type="checkbox"], input[type="radio"]').not('[data-switch-no-init]').bootstrapSwitch();

        $('#userForm').bootstrapValidator({
            container: 'tooltip',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: '姓名不能为空',
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '部门名称长度必须在6到18位之间'
                        }
                    }
                }
            }
        });

    });


    function doSubmit() {
        var bootstrapValidator = $("#userForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        setRoleChoice();
        if (bootstrapValidator.isValid()) {
            $("#userForm").ajaxSubmit({
                url: "${base}/sys/user/add.json",
                type: 'post',
                success: function (resp) {
                    if (resp.code = "0000") {
                        layer.msg("新增用户成功", function () {
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

    function doChoiceDept() {
        index = layer.open({
            type: 2,
            title: "选择部门",
            shadeClose: true,
            maxmin: true,
            area: ['50%', '70%'],
            content: "${base}/sys/dept/tree.htm"
        });
    }

    function setDeptInfo(deptCode, deptName) {
        $("#deptName").val(deptName);
        $("#deptCode").val(deptCode);
        layer.close(index);
    }

    function doClickChoice(){
        var choice = $("#srcRole").find("option:selected")[0]
        var option = "<option value='" + $(choice).val() + "' selected>" + $(choice).text() + "</option>"
        $("#targetRole").append(option);
        $(choice).remove();
    }

    function  doClickRetChoice(){
        var choice = $("#targetRole").find("option:selected")[0]
        var option = "<option value='" + $(choice).val() + "'>" + $(choice).text() + "</option>"
        $("#srcRole").append(option);
        $(choice).remove();
    }


    function choiceOne() {
        var option = "";
        $("#srcRole").find("option:selected").each(function (index) {
            console.log($(this).val() + ", " + $(this).text());
            option += "<option value='" + $(this).val() + "' selected>" + $(this).text() + "</option>"
            $(this).remove();
        })
        $("#targetRole").append(option);
    }

    function choiceAll() {
        var option = "";
        $("#srcRole").find("option").each(function(index){
            console.log($(this).val() + ", " + $(this).text());
            option += "<option value='" + $(this).val() + "' selected>" + $(this).text() + "</option>"
            $(this).remove();
        });
        $("#targetRole").append(option);
    }


    function retChoiceOne() {
        var option = "";
        $("#targetRole").find("option:selected").each(function (index) {
            console.log($(this).val() + ", " + $(this).text());
            option += "<option value='" + $(this).val() + "'>" + $(this).text() + "</option>"
            $(this).remove();
        })
        $("#srcRole").append(option);
    }

    function retChoiceAll() {
        var option = "";
        $("#targetRole").find("option").each(function(index){

            option += "<option value='" + $(this).val() + "'>" + $(this).text() + "</option>"
            $(this).remove();
        });
        $("#srcRole").append(option);
    }


    function  setRoleChoice(){
        $("#targetRole").find("option").each(function(index){
            $(this).attr("selected",true);
        });
    }

</script>

</body>