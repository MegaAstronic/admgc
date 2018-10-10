<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
 
    <title>登录页</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
 
</head>
<body>
 
<div class="login-main">
	
    <header class="layui-elip">登录</header>
    ${errmsg}
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="username" required lay-verify="required" placeholder="用户名" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input">
        </div>
        <img id="kaptcha" src="/kaptcha/render" onclick='this.src="/kaptcha/render"+"?"+Math.random()'/>
        <div class="layui-input-inline">
            <input type="text" name="code" required lay-verify="required" placeholder="验证码" autocomplete="off"
                   class="layui-input">
        </div>
        
        <div class="layui-input-inline login-btn">
            <button lay-submit lay-filter="login" class="layui-btn">登录</button>
        </div>
        

        <!--<div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-primary">QQ登录</button>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-normal">微信登录</button>
        </div>-->
        <!-- <p><a href="register.html" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p> -->
    </form>
</div>
 
 
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form','layer','jquery'], function () {
 
        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;
        form.on('submit(login)',function (data) {
            $.ajax({
                url:'/login/do',
                data:data.field,
                dataType:'text',
                type:'post',
                success:function (data) {
                	console.log("ajax data= "+data);
                    if (data == '1'){
                        location.href = "/";
                    }else if(data == '0'){
                        layer.msg('登录名或密码错误');
                    }else if(data == '-1'){
                    	layer.msg('验证码错误');
                    	$("#kaptcha").attr('src','/kaptcha/render'+"?"+Math.random());
                    }else{
                    	layer.msg('错误的返回数据');
                    }
                }
            })
            return false;
        })
 
    });
</script>
</body>
</html>