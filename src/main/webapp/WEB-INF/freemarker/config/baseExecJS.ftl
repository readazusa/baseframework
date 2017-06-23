<#macro addJS url title=" " width="50%" height="70%">

<script type="application/javascript">
    function add() {
        index = layer.open({
            type: 2,
            title: "${title}",
            shadeClose: true,
            shade: 0.8,
            shift: 2,
            maxmin: true,
            area: ['${width}', '${height}'],
            content: "${url}"
        });
    }
</script>

</#macro>

<#macro addDeptJS url title=" " width="50%" height="70%">
<script type="application/javascript">
    function add(parentCode) {
        index = layer.open({
            type: 2,
            title: "${title}",
            shadeClose: true,
            shade: 0.8,
            shift: 2,
            maxmin: true,
            area: ['${width}', '${height}'],
            content: "${url}?parentcode="+parentCode
        });
    }
</script>
</#macro>

<#macro deleteJS url title="">
<script type="application/javascript">
    function remove(id) {
        $.ajax({
            url: "${url}?id=" + id,
            success: function (resp) {
                if ("0000" == resp.code) {
                    index = layer.msg(resp.msg, function (index) {
                        refresh();
                    })
                } else {
                    layer.alert(resp.msg);
                }
            },
            error: function (resp) {
                layer.alert("${title}失败");
            }
        })
    }
</script>
</#macro>

<#macro editJS url title="" width="50%" height="70%">
<script type="application/javascript">

    function edit(id) {
        index = layer.open({
            type: 2,
            title: "${title}",
            shadeClose: true,
            shade: 0.8,
            shift: 2,
            maxmin: true,
            area: ['${width}', '${height}'],
            content: "${url}?id=" + id
        });
    }
</script>
</#macro>

<#macro viewJS url title="" width="50%" height="70%">
<script type="application/javascript">
    function view(id) {
        index = layer.open({
            type: 2,
            title: "${title}",
            shadeClose: true,
//            shade: 0.8,
//            shift: 2,
            maxmin: true,
            area: ['${width}', '${height}'],
            content: "${url}?id=" + id
        });
    }
</script>
</#macro>
