package net.moonj.admgc.interceptor;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 

	@Resource
	private SidebarTopnavInterceptor sidebarTopnavInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sidebarTopnavInterceptor).addPathPatterns("/**").excludePathPatterns("/**/*.js","/**/*.css");
    }
}
