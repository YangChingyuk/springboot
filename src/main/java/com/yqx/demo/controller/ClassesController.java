package com.yqx.demo.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqx.demo.entity.Classes;
import com.yqx.demo.service.ClassesService;

@Controller
@RequestMapping("/Classes")
public class ClassesController {
	
	@Resource
	private ClassesService classesService;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	@RequestMapping("/add")
	public void add(Classes s) throws Exception {
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			classesService.add(s);
			jo.put("state", 0);
			jo.put("msg", "�ɹ�������¼");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "������¼ʧ��" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/deleteById")
	public void deleteById(int id) {
		classesService.deleteById(Classes.class,id);
	}
	@RequestMapping("/deleteMore")
	@ResponseBody
	public void deleteMore(String ids) throws Exception {
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			classesService.deleteMore(Classes.class,ids);
			jo.put("state", 0);
			jo.put("msg", "�ɹ�ɾ����¼");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "ɾ����¼ʧ��" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public void update(Classes s) throws Exception {
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			classesService.update(s);
			jo.put("state", 0);
			jo.put("msg", "�ɹ��޸ļ�¼");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "�޸ļ�¼ʧ��" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/queryById")
	@ResponseBody
	public Classes queryById(int id,String currentPage,Map<String,Object> map) {
		String qname = request.getParameter("qname");
		String qClassesname = request.getParameter("qClassesname");
		String qsex = request.getParameter("qsex");
		
		Classes s = classesService.queryById(Classes.class,id);
		map.put("Classes", s);
		map.put("currentPage", currentPage);
		map.put("qname", qname);
		map.put("qClassesname", qClassesname);
		map.put("qsex", qsex);
		return s;
	}
	@RequestMapping("/queryByPage")
	@ResponseBody
	public void queryByPage(String page) throws Exception {
		String qname = request.getParameter("qname");
		String qClassesname = request.getParameter("qClassesname");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");

		String currentPage = request.getParameter("page");
		String rows = request.getParameter("rows");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%'";
		}
		if (qClassesname != null && !qClassesname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and Classesname like '%" + qClassesname + "%'";
		}
		if (qsex != null && !qsex.equals("") && !qsex.equals("-1") && !qname.equalsIgnoreCase("null")) {
			condition += " and sex = " + qsex;
		}
		if (qbeginDate != null && !qbeginDate.equals("")) {
			condition += " and birthday >= '" + qbeginDate + "'";
		}
		if (qendDate != null && !qendDate.equals("")) {
			condition += " and birthday <= '" + qendDate + "'";
		}

		int sp = 1;

		int totals = classesService.getTotals(Classes.class);

		int pageSize = Integer.parseInt(rows);

		int pageCounts = totals / pageSize;
		if (totals % pageSize != 0) {
			pageCounts++;
		}
		try {
			sp = Integer.parseInt(currentPage);
		} catch (Exception e) {
			sp = 1;
		}
		if (sp > pageCounts) {
			sp = pageCounts;
		}
		if (sp < 1) {
			sp = 1;
		}
		List<Classes> list = classesService.queryByPage(Classes.class,sp, pageSize);

		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("total", totals);
		jo.put("rows", list);
		String json = JSON.toJSONString(jo);
		out.write(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Classes> queryAll(){
		List<Classes> list = classesService.queryAll(Classes.class);
		return list;
	}
	
	/*
	 * �������Ϊ���ڸ�ʽ
	 * */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
	            true));
	}
	
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}
}
