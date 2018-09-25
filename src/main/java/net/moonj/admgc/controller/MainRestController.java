package net.moonj.admgc.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.moonj.admgc.service.SchemaQueryService;
import net.moonj.admgc.vo.GeneConfig;

@RestController
public class MainRestController {
	
	@Resource
	private SchemaQueryService schemaQueryService;
	
	
	

	private static final Logger logger = LoggerFactory.getLogger(MainRestController.class);

	@RequestMapping(path = "/api/table/show",method = RequestMethod.GET)
	public Object showTables(Integer page,Integer limit){
		
//		logger.info("showtable - page = "+page + " limit = " +limit);
		
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		
		
		IPage<Map<String,Object>> pages = schemaQueryService.showTablesPage(new Page<>(page, limit));
		List<Map<String,Object>> queryAnswer = pages.getRecords();
		Map<String,Object> json = new HashMap<>();
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", queryAnswer);
		return json;
	}
	
	@RequestMapping("/api/table/column/{tableName}")
	public Object listColumn(Integer page,Integer limit,@PathVariable("tableName") String tableName,HttpServletRequest req){
		
		
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 100;
		}
		
		Map<String,Object> json = new HashMap<>();
		IPage<Map<String,Object>> pages = schemaQueryService.listColumn(new Page<>(page, limit),tableName);
		List<Map<String,Object>> queryAnswer = pages.getRecords();
		
		Map<String,Map<String,Object>> columnMsg = new LinkedHashMap<>();
		GeneConfig config = (GeneConfig) req.getSession().getAttribute("geneConfig");
		if(config!=null){
			queryAnswer.forEach(x->{
				columnMsg.put((String) x.get("COLUMN_NAME"), x);
			});
		}
		config.setColumnMsg(columnMsg);
		
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", queryAnswer);
		return json;
	}
}
