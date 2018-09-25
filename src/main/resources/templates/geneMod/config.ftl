<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>生成</title>
  <link rel="stylesheet" href="/layui/css/layui.css">
  <script src="/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

<#include "/comp/topnav.ftl">  
<#include "/comp/sidebar.ftl"/>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  			<legend>columns</legend>
		</fieldset>
		<form class="layui-form" action="/gene/config/handle">
			<table class="layui-table" lay-data="{height:315, url:'/api/table/column/${tableName}', id:'tablecolumn'}" lay-filter="test">
			  <thead>
			    <tr>
			      <th lay-data="{field:'COLUMN_NAME', width:200}">列名</th>
			      <th lay-data="{field:'DATA_TYPE', width:150}">类型</th>
			      <th lay-data="{field:'CHARACTER_MAXIMUM_LENGTH', width:100}">字符长度</th>
			      <th lay-data="{field:'IS_NULLABLE', width:120}">可以为空</th>
			      <th lay-data="{field:'COLUMN_DEFAULT',width:200}">默认值</th>
			      <th lay-data="{field:'COLUMN_COMMENT',width:200}">注释</th>
			      <th lay-data="{field:'COLUMN_COMMENT',width:100 , templet:'#tablefunc'}">需要查询</th>
			    </tr>
			  </thead>
			</table>
			<div class="layui-form-item">
				<label class="layui-form-label">功能选择</label>
				<input type="checkbox" name="func:query" title="查询" checked disabled>
				<input type="checkbox" name="func:insert" title="增加" checked>
				<input type="checkbox" name="func:update" title="修改" checked> 
				<input type="checkbox" name="func:delete" title="删除" checked> 
			</div>
			
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
layui.use(['element','table','form'], function(){
  var element = layui.element;
  
});
</script>

<script type="text/html" id="tablefunc">
   <input type="checkbox" name="col:{{d.COLUMN_NAME}}" lay-skin="switch" lay-text="ON|OFF" checked="true">
</script>	

</body>
</html>