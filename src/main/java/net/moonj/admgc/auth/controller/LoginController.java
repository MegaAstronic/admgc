package net.moonj.admgc.auth.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	
	@RequestMapping("/login")
	public String login(String errmsg,Map<String,Object> model){
		if(errmsg == null){
			errmsg = "";
		}
		model.put("errmsg", errmsg);
		return "auth/login";
	}
	
	
	@RequestMapping("/login/do")
	public @ResponseBody String loginDo(String username,String password,HttpServletResponse resp) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try{
			subject.login(token);
			return "1";
		}catch(AuthenticationException e){
			return "0";
		}
		
	}
}
