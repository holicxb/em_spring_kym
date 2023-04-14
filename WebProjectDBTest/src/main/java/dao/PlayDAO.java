package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.PlayVO;

public class PlayDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<PlayVO> selectList(){
		List<PlayVO> list = sqlSession.selectList("p.play_list");
		return list;
	}
}
