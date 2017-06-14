<#import "../../template/template.ftl" as template>
<#import "../../config/config.ftl" as config>
<#import "../../config/baseExecJS.ftl" as baseExecJS>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>部门管理</title>
<@config.bootCSS></@config.bootCSS>
<@config.themeCSS></@config.themeCSS>
<@config.fontCSS></@config.fontCSS>
<@config.bootTableCSS></@config.bootTableCSS>
<@config.jsTreeCSS></@config.jsTreeCSS>
<@config.layerCSS></@config.layerCSS>
<@config.baseCSS></@config.baseCSS>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="nav-md" style="overflow: hidden">
<div class="container body">
    <div class="main_container">
    <@template.mainleft></@template.mainleft>
    <@template.mainTop></@template.mainTop>
        <div class="right_col" role="main" style="overflow-y: auto">
            <div class="base-dept">
                <div class="base-dept-left">
                    <div id="tree">
                    </div>
                </div>
                <div class="base-dept-right">
                    <div class="row">
                        <table id="table"></table>
                    </div>
                    <div id="toolbar">
                        <a href="javascript:addDept();" class="btn btn-primary btn-xs"><i
                                class="icon iconfont"></i>新增</a>
                    <#--<a href="javascript:remove();" class="btn btn-danger btn-xs"><i-->
                    <#--class="icon iconfont"></i>删除</a>-->
                    </div>
                </div>
            </div>
            <input type="hidden" id="parentCode" value="0000">
        </div>
    <@template.mainFooter></@template.mainFooter>
    </div>
</div>

<@config.jqueryJS></@config.jqueryJS>
<@config.bootJS></@config.bootJS>
<@config.themeJS></@config.themeJS>
<@config.bootTableJS></@config.bootTableJS>
<@config.bootTableJS_ZH_CN></@config.bootTableJS_ZH_CN>
<@config.jsTree></@config.jsTree>
<@config.layerJS></@config.layerJS>
<script type="application/javascript">
    var index;
    layui.use(['layer', 'form'], function () {
        layer = layui.layer
    });

    var $table = $("#table");


    $(function () {

        table = $('#table').bootstrapTable({
            method: "post",
            striped: true,
            url: "${base}/sys/dept/loaddeptbyparentcode.json",
            sidePagination: "server",
            search: true,
            showColumns: true,
            showRefresh: true,
            showToggle: true,
            showPaginationSwitch: true,
            toolbar: '#toolbar',
            clickToSelect: false,
//            detailView: true,
            showColumns: true,
            columns: [
                {
                    field: 'name',
                    title: '部门名称'
                },
                {
                    field: 'phone',
                    title: '部门电话'
                }, {
                    field: 'leader',
                    title: '部门领导'
                }, {
                    field: 'leaderMobile',
                    title: '领导电话',
                },
                {
                    field: 'price',
                    title: '操作',
                    formatter: function (value, row, index) {
                        return 1;
                    }
                }
            ],
            onCheck: function (row) {
                console.info("row: " + JSON.stringify(row));
            },
            onLoadSuccess: function (data) {
                console.log("data: " + JSON.stringify(data));
            },

            queryParams: function (param) {
                console.info("请求的数据: " + JSON.stringify(param));
                return {"parentcode": "0000"};

            }
        });

        $("#tree").jstree({
            "animation": 0,
            "check_callback": true,
            "themes": {"stripes": true},
            'core': {
                'data': {
                    "url": function (node) {
                        console.info("异步的数据: " + node.id);
                        var parentCode;
                        if ("#" == node.id) {
                            parentCode = "root";
                        } else {
                            parentCode = node.id
                        }
                        return "${base}/sys/dept/loaddepttreebyparent.json?parentcode=" + parentCode;
                    },
                    "data": function (node) {
                        console.info("id: " + node.id);
                        return {'id': node.id};
                    }
                }
            }
        });

        $('#tree').bind('activate_node.jstree', function (obj, e) {
            var node = e.node;
            var id = node.id;
        });
    });


    function refresh() {
        layer.close(index);
    }

    function viewChildrenDept(parentCode) {

    }

    function remove() {
        alert("执行删除");
    }

    function onChoiceAll() {
        var sel = $table.bootstrapTable("refresh", {
            query: {
                name: "一二三"
            }
        });
        console.info(JSON.stringify(sel));
    }

    function addDept() {
        add($("#parentCode").val());
    }

</script>


<@baseExecJS.addDeptJS url="${base}/sys/dept/newpage.htm" title="新增部门"></@baseExecJS.addDeptJS>
<@baseExecJS.editJS url="${base}/sys/dept/editpage.htm"></@baseExecJS.editJS>
<@baseExecJS.deleteJS  url="${base}/sys/dept/remove.htm"></@baseExecJS.deleteJS>
</body>