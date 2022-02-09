package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {

	@Autowired
	private FileService fileService;
	
	//파일업로드 폼
	@RequestMapping("/form") 
	public String form() { 
		System.out.println("FileController.form()");
		
		return "fileupload/form";
	}
	
	
	//파일업로드 처리
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileController.upload()");
		
		String saveName = fileService.restore(file);
		model.addAttribute("saveName", saveName);
		
		return "fileupload/result";
	}
	
	
	
}
