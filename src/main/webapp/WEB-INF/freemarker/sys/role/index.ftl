<#import "../../template/template.ftl" as template>
<#import "../../config/config.ftl" as config>
<#import "../../config/baseExecJS.ftl" as baseExec>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>角色管理</title>
<@config.bootCSS></@config.bootCSS>
<@config.themeCSS></@config.themeCSS>
<@config.fontCSS></@config.fontCSS>
<@config.bootTableCSS></@config.bootTableCSS>
<@config.baseCSS></@config.baseCSS>
<@config.layerCSS></@config.layerCSS>
</head>
<body class="nav-md" style="overflow: hidden">
<div class="container body">
    <div class="main_container">
    <@template.mainleft></@template.mainleft>
    <@template.mainTop></@template.mainTop>
        <div class="right_col" role="main" style="overflow-y: auto">
            <div class="row">
                <table id="table"></table>
            </div>
            <div id="toolbar">
                <a href="javascript:add();" class="btn btn-primary"><i
                        class="icon iconfont"></i>新增</a>
            </div>
        </div>
    <@template.mainFooter></@template.mainFooter>
    </div>
</div>

<@config.jqueryJS></@config.jqueryJS>
<@config.bootJS></@config.bootJS>
<@config.themeJS></@config.themeJS>
<@config.bootSelectJS></@config.bootSelectJS>
<@config.bootTableJS></@config.bootTableJS>
<@config.bootTableJS_ZH_CN></@config.bootTableJS_ZH_CN>
<@config.layerJS></@config.layerJS>
<script type="application/javascript">
    var index=0;
    layui.use(['layer'], function () {
        layer = layui.layer
    });

    var $table = $("#table");
    $(function () {
        table = $('#table').bootstrapTable({
            method: "post",
            striped: true,
            pagination: true,
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            url: "${base}/sys/role/querypage.json",
            sidePagination: "server",
            search: true,
            showColumns: true,
            showRefresh: true,
            showToggle: true,
            showPaginationSwitch: true,
            toolbar: '#toolbar',
            clickToSelect: false,
//            detailView: true,
            idField: "id",
            showColumns: true,
            columns: [
                {
                    field: 'name',
                    title: '角色名称'
                },
                {
                    field: 'code',
                    title: '角色编码'
                }, {
                    field: 'updateTimeStr',
                    title: '更新时间'
                },{
                    field: 'id',
                    title: '操作',
                    formatter: function (value, row, index) {
                        return '<a class="btn btn-primary btn-info" href="javascript:edit(' + value + ');" >编辑</a>&nbsp;<a class="btn btn-primary btn-warning" href="javascript:view(' + value + ');">查看</a>&nbsp;<a class="btn btn-primary btn-danger" href="javascript:remove(' + value + ');">删除</a>';
                    }
                }
            ],
            onCheck: function (row) {
                console.info("row: " + JSON.stringify(row));
            },
            onLoadSuccess: function (data) {
                console.log("data: " + JSON.stringify(data));
            }
//            detailFormatter: function () {
//                return "123123123";
//            }
//            queryParams: function (param) {
//                console.info("请求的数据: " + JSON.stringify(param));
//                return {"deptCode": $("#deptCode").val(), "offset": param.offset, "limit": param.limit};
//            }

        });
    });
    function refresh(){
        $table.bootstrapTable("refresh");
        layer.close(index);
    }


</script>

<@baseExec.addJS url="${base}/sys/role/newpage.htm" title="新增角色"></@baseExec.addJS>
<@baseExec.editJS url="${base}/sys/role/edit.htm" title="编辑角色"></@baseExec.editJS>
<@baseExec.deleteJS url="${base}/sys/role/delete.json"></@baseExec.deleteJS>

</body>