<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>增加数据projectInfo</title>
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
  			<legend>Insert projectInfo</legend>
		</fieldset>
		<form class="layui-form" action="/pages/project_info/insert/do" method="POST">
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">proid</label>
				<div class="layui-input-block">
                        <input type="text" name="proid" required  lay-verify="" placeholder="请输入proid" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">name</label>
				<div class="layui-input-block">
                        <input type="text" name="name" required  lay-verify="" placeholder="请输入name" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">content</label>
				<div class="layui-input-block">
                        <textarea name="content" placeholder="请输入内容" class="layui-textarea"></textarea>

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">teachers</label>
				<div class="layui-input-block">
                        <textarea name="teachers" placeholder="请输入内容" class="layui-textarea"></textarea>

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">partner</label>
				<div class="layui-input-block">
                        <input type="text" name="partner" required  lay-verify="" placeholder="请输入partner" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">period</label>
				<div class="layui-input-block">
                        <input type="text" name="period" required  lay-verify="" placeholder="请输入period" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">price</label>
				<div class="layui-input-block">
                        <input type="text" name="price" required  lay-verify="" placeholder="请输入price" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">groupName</label>
				<div class="layui-input-block">
                        <input type="text" name="groupName" required  lay-verify="" placeholder="请输入groupName" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">place</label>
				<div class="layui-input-block">
                        <input type="text" name="place" required  lay-verify="" placeholder="请输入place" autocomplete="off" class="layui-input">

				</div>
			</div>
			 
			
			<div class="layui-form-item">
				<label class="layui-form-label">type</label>
				<div class="layui-input-block">
                        <input type="text" name="type" required  lay-verify="" placeholder="请输入type" autocomplete="off" class="layui-input">

				</div>
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
layui.use(['element','laydate','form'], function(){
  var element = layui.element;
  var laydate = layui.laydate;

});
</script>

</body>
</html>