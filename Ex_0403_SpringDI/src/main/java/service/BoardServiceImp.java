package service;

import java.util.List;

import dao.BoardDAOImpl;

public class BoardServiceImp implements BoardService{

	BoardDAOImpl dao;
	
	public BoardServiceImp(BoardDAOImpl dao) {
		//나중에 ServiceImp가 생성될 때 DAO객체를 생성자로 받을 예정
		this.dao = dao;
	}
	
	@Override
	public List<String> selecList() {
		return dao.selectList();
	}
	
}
