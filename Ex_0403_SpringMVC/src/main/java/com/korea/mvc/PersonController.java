package com.korea.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.PersonDAO;
import vo.PersonVO;

@Controller
public class PersonController {
	
	public static final String VIEW_PATH = "/WEB-INF/views/person/";
	
	PersonDAO person_dao;

	public PersonController(PersonDAO person_dao) {
		System.out.println("--- PersonController() 생성자 ---");
		
		this.person_dao = person_dao;
	}
	
	//전체 목록 조회
	@RequestMapping(value={"/", "/list.do"})
	//반환형은 언제나 String
	public String select(Model model, HttpServletRequest request) {
		//dao로부터 검색결과 가져오기
		List<PersonVO> list = person_dao.selectList();
		
		//ip가져오기
		String ip = request.getRemoteAddr();
		
		//바인딩
		model.addAttribute("list", list);
		model.addAttribute("ip", ip);
		
		//model과 request 둘 다 있을 때는 원하는 것으로 하면 된다. 대부분 model에 저장하긴 함
		
		//포워딩
		return VIEW_PATH + "person.jsp";
	}
}
