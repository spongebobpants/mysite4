package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 리스트(리스트만 출력할때)
	public List<BoardVo> getBoardList() {
		System.out.println("boardService/list");

		return boardDao.selectList();
	}

	
	// 리스트(리스트 + 페이징)
	public Map<String, Object> getBoardList2(int crtPage) {
		System.out.println("boardService/list2");
		/////////////////////////////////////
		// 리스트 가져오기
		//////////////////////////////////////
		
		//페이지당 글개수
		int listCnt = 10;
		
		//현재페이지 처리
		crtPage = (crtPage>0) ? crtPage : (crtPage=1);
		
		//시작글 번호  1-->1     6-->51
		int startRnum = (crtPage-1)*listCnt + 1;
		
		//끝글 번호
		int endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum);
		
		
		/////////////////////////////////////
		// 페이징 버튼
		//////////////////////////////////////
		
		//전체 글갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt= " + totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;


		//**마지막 버튼 번호
			//1    1~5     0.2
			//2    1~5     0.4
			//3    1~5     0.6 
			//5    1~5     1 
			//6    6~10    1.2
			//10   6~10
			//11   11~15
		int endPageBtnNo = (int)(   Math.ceil( crtPage/(double)pageBtnCount )   )*pageBtnCount;  
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo * listCnt < totalCnt ) {
			next = true;
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		/////////////////////////////////////
		// 포장
		//////////////////////////////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		
		return pMap;
	}
	
	
	
	
	
	// 글쓰기
	public int addBoard(BoardVo boardVo) {
		System.out.println("boardService/addBoard");

		//페이징 테이타 추가 123개
		/*
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i + "번째글 제목입니다.");
			boardVo.setContent(i + "번째글 내용입니다.");
			boardVo.setHit(0);
			boardVo.setUserNo(1);
			boardDao.insert(boardVo);
		}
		*/
		return boardDao.insert(boardVo);
		
		//return 1;
	}

	// 글가져오기
	@Transactional
	public BoardVo getBoard(int no, String type) throws Exception {
		System.out.println("boardService/getBoard");

		if ("read".equals(type)) {// 읽기 일때는 조회수 올림
			boardDao.updateHit(no);
			BoardVo boardVo = boardDao.select(no);
			return boardVo;

		} else { // 수정 일때는 조회수 올리지 않음
			BoardVo boardVo = boardDao.select(no);
			return boardVo;
		}

	}

	// 글수정
	public int modifyBoard(BoardVo boardVo) {
		System.out.println("boardService/modifyBoard");

		return boardDao.update(boardVo);
	}

	// 글삭제
	public int removeBoard(BoardVo boardVo) {
		System.out.println("boardService/removeBoard");

		return boardDao.delete(boardVo);
	}

}
