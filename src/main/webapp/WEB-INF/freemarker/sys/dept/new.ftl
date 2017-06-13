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
<@config.layerCSS></@config.layerCSS>
    <@config.baseCSS></@config.baseCSS>

</head>

<body>

<div class="container  mag-top">
    <div>
        <#--<div class="col-md-8 col-md-offset-2">-->
            <form class="form-horizontal" role="form">
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
                        <input type="email" class="form-control" id="inputEmail3" placeholder="请输入领导电话">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">部门地址</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="请输入部门地址">
                    </div>
                </div>
            </form>
    </div>


</div>



<@config.jqueryJS></@config.jqueryJS>
<@config.bootJS></@config.bootJS>
<@config.layerJS></@config.layerJS>
<script>
    layui.use('form', function () {
        var form = layui.form();

    });

    </
    body >