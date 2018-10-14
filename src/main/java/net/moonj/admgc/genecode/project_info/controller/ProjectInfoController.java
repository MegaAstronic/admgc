package net.moonj.admgc.genecode.project_info.controller;

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

import net.moonj.admgc.genecode.project_info.entity.ProjectInfo;
import net.moonj.admgc.genecode.project_info.service.IProjectInfoService;

@Controller
public class ProjectInfoController {
	@Autowired
	private IProjectInfoService project_infoService;
	
	private String prefix = "/genecode/project_info/";
	
	@RequestMapping("/pages/project_info/query")
	public Object queryTable(){
		return prefix + "query";
	}
	
	@RequestMapping("/pages/project_info/insert")
	public Object insert(){
		return prefix + "insert";
	}
	@RequestMapping("/pages/project_info/update/{pk}")
	public Object update(@PathVariable("pk") Integer pk,Map<String,Object> model){
		model.put("entity", project_infoService.getById(pk));
		return prefix + "update";
	}
	
	@RequestMapping(path = "/api/project_info/")
	public @ResponseBody Object doQuery(Integer page,Integer limit){
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		IPage<ProjectInfo> pages = project_infoService.page(new Page<>(page, limit), null);
		Map<String,Object> json = new HashMap<>();
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", pages.getRecords());
		return json;
	}
	

	
	@RequestMapping(path = "/pages/project_info/delete/do/{pk}")
	public Object doDelete(@PathVariable("pk") Integer pk){
		project_infoService.removeById(pk);
		return prefix + "query";
	}

	@RequestMapping(path = "/pages/project_info/insert/do")
	public Object doInsert(ProjectInfo entity){
		project_infoService.save(entity);
		return prefix + "query";
	}
	
	@RequestMapping(path = "/pages/project_info/update/do")
	public Object doUpdate(ProjectInfo entity){
		project_infoService.updateById(entity);
		return prefix + "query";
	}
}
