package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	//방명록 리스트 가져오기
	public List<GuestbookVo> selectList(){
		System.out.println("guestbookDao/selectList");
		
		return sqlSession.selectList("guestbook.selectList");
	}
	
	
	//방명록 글1개 가져오기
	public GuestbookVo selectGuest(int no) {
		System.out.println("guestbookDao/selectGuest");
		return sqlSession.selectOne("guestbook.selectByNo", no);
	}
	
	
	//방명록 글 저장
	public int insert(GuestbookVo guestbookVo){
		System.out.println("guestbookDao/insert");
	
		return sqlSession.insert("guestbook.insert", guestbookVo);	
	}	

 
	//방명록 글 저장(selectKey)   //리턴 성공한 갯수
	public int insertSelectKey(GuestbookVo guestbookVo){
		System.out.println("guestbookDao/insertSelectKey");
		
		return sqlSession.insert("guestbook.insertSelectKey", guestbookVo);	
	}
	
	
	//방명록 글 삭제
	public int delete(GuestbookVo guestbookVo){
		System.out.println("guestbookDao/delete");
		
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	

}
