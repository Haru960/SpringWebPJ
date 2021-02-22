package com.javaEdu.pj.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.QADao;
import com.javaEdu.pj.dto.QADto;

public class QAListService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("PN");
		String searchType= request.getParameter("searchType");
		String search= request.getParameter("search");
		if(pageNumber == null) {
			pageNumber = "1";
		}
		if(searchType == null) {
			searchType="qTitle";
		}
		if(search == null) {
			search="";
		}
		if(searchType.equals("title")) {
			searchType = "qTitle";
		}
		else {
			searchType = "id";
		}
		QADao dao = new QADao();
		ArrayList<QADto> dtos = dao.list(pageNumber, searchType, search);
		model.addAttribute("list", dtos);
	}
	
}
