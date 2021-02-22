package com.javaEdu.pj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.QADao;

public class QModifyService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String qId = request.getParameter("qId");
		String id = request.getParameter("id");
		String qTitle = request.getParameter("qTitle");
		String qContent = request.getParameter("qContent");
		
		QADao dao = new QADao();
		dao.Q_modify(qId, id, qTitle, qContent);
	}

}
