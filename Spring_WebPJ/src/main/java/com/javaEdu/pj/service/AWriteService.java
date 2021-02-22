package com.javaEdu.pj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.QADao;

public class AWriteService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int qId = Integer.valueOf(request.getParameter("qId"));
		String id = request.getParameter("answer_id");
		String aTitle = request.getParameter("aTitle");
		String aContent = request.getParameter("aContent");
		
		QADao qaDao = new QADao();
		qaDao.answer_write(qId ,id, aTitle, aContent); 
		int aId = qaDao.get_aid(qId);
		qaDao.update_aid(qId, aId);
	}

}
