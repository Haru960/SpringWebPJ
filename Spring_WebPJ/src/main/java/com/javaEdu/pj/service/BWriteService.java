package com.javaEdu.pj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.javaEdu.pj.dao.BDao;
import com.javaEdu.pj.dao.FDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BWriteService implements Service{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		

				Map<String, Object> map = model.asMap();
				HttpServletRequest request = (HttpServletRequest)map.get("request");
				
				String ID = null;
				String bName = null;
				String bTitle = null;
				String bContent = null;
				int fId = -1;
				
				
				String directory = request.getSession().getServletContext().getRealPath("/upload/");
				int maxSize = 1024 * 1024 * 50; //50mb까지만 
				String encoding = "UTF-8";
				
				
				try{
					MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
					
					ID = multipartRequest.getParameter("id");
					bName = multipartRequest.getParameter("bName");
					bTitle = multipartRequest.getParameter("bTitle");
					bContent = multipartRequest.getParameter("bContent");
					
					
					String fileName = multipartRequest.getOriginalFileName("file");
					String fileRealName = multipartRequest.getFilesystemName("file");
					
					System.out.println("file name test:" +fileName);
					
					
					FDao fdao = new FDao();
					fId = fdao.upload(fileName, fileRealName) ;
					
					System.out.println("fId test : " +fId);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				BDao dao = new BDao();
				dao.write(ID, bName, bTitle, bContent, fId);
	}
	
}
