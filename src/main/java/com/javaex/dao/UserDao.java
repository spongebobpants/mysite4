package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 회원정보 저장
	public int insert(UserVo userVo) {
		System.out.println("userDao/insert");

		return sqlSession.insert("user.insert", userVo);
	}

	// 회원정보 가져오기 ->세션저장용
	public UserVo selectUser(UserVo userVo) {
		System.out.println("userDao/selectUser");

		return sqlSession.selectOne("user.selectUser", userVo);
	}

	// 회원정보 가져오기 ->회원정보 수정폼
	public UserVo selectUser(int no) {
		System.out.println("userDao/selectUserByNo");

		return sqlSession.selectOne("user.selectUserByNo", no);
	}

	// 회원정보 수정
	public int update(UserVo userVo) {
		System.out.println("userDao/update");

		return sqlSession.update("user.update", userVo);
	}

}
