package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook") 
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	
	@RequestMapping("/addList") //줄여썼음(원래 어떻게 쓰는지 확인)
	public String addList() {
		System.out.println("ApiGuestbookControlle.addList()");
		
		return "aGuestbook/addList";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookControlle.list()");
		List<GuestbookVo> guestbookList = guestbookService.getGuestList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookControlle.write()");
		
		//저장하고 저장된값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		return gVo;
	}
	
	@ResponseBody //json type 선언 
	@RequestMapping("/write2")
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookControlle.write2()");
		System.out.println(guestbookVo);
		
		//저장하고 저장된값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		return gVo;
	}
	
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookControlle.remove()");
		System.out.println(guestbookVo);
		
		String result = guestbookService.remove(guestbookVo);
		System.out.println(result);
		return result;
	}
	
}
