package com.javaEdu.pj.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.BDao;

public class BModifyService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request =(HttpServletRequest)map.get("request");
		
		String bId = request.getParameter("bId");
		String id = request.getParameter("id");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		System.out.println("수정 서비스 값 ::::::::::::::::::::::" +bTitle);
		System.out.println("수정 서비스 값 ::::::::::::::::::::::" +bContent);
		System.out.println("수정 서비스 값 ::::::::::::::::::::::" +id);
		System.out.println("수정 서비스 값 ::::::::::::::::::::::" +bId);
		
		BDao dao = new BDao();
		dao.modify(bId, id, bTitle, bContent);
	}
	
}
