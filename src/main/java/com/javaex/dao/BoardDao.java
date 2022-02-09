package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 글전체 가져오기(리스트만 출력할때)
	public List<BoardVo> selectList() {
		System.out.println("boardDao/selectList");

		return sqlSession.selectList("board.selectList");
	}


	// 글리스트 가져오기(리스트+페이징)
	public List<BoardVo> selectList2(int startRnum, int endRnum) {
		System.out.println("boardDao/selectList2");
		System.out.println(startRnum + "," + endRnum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		List<BoardVo> boardList=sqlSession.selectList("board.selectList2", map);
		return boardList;
	}
	
	

	// 글저장
	public int insert(BoardVo boardVo) {
		System.out.println("boardDao/insert");

		return sqlSession.insert("board.insert", boardVo);
	}

	// 글 1개 가져오기
	public BoardVo select(int no) {
		System.out.println("boardDao/select");
		System.out.println(no);
		return sqlSession.selectOne("board.selcet", no);
	}

	// 조회수 업데이트
	public int updateHit(int no) {
		System.out.println("boardDao/updateHit");

		return sqlSession.update("board.updateHit", no);
	}

	// 글수정
	public int update(BoardVo boardVo) {
		System.out.println("boardDao/update");

		return sqlSession.update("board.update", boardVo);
	}

	// 글삭제
	public int delete(BoardVo boardVo) {
		System.out.println("boardDao/delete");

		return sqlSession.delete("board.delete", boardVo);
	}

	//전체 글갯수 가져오기
	public int selectTotal() {
		System.out.println("boardDao/selectTotal");
		
		return sqlSession.selectOne("board.totalCnt");
	}
	
	
	
}
