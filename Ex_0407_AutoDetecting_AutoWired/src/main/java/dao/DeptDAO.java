package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.DeptVO;

@Repository("dept_dao")//데이터 관리 어노테이션
public class DeptDAO {
	
	//AutoWired 자동 연결을 통해서 setter, 생성자 인젝션 없이 
	//sqlSession객체를 생성
	SqlSession sqlSession;
	
	//@Component의 자식 클래스를 어노테이션으로 가지고 있는 경우
	//servlet-context.xml에서 auto-detectinf(자동생성)이 가능해진다
	//@Component
	//ㄴ@Controller
	//ㄴ@Repository
	//ㄴ@Service
	
	@Autowired
	public DeptDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		System.out.println("Session : " + this.sqlSession);
		System.out.println("--- DeptDAO의 생성자가 만들어짐 ---");
	}
	
	//부서목록조회
	public List<DeptVO> selectList(){
		List<DeptVO> list = sqlSession.selectList("d.dept_list");
		
		return list;
	}
}
