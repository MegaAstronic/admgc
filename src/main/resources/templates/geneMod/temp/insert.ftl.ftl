[#ftl]
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>增加数据${config.entityName}</title>
  <link rel="stylesheet" href="/layui/css/layui.css">
  <script src="/layui/layui.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

<#include "/comp/topnav.ftl">  
<#include "/comp/sidebar.ftl"/>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  			<legend>Insert ${config.entityName}</legend>
		</fieldset>
		<form class="layui-form" action="/pages/${config.tableName}/insert/do" method="POST">
			
		[#list config.insertColumnShowType?keys as key]
			
			<div class="layui-form-item">
				<label class="layui-form-label">${config.queryColumnsNamingMap[key]}</label>
				<div class="layui-input-block">
                    [#assign showType = config.insertColumnShowType[key]]
					[#if (showType == "shortText")]
                        <input type="text" name="${config.queryColumnsNamingMap[key]}" required  lay-verify="" placeholder="请输入${config.queryColumnsNamingMap[key]}" autocomplete="off" class="layui-input">
                    [#elseif (showType == "longText")/]
                        <textarea name="${config.queryColumnsNamingMap[key]}" placeholder="请输入内容" class="layui-textarea"></textarea>
                    [#elseif (showType == "datetime")/]
                        <input type="text" class="layui-input" id="date-${config.queryColumnsNamingMap[key]}" name="${config.queryColumnsNamingMap[key]}">
                    [#elseif (showType == "richText")/]
                    	<input type="text" name="${config.queryColumnsNamingMap[key]}" required  lay-verify="" placeholder="请输入${config.queryColumnsNamingMap[key]}" >
                    	<script type="text/javascript">
                    		var ue = UE.getEditor('editor');
                    	</script>
                    [/#if]

				</div>
			</div>
			 
		[/#list]
		   <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>

<script>
//JavaScript代码区域
layui.use(['element','laydate','form'], function(){
  var element = layui.element;
  var laydate = layui.laydate;

    [#list config.insertColumnShowType?keys as key]
        [#assign showType = config.insertColumnShowType[key]]
        [#if (showType == "datetime")]
            //执行一个laydate实例
            laydate.render({
                elem: '#date-${config.queryColumnsNamingMap[key]}' //指定元素
            });
        [/#if]
    [/#list]
});
</script>

</body>
</html>