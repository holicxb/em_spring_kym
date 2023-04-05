package com.korea.visit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDAO;
import util.MyCommon;
import vo.VisitVO;

@Controller
public class VisitController {
	
	VisitDAO visit_dao;
	
	public void setVisit_dao(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	//방명록 전체 정보 조회
	@RequestMapping(value= {"/", "/list.do"})
	public String list(Model model) {
		List<VisitVO> list = visit_dao.selectList();
		model.addAttribute("list", list);//바인딩
		return MyCommon.Visit.VIEW_PATH + "visit_list.jsp";//포워딩
	}
	
	//새 글 추가 화면전환 용
	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return MyCommon.Visit.VIEW_PATH + "visit_insert_form.jsp";
	}
	
	//새 글 추가
	@RequestMapping("/insert.do")
	public String insert(VisitVO vo, HttpServletRequest request) {
		//insert.do?name=kim&content=내용&pwd=1111
		//vo파라미터를 통해 이름, 내용, 비밀번호는 이미 받은 상태
		String ip = request.getRemoteAddr();
		
		vo.setIp(ip);
		
		visit_dao.insert(vo);
		
		return "redirect:list.do";
	}
	
	//글 삭제
	@RequestMapping("/delete.do")
	@ResponseBody //Ajax로 요청된 메서드가 반드시 가지고 있어야 하는 어노테이션
	public String delete(int idx) {
		//delete.do?idx=2
		int res = visit_dao.delete(idx);
		
		String result = "no";
		
		if(res != 0) {
			result = "yes";
		}
		
		//@ResponseBody 어노테이션의 속성을 통해 반환된 result 값이 자동으로 콜백메서드로 돌아감
		return result;
	}
	
	//수정용 화면 이동
	@RequestMapping("/modify_form.do")
	public String modify_form(Model model, int idx) {
		VisitVO vo = visit_dao.selectOne(idx);
		model.addAttribute("vo", vo);//바인딩
		return MyCommon.Visit.VIEW_PATH + "visit_modify_form.jsp";
	}
	
	//글 수정
	@RequestMapping("/modify.do")
	@ResponseBody
	public String modify(int idx, String name, String content, HttpServletRequest request) {
		VisitVO vo = new VisitVO();
		String ip = request.getRemoteAddr();
		vo.setIdx(idx);
		vo.setName(name);
		vo.setContent(content);
		vo.setIp(ip);
		
		int res = visit_dao.modify(vo);
		
		String result = "no";
		
		if(res != 0) {
			result = "yes";
		}
		return result;
	}
}
