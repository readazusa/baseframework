
<#import "../../config/config.ftl" as config>
<#import "../../config/baseExecJS.ftl" as baseExec>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户管理</title>
<@config.bootCSS></@config.bootCSS>
<@config.themeCSS></@config.themeCSS>
<@config.fontCSS></@config.fontCSS>
<@config.bootTableCSS></@config.bootTableCSS>
<@config.jsTreeCSS></@config.jsTreeCSS>
<@config.baseCSS></@config.baseCSS>

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
                <div class="base-user-right">
                    <div class="row">
                        <table id="table"></table>
                    </div>
                    <div id="toolbar">
                        <a href="javascript:void(0);" class="btn btn-primary btn-xs"><i
                                class="icon iconfont"></i>新增</a>
                        <a href="javascript:remove();" class="btn btn-danger btn-xs"><i
                                class="icon iconfont"></i>删除</a>
                    </div>
                </div>

                <input type="hidden" id="deptCode">

            </div>
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
<script type="application/javascript">
    var $table = $("#table");
    $(function () {
        table = $('#table').bootstrapTable({
            method: "post",
            striped: true,
            pagination: true,
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            url: "${base}/sys/user/querypage.json",
            sidePagination: "server",
            search: true,
            showColumns: true,
            showRefresh: true,
            showToggle: true,
            showPaginationSwitch: true,
            toolbar: '#toolbar',
            clickToSelect: false,
            detailView: true,
            idField: "id",
            showColumns: true,
//            cardView:true,
            columns: [
                {
                    field: 'username',
                    title: '用户名'
                },
                {
                    field: 'name',
                    title: '姓名'
                }, {
                    field: 'mobile',
                    title: '联系电话'
                }, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                        var result = null;
                        if (value == 'male') {
                            result = "男";
                        } else {
                            result = "女";
                        }
                        return result;
                    }
                }, {
                    field: "position",
                    title: "职位"
                }, {
                    field:"birthdayStr",
                    title:"生日"
                },{
                    field: 'id',
                    title: '操作',
                    formatter: function (value, row, index) {
//                        console.log("value: " + JSON.stringify(value));
//                        console.log("row: " + JSON.stringify(row) + ", name: " + row.name);
//                        console.log("index: " + JSON.stringify(index));
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
            detailFormatter: function () {
                return "123123123";
            },
            queryParams: function (param) {
                console.info("请求的数据: " + JSON.stringify(param));
                return {"deptCode": $("#deptCode").val(), "offset": param.offset, "limit": param.limit};
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
            var id = node.id;    //部门编码
            $("#deptCode").val(id)
            reload();
        });
    });

    function reload() {
        $table.bootstrapTable("refresh")
    }


</script>

<@baseExec.addDeptJS url=""></@baseExec.addDeptJS>
<@baseExec.editJS url=""></@baseExec.editJS>
<@baseExec.deleteJS url=""></@baseExec.deleteJS>
</body>