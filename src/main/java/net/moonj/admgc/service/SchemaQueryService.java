package net.moonj.admgc.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.moonj.admgc.generator.mapper.SchemaQueryMapper;

@Service
public class SchemaQueryService {
	@Resource
	private SchemaQueryMapper schemaQueryMapper;
	
	@Value("${site.config.dbname}")
	private String dbname;
	
	public IPage<Map<String, Object>> showTablesPage(Page<Map<String, Object>> page){
		return schemaQueryMapper.showTablesPage(page,dbname);
	}
	
	public List<Map<String, Object>> showTables(){
		return schemaQueryMapper.showTables(dbname);
	}
	
	public IPage<Map<String, Object>> listColumn(Page<Map<String, Object>> page,String tableName){
		HashMap<String, Object> data = new LinkedHashMap<>();
		data.put("dbname", dbname);
		data.put("tableName", tableName);
		return schemaQueryMapper.listColumn(page,data);
	}
	
	public String getPrimaryKey(String tableName){
		Map<String,String> data = new LinkedHashMap<>();
		data.put("tableName", tableName);
		data.put("dbname", dbname);
		return schemaQueryMapper.getPrimaryKey(data);
	}
	
}
