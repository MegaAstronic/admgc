package net.moonj.admgc.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.moonj.admgc.CodeGenerator;
import net.moonj.admgc.service.SchemaQueryService;
import net.moonj.admgc.service.SiteStructure;
import net.moonj.admgc.util.CompilerUtils;
import net.moonj.admgc.util.TemplateUtils;


@Controller
public class MainController {
	
	@Resource
	private SiteStructure siteStructure;
	

	
	
	
	@RequestMapping("/tableSelect")
	public Object geneConfig(Map<String,Object> model){
		return "geneMod/tableSelect";
	}
	
	@RequestMapping("/")
	public Object index(Map<String,Object> model) throws Exception{
//		TemplateUtils.srcProcess("inner/table.ftl", new HashMap<>(), "templates/tablex.ftl");
//		CompilerUtils.mvnPackage();
		return "index";
	}
	
	@RequestMapping("/table")
	public Object tb(Map<String,Object> model) throws Exception{
		return "tablex";
	}
	
	@RequestMapping("/gc")
	public String gc() throws IOException{
		CodeGenerator.main(null);
		CompilerUtils.mvnPackage();
		return "call successfully";
	}
}
