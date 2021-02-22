 package com.javaEdu.pj.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.BDao;
import com.javaEdu.pj.dto.BDto;

public class BListService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("PN");
		String searchType= request.getParameter("searchType");
		String search= request.getParameter("search");
//		String targetPage = request.getParameter("targetPage");
		if(pageNumber == null) {
			pageNumber = "1";
		}
		if(searchType == null) {
			searchType="title";
		}
		if(search == null) {
			search="";
		}
//		if(targetPage ==null) {
//			targetPage = "1";
//		}
		if(searchType.equals("title")) {
			searchType = "bTitle";
		}
		else {
			searchType = "id";
		}
//		search = "%" +search +"%";
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list(pageNumber, searchType, search);
//		targetPage = String.valueOf(dao.targetPage(pageNumber, search, searchType));
		model.addAttribute("list", dtos);
//		request.setAttribute("targetPage", targetPage);
	}
	
}
