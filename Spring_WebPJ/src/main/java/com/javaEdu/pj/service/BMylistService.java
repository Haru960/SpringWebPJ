package com.javaEdu.pj.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.BDao;
import com.javaEdu.pj.dao.QADao;
import com.javaEdu.pj.dto.BDto;
import com.javaEdu.pj.dto.QADto;

public class BMylistService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();

		String pageNumber = request.getParameter("PN");
		String search = request.getParameter("search");
		String id = (String)session.getAttribute("id");
		String ck = request.getParameter("ck");
		
		if(pageNumber == null) {
			pageNumber = "1";
		}
		if(search == null) {
			search="";
		}
		if(id == null) {
			id = "";
		}
		if(ck == null) {
			ck = "mp";
		}
		System.out.println(pageNumber +"   pn");
		System.out.println(id +"   id");
		System.out.println(search +"   search");
		
		
//		if(ck.equals("mw")) {
			BDao bdao = new BDao();
			ArrayList<BDto> dtos = bdao.modify_list(pageNumber, search, id);
			model.addAttribute("list", dtos);
//		}else if(ck.equals("myq")) {
			QADao qadao = new QADao();
			ArrayList<QADto> qdtos = qadao.my_q_list(pageNumber, search, id);
			model.addAttribute("qlist", qdtos);
//		}else if(ck.equals("mya")) {
//			ArrayList<QADto> adtos = qadao.my_a_list(pageNumber, search, id);
//			request.setAttribute("alist", adtos);
//		}
		
		
	}
}
