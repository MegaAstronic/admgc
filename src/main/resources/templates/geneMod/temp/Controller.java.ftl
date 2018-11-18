[#ftl]
package ${package.Controller};

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.moonj.admgc.genecode.${table.name}.entity.${table.entityName};
import net.moonj.admgc.genecode.${table.name}.service.${table.serviceName};

@Controller
public class ${table.controllerName} {
	[#list table.fields as field]
		[#if field.keyFlag]
			[#assign pkType="${field.columnType.type}"/]
		[/#if]		
	[/#list]	
	@Autowired
	private ${table.serviceName} ${table.name}Service;
	
	private String prefix = "/genecode/${table.name}/";
	
	@RequestMapping("/pages/${table.name}/query")
	public Object queryTable(){
		return prefix + "query";
	}
	
	@RequestMapping("/pages/${table.name}/insert")
	public Object insert(){
		return prefix + "insert";
	}
	@RequestMapping("/pages/${table.name}/update/{pk}")
	public Object update(@PathVariable("pk") ${pkType} pk,Map<String,Object> model){
		model.put("entity", ${table.name}Service.getById(pk));
		return prefix + "update";
	}
	
	@RequestMapping(path = "/api/${table.name}")
	public @ResponseBody Object doQuery(Integer page,Integer limit){
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		IPage<${table.entityName}> pages = ${table.name}Service.page(new Page<>(page, limit), null);
		Map<String,Object> json = new HashMap<>();
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", pages.getRecords());
		return json;
	}
	
	private final String QUERY_URI = "/pages/${table.name}/query";
	private final String MSG_URI = "/msg";
	
	@RequestMapping(path = "/pages/${table.name}/delete/do/{pk}")
	public Object doDelete(@PathVariable("pk") ${pkType} pk,ModelMap map){
		boolean ans = ${table.name}Service.removeById(pk);
		map.put("msg", "删除"+(ans?"成功":"失败"));
		map.put("url",QUERY_URI);
		return MSG_URI;
	}

	@RequestMapping(path = "/pages/${table.name}/insert/do")
	public Object doInsert(${table.entityName} entity,ModelMap map){
		boolean ans = ${table.name}Service.save(entity);
		map.put("msg", "新增数据"+(ans?"成功":"失败"));
		map.put("url",QUERY_URI);
		return MSG_URI;
	}
	
	@RequestMapping(path = "/pages/${table.name}/update/do")
	public Object doUpdate(${table.entityName} entity,ModelMap map){
		boolean ans = ${table.name}Service.updateById(entity);
		map.put("msg", "更新数据"+(ans?"成功":"失败"));
		map.put("url",QUERY_URI);
		return MSG_URI;
	}
}
