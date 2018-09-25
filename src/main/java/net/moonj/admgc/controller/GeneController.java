package net.moonj.admgc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.moonj.admgc.vo.GeneConfig;
import net.moonj.admgc.vo.GeneConfig.Function;
import net.moonj.admgc.vo.GeneConfig.ShowType;

@Controller
public class GeneController {
	
	
	private static final String prefix = "/geneMod/";
	@RequestMapping("/gene/table")
	public Object geneTableSelect(Map<String,Object> model,HttpServletRequest req){
		req.getSession().setAttribute("geneConfig", new GeneConfig());
		return prefix+"table";
	}
	
	@RequestMapping("/gene/config")
	public Object geneConfig(Map<String,Object> model,HttpServletRequest req,String tableName,HttpServletResponse resp) throws IOException{

		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(tableName==null || config == null){
			resp.sendRedirect("/gene/table");
			return null;
		}
		
		config.setTableName(tableName);
		model.put("tableName", tableName);
		return prefix+"config";
	}
	
	@RequestMapping("/gene/config/handle")
	public Object geneConfig(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		List<String> configAttr = req.getParameterMap().entrySet().stream().map(x->x.getKey()).collect(Collectors.toList());
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect("/gene/table");
			return null;
		}
		config.setFunctions(new ArrayList<>());
		config.setQueryColumns(new ArrayList<>());
		configAttr.forEach(e->{
			if(e.startsWith("func:")){
				config.getFunctions().add(GeneConfig.Function.valueOf(e.split(":")[1]));
			}else if(e.startsWith("col:")){
				config.getQueryColumns().add(e.split(":")[1]);
			}
		});
		resp.sendRedirect("/gene/insert");
		return null;
	}
	
	@RequestMapping("/gene/insert")
	public Object geneInsert(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect("/gene/table");
			return null;
		} else if(!config.getFunctions().contains(Function.insert)){
			resp.sendRedirect("/gene/update");
			return null;
		}
		model.put("columns", config.getColumnMsg().entrySet().stream().map(x->x.getKey()).collect(Collectors.toList()));
		model.put("columnMsg",config.getColumnMsg());
		
		
		
		return prefix+"insert";
	}
	
	@RequestMapping("/gene/insert/handle")
	public Object geneInsertHandle(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect("/gene/table");
			return null;
		}		
//		req.getParameterMap().entrySet().forEach(e->{
//			System.out.println("key:"+e.getKey()+" |  value:"+e.getValue()[0]);
//		});
		
		config.setInsertColumnShowType(new HashMap<>());
		req.getParameterMap().entrySet().forEach(e->{
			config.getInsertColumnShowType().put(e.getKey(), ShowType.valueOf(e.getValue()[0]));
		});
		
		return prefix+"update";
	}
	
	@RequestMapping("/gene/update")
	public Object geneUpdate(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect("/gene/table");
			return null;
		} else if(!config.getFunctions().contains(Function.insert)){
			resp.sendRedirect("/gene/do");
			return null;
		}	
		model.put("columns", config.getColumnMsg().entrySet().stream().map(x->x.getKey()).collect(Collectors.toList()));
		model.put("columnMsg",config.getColumnMsg());
		return prefix+"update";
	}
	
	@RequestMapping("/gene/update/handle")
	public Object geneUpdateHandle(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect("/gene/table");
			return null;
		}
//		req.getParameterMap().entrySet().forEach(e->{
//			System.out.println("key:"+e.getKey()+" |  value:"+e.getValue()[0]);
//		});
		
		config.setUpdateColumnShowType(new HashMap<>());
		req.getParameterMap().entrySet().forEach(e->{
			config.getUpdateColumnShowType().put(e.getKey(), ShowType.valueOf(e.getValue()[0]));
		});
		
		return null;
	}
	@RequestMapping("/gene/do")
	public Object geneDo(Map<String,Object> model,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config == null){
			resp.sendRedirect("/gene/table");
			return null;
		}
		//TODO
		return config;
	}
}
