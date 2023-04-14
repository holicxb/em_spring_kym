package com.korea.visit;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.VisitDAO;
import util.MyCommon;
import vo.VisitVO;

@Controller
public class JsonVisitController {

	@Autowired // 자동주입 : 스프링으로부터 자동생성 가능한 객체를 new없이 알아서 생성해준다
	HttpServletRequest request; // 서블릿 컨택스트로 감 / ip구할 때 사용

	@Autowired
	ServletContext app; // 절대 경로 찾기 위해서 만들어 둠

	VisitDAO visit_dao;

	public void setVisit_dao(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}

	// 방명록 전체 정보 조회
	@RequestMapping(value={"/","/list2.do"})
	@ResponseBody
	public List<VisitVO> list(Model model) {
		List<VisitVO> list = visit_dao.selectList();
		//model.addAttribute("list", list);// 바인딩
		return list;// 포워딩
	}

	// 새 글 추가 화면전환 용
	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return MyCommon.Visit.VIEW_PATH + "visit_insert_form.jsp";
	}

	// 새 글 추가
	@RequestMapping("/insert.do")
	public String insert(VisitVO vo) {
		// insert.do?name=kim&content=내용&pwd=1111
		// vo파라미터를 통해 이름, 내용, 비밀번호는 이미 받은 상태
		String ip = request.getRemoteAddr();
		vo.setIp(ip);

		// 이미지가 저장될 절대경로를 만들고 구하기
		String webPath = "/resources/upload/"; // 상대경로
		String savePath = app.getRealPath(webPath); // 절대경로

		System.out.println("절대경로 : " + savePath);

		// 업로드 된 파일 정보
		MultipartFile photo = vo.getPhoto();
		String filename = "no_file";
		if (!photo.isEmpty()) {
			// DB에 추가할 실제 파일의 이름을 알아온다
			filename = photo.getOriginalFilename();

			// 파일을 저장할 절대경로
			File saveFile = new File(savePath, filename);

			if (!saveFile.exists()) {
				saveFile.mkdirs(); // 절대 경로에 upload라는 이름의 폴더 생성
			} else {
				// 동일 파일일 경우 현재 업로드 시간을 붙여서 이름 변경
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time, filename);
				saveFile = new File(savePath, filename);
			}

			try {
				// 업로드를 요청한 파일은 MultipartResolver클래스가 임시저장소에 보관함
				// 임시 저장소에 보관된 파일은 일정 시간이 지나면 사라짐으로,
				// 절대 경로 위치에 이미지를 물리적으로 복사해넣어주는 작업
				photo.transferTo(saveFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		vo.setFilename(filename);

		visit_dao.insert(vo);

		return "redirect:list.do";
	}

	// 글 삭제
	@RequestMapping("/delete.do")
	@ResponseBody // Ajax로 요청된 메서드가 반드시 가지고 있어야 하는 어노테이션
	public String delete(int idx) {
		// delete.do?idx=2
		
		int res = visit_dao.delete(idx);

		String result = "no";

		if (res != 0) {
			result = "yes";
		}

		// @ResponseBody 어노테이션의 속성을 통해 반환된 result 값이 자동으로 콜백메서드로 돌아감
		return result;
	}

	// 수정용 화면 이동
	@RequestMapping("/modify_form.do")
	public String modify_form(Model model, int idx) {
		VisitVO vo = visit_dao.selectOne(idx);
		model.addAttribute("vo", vo);// 바인딩
		return MyCommon.Visit.VIEW_PATH + "visit_modify_form.jsp";
	}

	// 글 수정
	@RequestMapping(value = "/modify.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modify(VisitVO vo) {
		String ip = request.getRemoteAddr();
		vo.setIp(ip);

		// 이미지가 저장될 절대경로를 만들고 구하기
		String webPath = "/resources/upload/"; // 상대경로
		String savePath = app.getRealPath(webPath); // 절대경로

		System.out.println("절대경로 : " + savePath);

		// 업로드 된 파일 정보
		MultipartFile photo = vo.getPhoto();
		String filename = "no_file";
		if (!photo.isEmpty()) {
			// DB에 추가할 실제 파일의 이름을 알아온다
			filename = photo.getOriginalFilename();

			// 파일을 저장할 절대경로
			File saveFile = new File(savePath, filename);

			if (!saveFile.exists()) {
				saveFile.mkdirs(); // 절대 경로에 upload라는 이름의 폴더 생성
			} else {
				// 동일 파일일 경우 현재 업로드 시간을 붙여서 이름 변경
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time, filename);
				saveFile = new File(savePath, filename);
			}

			try {
				// 업로드를 요청한 파일은 MultipartResolver클래스가 임시저장소에 보관함
				// 임시 저장소에 보관된 파일은 일정 시간이 지나면 사라짐으로,
				// 절대 경로 위치에 이미지를 물리적으로 복사해넣어주는 작업
				photo.transferTo(saveFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		vo.setFilename(filename);

		int res = visit_dao.modify(vo);

		String result = "no";

		if (res != 0) {
			result = "yes";
		}

		// 값을 가지고 콜백 메서드로 돌아감
		// 만약 return 된 데이터에 한글이 포함되어 있다면 깨져서 넘어간다.
		// 이를 해결하기 위해 @RequestMapping 영역에
		// produces="application/json;charset=UTF-8" 코드를 추가해줘야한다
		return result;
	}
	
	
}
