<#import "../../config/config.ftl" as config>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>部门管理</title>
<@config.layerCSS></@config.layerCSS>
<@config.bootCSS></@config.bootCSS>
<@config.bootSwitchCSS></@config.bootSwitchCSS>
<@config.bootValidatorCSS></@config.bootValidatorCSS>
<@config.bootSwitchCSS></@config.bootSwitchCSS>
<@config.baseCSS></@config.baseCSS>

</head>

<body>

<div class="container  mag-top">
    <form class="form-horizontal" role="form" id="userForm">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入姓名" name="name" value="${user.name!""}" readonly="readonly">
            </div>
            <label for="inputEmail3" class="col-sm-2 control-label">性别</label>
            <div class="col-sm-4">
                <input id="switch-onText" type="checkbox" <#if user.sex=='男'>checked="checked"</#if> data-on-text="男" data-off-text="女"
                       value="${user.sex}" name="sex" readonly="readonly">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入用户名" name="username" value="${user.username}" readonly="readonly">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" placeholder="请输入密码" name="password" value="${user.password}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">手机号码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入手机号码" name="mobile" value="${user.mobile!""}" readonly="readonly">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">办公室号码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" placeholder="请输入办公室号码" name="phone" value="${user.phone!""}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" value="${user.deptName!""}"
                       readonly="readonly" name="deptName" id="deptName" >
                <input type="hidden" id="deptCode" name="deptCode" value="${user.deptCode!""}" readonly="readonly">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">职位</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入职位" name="position" value="${user.position!""}" readonly="readonly">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">出生日期</label>
            <div class="col-sm-4">
                <input id="birthday" type="text"  name="birthday"   placeholder="点击选择出生日期" class="form-control" value="${user.birthday?string('yyyy-MM-dd HH:mm:ss')}" readonly="readonly">
            </div>
            <label for="inputEmail3" class="col-sm-2 control-label">是否禁用</label>
            <div class="col-sm-4">
                <input id="switch-onText" type="checkbox"  value="${user.flag}"  name="flag"  data-on-text="YES" data-off-text="NO"  <#if user.flag=='0'>checked="checked"</#if>   readonly="readonly">
            </div>
        </div>

        <div class="form-group">

            <div class="col-sm-4 col-sm-offset-1">
                <label class="control-label">全部角色：</label>
                <select class="selectpicker show-tick form-control" multiple
                        data-live-search="false" style="height: 200px;" id="srcRole" >
                <#list syRoleInfo as role>
                    <option value="${role.id}" >${role.name}</option>
                </#list>
                </select>
            </div>
            <div class="col-sm-1 col-sm-offset-1" style="height: 200px; padding-top: 5px">
                <div class="role-exec">
                    <div>
                        <a class="btn cbtn" href="javascript:void ">&gt; </a>
                    </div>
                    <div>
                        <a class="btn cbtn" href="javascript:void()">&gt;&gt; </a>
                    </div>
                    <div>
                        <a class="btn cbtn" href="javascript:void()">&lt; </a>
                    </div>
                    <div>
                        <a class="btn cbtn" href="javascript:void()">&lt;&lt; </a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 col-sm-offset-1">
                <label class="control-label">以选择角色：</label>
                <select class="selectpicker show-tick form-control" multiple
                        data-live-search="false" style="height: 200px;" id="targetRole"   >

                    <#list hasRoleInfo as role>
                        <option value="${role.id}" >${role.name}</option>
                    </#list>
                </select>
            </div>


            <input id="roleIds" name="roleIds" type="hidden">

            <input name="id" value="${user.id}" type="hidden">
        </div>

    </form>


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
    layui.use(['layer', 'laydate'], function () {
        layer = layui.layer;
        var laydate = layui.laydate;


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
                url: "${base}/sys/user/update.json",
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
            option += "<option value='" + $(this).val() + "' selected >" + $(this).text() + "</option>"
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
        var roleIds="";
        $("#targetRole").find("option").each(function(index){
            roleIds +=$(this).val()+","
        });
        $("#roleIds").val(roleIds);
    }

</script>

</body>