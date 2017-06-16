<#import "../../config/config.ftl" as config>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>部门</title>
    <@config.jsTreeCSS></@config.jsTreeCSS>
    <@config.baseCSS></@config.baseCSS>
</head>

<body>

<div class="container  mag-top">
    <div>
        <div id="tree">
        </div>
    </div>

</div>
<@config.jqueryJS></@config.jqueryJS>
<@config.jsTree></@config.jsTree>
<script type="application/javascript">

    $(function () {
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
            var name = node.text;
            setDeptInfo(id,name)
        });
    });


    function setDeptInfo(deptCode,deptName){
        parent.setDeptInfo(deptCode,deptName);
    }



</script>

</body>