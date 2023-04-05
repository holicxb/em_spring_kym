package com.korea.param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vo.PersonVO;

@Controller
public class ParamController {
	public static final String VIEW_PATH = "/WEB-INF/views/";
	
	@RequestMapping(value={"/", "/insert_form.do"})
	public String insert_form() {
		return VIEW_PATH + "insert_form.jsp";
	}
	
	//send1() 호출에 대한 처리
	@RequestMapping("/insert1.do")
	public String insert1(Model model, String name, int age, String tel) {
		//insert1.do?name=홍길동&age=25&tel=0101112222 
		//-> 파라미터 명을 일치시켜줘야 request.getParam을 안해줘도 된다.
		
		//name, age, tel이 파라미터로 자동으로 넘어옴
		//int 타입의 경우 자동으로 형 변환까지 처리
		
		//넘겨받은 3개의 파라미터를 VO에 담아서 바인딩
		PersonVO vo = new PersonVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setTel(tel);
		
		model.addAttribute("vo", vo);
		return VIEW_PATH+"insert_result.jsp";
	}
	
	//send2()에 대한 객체 처리
	@RequestMapping("/insert2.do")
	public String insert2(Model model, PersonVO vo) {
		//파라미터 이름과 vo의 이름이 같으면 가능 + DB의 컬럼명까지
		model.addAttribute("vo", vo);
		return VIEW_PATH + "insert_result.jsp";
		
		//insert2(Model model, PersonVO vo, String name){ }
		//-> name은 이미 vo에 동일한 이름이 있기 때문에 오류 발생
		//(vo에 이미 존재하는 변수를 파라미터로 또 받게되면 실행 시 오류 발생)
	}
}
