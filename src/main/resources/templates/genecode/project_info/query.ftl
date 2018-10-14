<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>查询projectInfo</title>
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
  			<legend>query projectInfo</legend>
		</fieldset>
		
		<a href="/pages/project_info/insert" class="layui-btn layui-btn">增加数据</a>

		
    	<table class="layui-table" lay-data="{height:315, url:'/api/project_info/', page:{limit:10}, id:'tabletable'}" lay-filter="test">
		  <thead>
		    <tr>
		      <th lay-data="{field:'proid'}">proid</th>
		      <th lay-data="{field:'name'}">name</th>
		      <th lay-data="{field:'content'}">content</th>
		      <th lay-data="{field:'teachers'}">teachers</th>
		      <th lay-data="{field:'partner'}">partner</th>
		      <th lay-data="{field:'period'}">period</th>
		      <th lay-data="{field:'price'}">price</th>
		      <th lay-data="{field:'groupName'}">group_name</th>
		      <th lay-data="{field:'place'}">place</th>
		      <th lay-data="{field:'type'}">type</th>
          		<th lay-data="{width:250 ,templet:'#tablefunc'}" >功能</th>
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
<script type="text/html" id="tablefunc">
  <a href="/pages/project_info/update/{{d.proid}}" class="layui-btn layui-btn layui-btn-xs">修改</a>
  <a href="/pages/project_info/delete/do/{{d.proid}}" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
</script>
      
</body>
</html>