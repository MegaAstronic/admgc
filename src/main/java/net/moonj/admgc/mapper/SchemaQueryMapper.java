package net.moonj.admgc.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface SchemaQueryMapper {
	public IPage<Map<String,Object>> showTablesPage(Page<Map<String,Object>> page);
	
	public List<Map<String,Object>> showTables();
}
