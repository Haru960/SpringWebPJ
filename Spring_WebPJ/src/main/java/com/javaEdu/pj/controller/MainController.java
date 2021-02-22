package com.javaEdu.pj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaEdu.pj.service.AWriteService;
import com.javaEdu.pj.service.BContentService;
import com.javaEdu.pj.service.BDeleteService;
import com.javaEdu.pj.service.BFileDownService;
import com.javaEdu.pj.service.BListService;
import com.javaEdu.pj.service.BModifyService;
import com.javaEdu.pj.service.BMylistService;
import com.javaEdu.pj.service.BWriteService;
import com.javaEdu.pj.service.QAListService;
import com.javaEdu.pj.service.QAWriteService;
import com.javaEdu.pj.service.QContentService;
import com.javaEdu.pj.service.QDeleteService;
import com.javaEdu.pj.service.QModifyService;
import com.javaEdu.pj.service.Service;


@Controller
public class MainController {
	
	Service service = null;
	
	@RequestMapping("/Main")
	public String Main(Model model) {
		System.out.println("Main");
		return "Main";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		model.addAttribute("request", request);
		
		service = new BListService();
		service.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/bwrite_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "bwrite_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write");
		model.addAttribute("request", request);
		
		service = new BWriteService();
		service.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/bcontent_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view");
		model.addAttribute("request", request);
		
		service = new BContentService();
		service.execute(model);
		
		return "bcontent_view";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify");
		model.addAttribute("request", request);
		
		service = new BModifyService();
		service.execute(model);
		
		return "redirect:bcontent_view?bId="+request.getParameter("bId");
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete");
		model.addAttribute("request", request);
		
		service = new BDeleteService();
		service.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/check_pass")
	public String delete( Model model) {
		System.out.println("check_pass");
		return "check_pass";
	}
	
	@RequestMapping("/file_Down")
	public String file_Down(HttpServletRequest request, Model model) {
		System.out.println("file_Down");
		model.addAttribute("request", request);
		
		service = new BFileDownService();
		service.execute(model);
		
		return "file_Down";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	@RequestMapping("/loginOk")
	public String loginOk(Model model) {
		return "loginOk";
	}
	@RequestMapping("/join")
	public String join(Model model) {
		return "join";
	}
	@RequestMapping("/joinOk")
	public String joinOk(Model model) {
		return "joinOk";
	}
	@RequestMapping("/logout")
	public String logout(Model model) {
		return "logout";
	}
	
	@RequestMapping("/user_modify")
	public String user_modify(HttpServletRequest request, Model model) {
		System.out.println("user_modify");
		model.addAttribute("request", request);
		
		service = new BMylistService();
		service.execute(model);
		return "user_modify";
	}
	@RequestMapping("/user_modifyOk")
	public String user_modifyOk(Model model) {
		System.out.println("user_modifyOk");
		return "user_modifyOk";
	}
	
	@RequestMapping("/QAlist")
	public String QAlist(HttpServletRequest request, Model model) {
		System.out.println("list()");
		model.addAttribute("request", request);
		
		service = new QAListService();
		service.execute(model);
		
		return "QAlist";
	}
	@RequestMapping("/qa_content_view")
	public String qa_content_view(HttpServletRequest request, Model model) {
		System.out.println("qa_content_view()");
		model.addAttribute("request", request);
		
		service = new QContentService();
		service.execute(model);
		
		return "qa_content_view";
	}
	
	@RequestMapping("/qa_write_view")
	public String qa_write_view(Model model) {
		System.out.println("qa_write_view()");
		
		return "qa_write_view";
	}
	
	@RequestMapping("/qa_write")
	public String qa_write(HttpServletRequest request, Model model) {
		System.out.println("qa_write()");
		model.addAttribute("request", request);
		
		service = new QAWriteService();
		service.execute(model);
		
		return "redirect:QAlist";
	}
	
	@RequestMapping("/qa_answer")
	public String qa_answer(HttpServletRequest request, Model model) {
		System.out.println("qa_answer()");
		model.addAttribute("request", request);
		
		service = new AWriteService();
		service.execute(model);
		
		return "redirect:qa_content_view?qId=" +request.getParameter("qId");
	}
	@RequestMapping("/qa_modify")
	public String qa_modify(HttpServletRequest request, Model model) {
		System.out.println("qa_modify()");
		model.addAttribute("request", request);
		
		service = new QModifyService();
		service.execute(model);
		
		return "redirect:qa_content_view?qId=" +request.getParameter("qId");
	}
	@RequestMapping("/qa_delete")
	public String qa_delete(HttpServletRequest request, Model model) {
		System.out.println("qa_delete()");
		model.addAttribute("request", request);
		
		service = new QDeleteService();
		service.execute(model);
		
		return "redirect:QAlist";
	}
}
