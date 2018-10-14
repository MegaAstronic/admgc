package net.moonj.admgc.generator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.moonj.admgc.generator.GeneConfig;
import net.moonj.admgc.generator.GeneConfig.Function;
import net.moonj.admgc.generator.GeneConfig.ShowType;
import net.moonj.admgc.generator.service.GeneratorService;
import net.moonj.admgc.service.SchemaQueryService;
import net.moonj.admgc.util.CompilerUtils;

@Controller
@RequestMapping("/pages")
public class GeneController {
	
	
	@Resource
	private SchemaQueryService schemaQueryService;
	
	@Resource
	private GeneratorService generatorService;
	
	private final String redirectPrefix = "/pages";
	
	private static final String prefixFTL = "/geneMod/";
	@RequestMapping("/gene/table")
	public Object geneTableSelect(Map<String,Object> model,HttpServletRequest req){
		req.getSession().setAttribute("geneConfig", new GeneConfig());
		return prefixFTL+"table";
	}
	
	@RequestMapping("/gene/config")
	public Object geneConfig(Map<String,Object> model,HttpServletRequest req,String tableName,HttpServletResponse resp) throws IOException{

		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(tableName==null || config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		}
		
		config.setTableName(tableName);
		config.setPrimaryKey(schemaQueryService.getPrimaryKey(tableName));
//		System.out.println("[[pk]]"+config.getPrimaryKey());
		model.put("tableName", tableName);
		return prefixFTL+"config";
	}
	
	@RequestMapping("/gene/config/handle")
	public Object geneConfig(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		}
		config.setFunctions(new ArrayList<>());
		config.setQueryColumns(new ArrayList<>());
		config.setAliasMap(new LinkedHashMap<String, String>());
		req.getParameterMap().entrySet().forEach(x->{
			String k = x.getKey();
			String v = x.getValue()[0];
			if(k.startsWith("func:")){
				config.getFunctions().add(GeneConfig.Function.valueOf(k.split(":")[1]));
			}else if(k.startsWith("col:")){
				config.getQueryColumns().add(k.split(":")[1]);
			}else if (k.startsWith("alias:")){
				config.getAliasMap().put(k.split(":")[1], v);
			}
		});
		resp.sendRedirect(redirectPrefix+"/gene/insert");
		return null;
	}
	
	@RequestMapping("/gene/insert")
	public Object geneInsert(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		} else if(!config.getFunctions().contains(Function.insert)){
			resp.sendRedirect(redirectPrefix+"/gene/update");
			return null;
		}
		List<String> columns = config.getColumnMsg().entrySet().stream().map(x->x.getKey()).collect(Collectors.toList());
		model.put("columns", columns );
		model.put("columnMsg",config.getColumnMsg());
		
		
		
		return prefixFTL+"insert";
	}
	
	@RequestMapping("/gene/insert/handle")
	public Object geneInsertHandle(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		}		
//		req.getParameterMap().entrySet().forEach(e->{
//			System.out.println("key:"+e.getKey()+" |  value:"+e.getValue()[0]);
//		});
		
		config.setInsertColumnShowType(new LinkedHashMap<>());
		req.getParameterMap().entrySet().forEach(e->{
			config.getInsertColumnShowType().put(e.getKey(), ShowType.valueOf(e.getValue()[0]));
		});
		
		resp.sendRedirect(redirectPrefix+"/gene/update");
		return null;
	}
	
	@RequestMapping("/gene/update")
	public Object geneUpdate(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		} else if(!config.getFunctions().contains(Function.update)){
			resp.sendRedirect(redirectPrefix+"/gene/do");
			return null;
		}	
		List<String> columns = config.getColumnMsg().entrySet().stream().map(x->x.getKey()).collect(Collectors.toList());
//		System.out.println("[columns]"+columns);
		model.put("columns", columns );
		model.put("columnMsg",config.getColumnMsg());
		return prefixFTL+"update";
	}
	
	@RequestMapping("/gene/update/handle")
	public Object geneUpdateHandle(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		}
//		req.getParameterMap().entrySet().forEach(e->{
//			System.out.println("key:"+e.getKey()+" |  value:"+e.getValue()[0]);
//		});
		
		config.setUpdateColumnShowType(new LinkedHashMap<>());
		req.getParameterMap().entrySet().forEach(e->{
			config.getUpdateColumnShowType().put(e.getKey(), ShowType.valueOf(e.getValue()[0]));
		});
		resp.sendRedirect(redirectPrefix+"/gene/do");
		return null;
	}
	@RequestMapping("/gene/do")
	public @ResponseBody Object geneDo(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws Exception{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect(redirectPrefix+"/gene/table");
			return null;
		}
		//TODO
		generatorService.generate((GeneConfig) req.getSession().getAttribute("geneConfig"));
		CompilerUtils.mvnPackage();
		return config;
	}
}
