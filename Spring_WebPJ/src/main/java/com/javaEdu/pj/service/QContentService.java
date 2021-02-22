package com.javaEdu.pj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.QADao;
import com.javaEdu.pj.dto.QADto;

public class QContentService implements Service {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String qId = request.getParameter("qId");
		QADao dao = new QADao();
		QADto qdto = dao.contentView(qId);
		
		
		if(qdto.getaId() >= 1) {
			QADto adto = dao.reply_view(qdto.getaId());
			model.addAttribute("reply_view", adto);
		}
		
		
		
		model.addAttribute("content_view", qdto);
	}
}
