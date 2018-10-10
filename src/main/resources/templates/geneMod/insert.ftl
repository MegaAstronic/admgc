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
  			<legend>Insert</legend>
		</fieldset>
		<form class="layui-form" action="/pages/gene/insert/handle">
			
		<#list columns as col>
			
			<div class="layui-form-item">
				<label class="layui-form-label">${col}</label>
				<div class="layui-input-block">
					<select name="${col}" lay-verify="">
					  <option value="shortText" ${(columnMsg[col].DATA_TYPE == "varchar")?string('selected', '')}>短文本(一行)</option>
					  <option value="longText" ${(columnMsg[col].DATA_TYPE == "text")?string('selected', '')} >长文本(文本域)</option>
					  <option value="datetime" ${(columnMsg[col].DATA_TYPE == "datetime")?string('selected', '')} >日期(日期选择器)</option>
					</select>  
				</div>
			</div>
			 
		</#list>
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