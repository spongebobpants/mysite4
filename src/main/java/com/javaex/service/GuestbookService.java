package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//방명록 리스트 가져오기
	public List<GuestbookVo> getGuestList(){
		System.out.println("guestbookService/getList");
		
		return guestbookDao.selectList();
	}

	//방명록 글 저장
	public int addGuest(GuestbookVo guestbookVo){
		System.out.println("guestbookService/write");
		
		return guestbookDao.insert(guestbookVo);
	}
	
	
	//방명록 글 저장--> 저장글 리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		System.out.println("guestbookService/addGuestResultVo");
		
		//저장하기
		int count =guestbookDao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuest(no);
	}
	
	
	//방명록 글 삭제
	public int removeGuest(GuestbookVo guestbookVo){
		System.out.println("guestbookService/remove");
		return guestbookDao.delete(guestbookVo);
	}
	

	//ajax 방명록 글 삭제
	public String remove(GuestbookVo guestbookVo) {
		System.out.println("guestbookService/remove ajax");
		
		int count = guestbookDao.delete(guestbookVo);
		
		if(count>0) {
			return "success";
		}else {
			return "fail";
		}
	
	}
	

}
