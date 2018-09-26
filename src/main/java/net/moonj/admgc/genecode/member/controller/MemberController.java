package net.moonj.admgc.genecode.member.controller;

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

import net.moonj.admgc.genecode.member.entity.Member;
import net.moonj.admgc.genecode.member.service.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	private IMemberService memberService;
	
	private String prefix = "/genecode/member/";
	
	@RequestMapping("/pages/member/query")
	public Object queryTable(){
		return prefix + "query";
	}
	
	@RequestMapping("/pages/member/insert/{pk}")
	public Object insert(@PathVariable("pk") Serializable pk){
		return prefix + "insert";
	}
	@RequestMapping("/pages/member/update/{pk}")
	public Object update(@PathVariable("pk") Serializable pk){
		return prefix + "update";
	}
	
	@RequestMapping(path = "/api/member")
	public @ResponseBody Object doQuery(Integer page,Integer limit){
		if(page==null){
			page = 1;
		}
		if(limit == null){
			limit = 10;
		}
		IPage<Member> pages = memberService.page(new Page<>(page, limit), null);
		
//		System.out.println(pages.getRecords());
		
		Map<String,Object> json = new HashMap<>();
		json.put("code", "0");
		json.put("msg", "");
		json.put("count",pages.getTotal());
		json.put("data", pages.getRecords());
		return json;
	}
	@RequestMapping(path = "/pages/member/delete/do/{pk}")
	public Object doDelete(@PathVariable("pk") Serializable pk){
		memberService.removeById(pk);
		return prefix + "query";
	}

	@RequestMapping(path = "/pages/member/insert/do")
	public Object doInsert(Member entity){
		memberService.save(entity);
		return prefix + "query";
	}
	
	@RequestMapping(path = "/pages/member/update/do")
	public Object doUpdate(Member entity){
		memberService.updateById(entity);
		return prefix + "query";
	}
}
