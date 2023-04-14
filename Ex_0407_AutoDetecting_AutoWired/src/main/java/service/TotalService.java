package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DeptDAO;
import vo.DeptVO;

@Service
public class TotalService {
	
	DeptDAO dept_dao;//@Repository("dept_dao") <-- 이름을 그대로 가져와야함
	@Autowired
	public TotalService(DeptDAO dept_dao) {
		this.dept_dao = dept_dao;
		System.out.println("dao : " + dept_dao);
		System.out.println("--- TotalService가 생성됨 ---");
	}
	
	public List<DeptVO> selectList(){
		List<DeptVO> list = dept_dao.selectList();
		return list;
	}
}
