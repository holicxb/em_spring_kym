package com.webp.db;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.PlayDAO;
import util.MyCommon;

public class PlayController {
	@Autowired // 자동주입 : 스프링으로부터 자동생성 가능한 객체를 new없이 알아서 생성해준다
	HttpServletRequest request; // 서블릿 컨택스트로 감 / ip구할 때 사용

	@Autowired
	ServletContext app; // 절대 경로 찾기 위해서 만들어 둠

	PlayDAO play_dao;
	
	public void setPlay_dao(PlayDAO play_dao) {
		this.play_dao = play_dao;
	}
	
	//PlayList 조회
	@RequestMapping(value={"/", "playshow.do"})
	public String insert_form() {
		return MyCommon.Play.VIEW_PATH + "play_list.jsp";
	}
}
