package com.javaEdu.pj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.BDao;

public class BDeleteService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		dao.delete(bId);
	}

}
