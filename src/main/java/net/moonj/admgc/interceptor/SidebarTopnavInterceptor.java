package net.moonj.admgc.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import net.moonj.admgc.service.SiteStructure;

@Component
public class SidebarTopnavInterceptor implements HandlerInterceptor {

	@Resource
	private SiteStructure siteStructure;
	
	

	private static final Logger logger = LoggerFactory.getLogger(SidebarTopnavInterceptor.class);

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView==null || modelAndView.getModel() == null){
			logger.debug("拦截到controller以外的请求 :"+request.getServletPath());
		}else{
			siteStructure.modelPut(modelAndView.getModel());
		}
		
	}
	
}
