<#import "../template/template.ftl" as template>
<#import "../config/config.ftl" as config>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>
<@config.bootCSS></@config.bootCSS>
<#--<@config.themeCSS></@config.themeCSS>-->
<@config.fontCSS></@config.fontCSS>
<#--<@config.baseCSS></@config.baseCSS>-->
<@config.loginCSS></@config.loginCSS>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>智慧城管电子台账</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>电子台账系统</strong></h4>
            </div>
        </div>
        <div class="col-sm-5">
            <form method="post" action="${base}/login.htm">
                <h4 class="no-margins">登录：</h4>
            <#--<p class="m-t-md">登录到H+后台主题UI框架</p>-->
                <input type="text" class="form-control uname" placeholder="用户名" name="username"/>
                <input type="password" class="form-control pword m-b" placeholder="密码" name="password"/>
                <div class="validate-code">
                    <input type="text" class="form-control validate-code-input" placeholder="验证码" name="validatecode"/>
                    <img src="${base}/api/validatecode" onclick="reloadImage(this)">
                </div>
                <div class="remember-me checkbox"><label>
                    <input type="checkbox" class="checkbox" id="agree" name="rememberMe">&nbsp; &nbsp;&nbsp;记住我
                </label></div>
                <span style="color: red;">${Session["error"]?default("")}</span>
                <button class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
        <#--&copy; 2015 All Rights Reserved. H+-->
        </div>
    </div>
</div>

<@config.jqueryJS></@config.jqueryJS>
<script type="application/javascript">
    function reloadImage(obj) {
        $(obj).attr("src", "${base}/api/validatecode?t=" + new Date().getTime());
    }
</script>

</body>