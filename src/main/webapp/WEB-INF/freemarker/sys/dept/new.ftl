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
    <@config.baseCSS></@config.baseCSS>

</head>

<body>

<div class="container  mag-top">
    <form class="form-horizontal" role="form" id="deptForm">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门名称</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入部门名称">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">部门电话</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入部门电话">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门领导</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入部门领导">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">领导电话</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入领导电话">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">领导职位</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入领导职位">
            </div>

            <label for="inputEmail3" class="col-sm-2 control-label">是否禁用</label>
            <div class="col-sm-4">
                <input id="switch-onText" type="checkbox" checked="checked" data-on-text="Yes" data-on-color="info">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">部门地址</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入部门地址">
            </div>
        </div>
    </form>

    <div class="submit">
        <button type="button" class="btn btn-success">提交</button>
        &nbsp; &nbsp; &nbsp; &nbsp;
        <button type="button" class="btn btn-success">重置</button>
    </div>
</div>



<@config.jqueryJS></@config.jqueryJS>
<@config.bootJS></@config.bootJS>
<@config.bootValidatorJS></@config.bootValidatorJS>
<@config.bootValidatorJSLANG></@config.bootValidatorJSLANG>
<@config.bootSwitchJS></@config.bootSwitchJS>
 <script type="application/javascript">
     $('input[type="checkbox"], input[type="radio"]').bootstrapSwitch();

 </script>

</body>