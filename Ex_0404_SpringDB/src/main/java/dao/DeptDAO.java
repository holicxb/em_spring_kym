package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVO;

public class DeptDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//전체목록 조회
	public List<DeptVO> selectList(){
		List<DeptVO> list = sqlSession.selectList("d.dept_list");
		return list;
	}
	
	//부서 추가
	public int insert(DeptVO vo) {
		int res = sqlSession.insert("d.dept_ins", vo);
		return res;
	}
	
	//내용 수정
	public int update(DeptVO vo) {
		int res = sqlSession.update("d.dept_mod", vo);
		return res;
	}
	
	//내용 삭제
	public int delete(int delno) {
		int res = sqlSession.delete("d.dept_del", delno);
		return res;
	}
}
