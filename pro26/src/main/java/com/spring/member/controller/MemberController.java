package com.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void addMember(@RequestParam Map<String, String> info ,HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView removeMember(@RequestParam("id")String id ,HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void checkId(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public String modForm(Model model, @RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void modMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)throws Exception;
}
