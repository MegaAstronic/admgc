package ${package.Controller};

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.moonj.admgc.genecode.${table.name}.entity.${table.entityName};
import net.moonj.admgc.genecode.${table.name}.service.${table.serviceName};

@Controller
public class ${table.controllerName} {
	
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
	public Object update(@PathVariable("pk") Serializable pk){
		return prefix + "update";
	}
	
	@RequestMapping(path = "/api/${table.name}/")
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
	@RequestMapping(path = "/pages/${table.name}/delete/do/{pk}")
	public Object doDelete(@PathVariable("pk") Serializable pk){
		${table.name}Service.removeById(pk);
		return prefix + "query";
	}

	@RequestMapping(path = "/pages/${table.name}/insert/do")
	public Object doInsert(${table.entityName} entity){
		${table.name}Service.save(entity);
		return prefix + "query";
	}
	
	@RequestMapping(path = "/pages/${table.name}/update/do")
	public Object doUpdate(${table.entityName} entity){
		${table.name}Service.updateById(entity);
		return prefix + "query";
	}
}
