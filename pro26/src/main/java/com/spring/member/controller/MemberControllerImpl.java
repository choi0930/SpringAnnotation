package com.spring.member.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;
@Controller("memberController")
public class MemberControllerImpl extends MultiActionController implements MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/member/listMembers.do", method= RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public void addMember(@RequestParam Map<String, String> info , HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = info.get("id");
		String pwd = info.get("pwd");
		String name = info.get("name");
		String email1 =info.get("email1");
		String email2=info.get("email2");
		String email3= info.get("email3");
		String email = "";
		System.out.println(id);
		System.out.println(pwd);
		if(email3.equals("choose") || email3.equals("self")) {
			email = email1+"@"+email2;
		}else {
			email = email1+"@"+email3;
		}
		
		memberVO = new MemberVO(id, pwd, name, email);
		
		int result=0;
		result = memberService.addMember(memberVO);
		out.write("<script>");
		out.write("alert('회원가입 완료');");
		out.write("location.href='"+request.getContextPath()+"/member/listMembers.do';");
		out.write("</script>");
		return;	
		
		//ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		//return mav;
	}

	@Override
	@RequestMapping(value="/member/removeMember.do",method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value="/member/*Form.do",method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/checkId.do",  method=RequestMethod.POST)
	public void checkId(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		memberVO = memberService.selectMemberById(id);
		if(memberVO != null) {
			out.print("no-usable");
		} else {
			out.print("usable");
		}
		return;
	}
	
	
	@Override
	@RequestMapping(value="/member/modMemberForm1.do",method=RequestMethod.GET)
	public String modForm(Model model ,@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String viewName = getViewName(request);
		memberVO = memberService.selectMemberById(id);
		model.addAttribute("memberVO", memberVO);
		return viewName;
	}
	
	
	@Override
	@RequestMapping(value="/member/modMember.do",method=RequestMethod.POST)
	public void modMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		memberService.updateMember(memberVO);
		PrintWriter out = response.getWriter();
		out.write("<script>");
		out.write("alert('수정 완료');");
		out.write("location.href='"+request.getContextPath()+"/member/listMembers.do';");
		out.write("</script>");
		return;	
	}
	
	private String getViewName(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		System.out.println(contextPath);
		System.out.println(uri);
		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		
		int begin = 0;
		if(!((contextPath==null)||("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		
		int end;
		if(uri.indexOf(";")!=-1) {
			end=uri.indexOf(";");
		} else if(uri.indexOf("?")!= -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		
		String fileName = uri.substring(begin, end);
		System.out.println(fileName);
		if(fileName.indexOf(".")!= -1) {
			fileName = fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!= -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		System.out.println(fileName);
		return fileName;
	}
}
