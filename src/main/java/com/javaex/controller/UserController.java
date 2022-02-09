package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 회원가입 폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("userController/joinFrom");

		return "user/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("userController/join");
		// System.out.println(userVo);

		// 서비스를 통해 회원정보 저장
		userService.join(userVo);
		return "user/joinOk";
	}

	// 회원정보수정폼
	@RequestMapping("/modifyForm")
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("userController/modifyForm");

		// 세션에서 로그인한 사용자 no값 가져오기
		int no = ((UserVo) session.getAttribute("authUser")).getNo();

		// userService를 통해 로그인한 유저 정보 가져오기
		UserVo userVo = userService.modifyForm(no);

		// Dispacher Servlet에 유저정보 전달
		model.addAttribute("userVo", userVo);
		// System.out.println(userVo.toString());
		return "user/modifyForm";
	}

	// 회원수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("userController/modify");

		// 세션에서 로그인한 사용자 정보 가져오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 세션에서 로그인한 사용자 no값 가져오기
		int no = authUser.getNo();

		// 로그인한 사용자 no값 추가
		userVo.setNo(no);

		// userService를 통해 로그인한 사용자 정보 수정
		userService.modify(userVo);

		// 세션에 이름 변경
		authUser.setName(userVo.getName());

		return "redirect:/";
	}

	// 로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("userController/loginForm");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("userController/login");

		UserVo authUser = userService.login(userVo);

		if (authUser != null) { // 로그인 성공일때
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else { // 로그인 실패일때
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}

	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("userController/logout");

		// 세션의 값을 삭제한다.
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
}
