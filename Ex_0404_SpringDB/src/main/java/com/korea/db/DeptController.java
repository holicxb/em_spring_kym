package com.korea.db;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDAO;
import vo.DeptVO;

@Controller
public class DeptController {
	
	public static final String VIEW_PATH = "/WEB-INF/views/dept/";
	
	DeptDAO dept_dao;
	
	public DeptController(DeptDAO dept_dao) {
		this.dept_dao = dept_dao;
		
		//System.out.println("DEPT컨트롤러 생성자 : " + dept_dao);
	}
	
	//전체 조회
	@RequestMapping(value={"/", "/list.do"})
	public String list(Model model) {
		//dept_dao로부터 전체 목록을 가져온다
		List<DeptVO> list = dept_dao.selectList();
		
		//바인딩
		model.addAttribute("list", list);
		
		//포워딩
		return VIEW_PATH + "dept_list.jsp";
		
		//다시 원래 위치로 돌아가고 싶을 때(sendRedirect("list.do");)
		//return "redirect:list.do";
	}
	
	//부서 추가
	@RequestMapping("/insert.do")
	public String insert(Model model, DeptVO vo) {
		//System.out.println(vo.getDeptno());
		//System.out.println(vo.getDname());
		//System.out.println(vo.getLoc());
		int res = dept_dao.insert(vo);
		
		return "redirect:list.do";
	}
	
	//부서 수정
	@RequestMapping("/modify.do")
	public String modify(DeptVO vo) {
		dept_dao.update(vo);
		return "redirect:list.do";
	}
	
	//부서 삭제
	@RequestMapping("/del.do")
	public String delete(int delno) {
		dept_dao.delete(delno);
		return "redirect:list.do";
	}
}
