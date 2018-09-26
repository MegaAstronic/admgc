package net.moonj.admgc.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface SchemaQueryMapper {
	public IPage<Map<String,Object>> showTablesPage(Page<Map<String,Object>> page,String dbname);
	
	public List<Map<String,Object>> showTables(String dbname);
	/**
	 * need data:
	 * data.dbname
	 * data.tableName
	 * @param data
	 * @return
	 */
	public IPage<Map<String,Object>> listColumn(Page<Map<String,Object>> page,Map<String,Object> data);

	public String getPrimaryKey(Map<String,String> data);

}
