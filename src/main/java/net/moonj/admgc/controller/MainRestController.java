package net.moonj.admgc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.JSONPObject;

import net.moonj.admgc.service.SchemaQueryService;

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
		
		Map<String,Object> json = new HashMap<>();
		IPage<Map<String,Object>> pages = schemaQueryService.showTablesPage(new Page<>(page, limit));
		List<Map<String,Object>> queryAnswer = pages.getRecords();
		
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", queryAnswer);
		return json;
	}
	
	@RequestMapping("/api/table/column/{tableName}")
	public Object listColumn(Integer page,Integer limit,@PathVariable("tableName") String tableName){
		
		
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		
		Map<String,Object> json = new HashMap<>();
		IPage<Map<String,Object>> pages = schemaQueryService.listColumn(new Page<>(page, limit),tableName);
		List<Map<String,Object>> queryAnswer = pages.getRecords();
		
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", queryAnswer);
		return json;
	}
}
