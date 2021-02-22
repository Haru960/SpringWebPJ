package com.javaEdu.pj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.BDao;
import com.javaEdu.pj.dao.FDao;
import com.javaEdu.pj.dto.BDto;
import com.javaEdu.pj.dto.FDto;

public class BContentService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		FDto fDto = null;
		System.out.println(dto.getfId());
		if(dto.getfId() != -1) {
			FDao fDao = new FDao();
			System.out.println("==============================");
			fDto = fDao.getFileName(dto.getfId());
		}
		System.out.println("==============================file 잘넘어왔는가?" );
		request.setAttribute("content_view", dto);
		request.setAttribute("fileInfo", fDto);
	}
	
}
