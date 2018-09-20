package net.moonj.admgc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.JSONPObject;

import net.moonj.admgc.service.SchemaQueryService;

@RestController
public class MainRestController {
	
	@Resource
	private SchemaQueryService schemaQueryService;
	
	@RequestMapping("/api/table/show")
	public Object showTables(Integer page,Integer limit){
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		
		Map<String,Object> json = new HashMap<>();
		List<Map<String,Object>> queryAnswer = schemaQueryService.showTablesPage(new Page<>(page, limit));
		
		json.put("code", "0");
		json.put("msg", "");
		json.put("count", queryAnswer.size());
		json.put("data", queryAnswer);
		return json;
	}
}
