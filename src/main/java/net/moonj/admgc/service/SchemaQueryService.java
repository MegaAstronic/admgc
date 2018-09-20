package net.moonj.admgc.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.moonj.admgc.mapper.SchemaQueryMapper;

@Service
public class SchemaQueryService {
	@Resource
	private SchemaQueryMapper schemaQueryMapper;
	
	public List<Map<String, Object>> showTablesPage(Page<Map<String, Object>> page){
		return schemaQueryMapper.showTablesPage(page).getRecords();
	}
	
	public List<Map<String, Object>> showTables(){
		return schemaQueryMapper.showTables();
	}
}
