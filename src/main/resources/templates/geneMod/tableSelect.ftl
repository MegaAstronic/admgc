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
  			<legend>Tables in database</legend>
		</fieldset>
    	<table class="layui-table" lay-data="{height:315, url:'/api/table/show', page:true, id:'test'}" lay-filter="test">
		  <thead>
		    <tr>
		      <th lay-data="{field:'TABLE_NAME', width:200}">表名</th>
		      <th lay-data="{field:'TABLE_ROWS', width:80, sort: true}">行数</th>
		      <th lay-data="{field:'ENGINE', width:100}">引擎</th>
		      <th lay-data="{field:'TABLE_TYPE', width:120}">表类型</th>
		      <th lay-data="{field:'TABLE_COMMENT'}">注释</th>
		    </tr>
		  </thead>
		</table>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>

<script>
//JavaScript代码区域
layui.use(['element','table'], function(){
  var element = layui.element;
  
});
</script>
</body>
</html>