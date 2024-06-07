package com.springstudy.blog.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springstudy.blog.domain.BlogPost;
import com.springstudy.blog.domain.Category;
import com.springstudy.blog.domain.PostReply;
import com.springstudy.blog.service.BlogService;

// 스프링 MVC의 컨트롤러임을 정의
@Controller
public class BlogController {
	
	// 업로드한 메인 이미지와 첨부 파일을 저장할 폴더 위치를 상수로 선언
	private final static String POST_MAIN_IMG_PATH = "/resources/postMainImg/";	
	private final static String POST_FILE_PATH = "/resources/postFile/";	
		
	private BlogService blogService;
	
	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
		
	}
	
	// 포스트 리스트 보기 요청 처리 메소드
	@RequestMapping({"/main", "/postList"})
	public String postList(Model model) {
		
		List<BlogPost> postList = blogService.postList();
		List<Category> categoryList = blogService.categoryList();
		model.addAttribute("postList", postList);
		model.addAttribute("categoryList", categoryList);
		
		return "/postList";
	}
	
	// 카테고리별 포스트 리스트 요청 처리 메소드 
	@RequestMapping("/postListByCategory")
	public String postListByCategory(Model model, int categoryNo) {
		
		List<BlogPost> postList = blogService.postListByCategory(categoryNo);
		List<Category> categoryList = blogService.categoryList();
		model.addAttribute("postList", postList);
		model.addAttribute("categoryList", categoryList);
		
		return "/postList";
	}
	
	// 포스트 상세보기 요청 처리 메소드
	@RequestMapping("/postDetail")
	public String postDetail(Model model, int postNo) {		
		BlogPost post = blogService.postDetail(postNo);
		List<Category> categoryList = blogService.categoryList();
		List<PostReply> postReplyList = blogService.postReplyList(postNo);
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postReplyList", postReplyList);
		
		return "/postDetail";
	}
	
	// 포스트 쓰기 폼 요청 처리 메소드
	@RequestMapping("/postWriteForm")
	public String postWriteForm(Model model) {
		
		/* 단순히 입력 폼 자체만 보여주는 요청 처리는 뷰 전용 컨트롤러를
		 * 사용하면 간단히 해결할 수 있지만 카테고리가 있는 블로그 포스트
		 * 작성은 현재 정의된 카테고리를 db에서 읽어와 입력 폼에 출력하고
		 * 포스트를 작성하는 사용자가 작성하는 포스트가 어떤 카테고리에 소속
		 * 되는지 선택할 수 있어야 하므로 카테고리 리스트를 모델에 담아서
		 * 뷰 페이지에서 폼을 출력할 때 사용할 수 있도록 해야 한다. 
		 **/
		List<Category> categoryList = blogService.categoryList();
		model.addAttribute("categoryList", categoryList);		
		
		return "/postWriteForm";
	}
	
	// 포스트 쓰기 요청 처리 메소드
	@RequestMapping(value="/postWriteProcess", method=RequestMethod.POST)
	public String postWriteProcess(HttpServletRequest request, BlogPost post, 
			@RequestParam(value="addImg", required=false) MultipartFile addImg, 
			@RequestParam(value="addFile", required=false) MultipartFile addFile) 
				throws Exception {
		
		System.out.println("addImg originName : " + addImg.getOriginalFilename());
		System.out.println("addFile originName : " + addFile.getOriginalFilename());
		
		if(!addImg.isEmpty()) { // 메인 이미지 파일이 존재하면
			
			// Request 객체를 이용해 파일이 저장될 실제 경로를 구한다.
			String filePath = 
					request.getServletContext().getRealPath(POST_MAIN_IMG_PATH);
			
			// UUID(Universally Unique Identifier, 범용 고유 식별자)			
			UUID uid = UUID.randomUUID();
			String saveName = 
					uid.toString() + "_" + addImg.getOriginalFilename();
			
			File file = new File(filePath, saveName);
			System.out.println("newMainImg Name : " + file.getName());			
			
			// 업로드 되는 파일을 upload 폴더로 저장한다.
			addImg.transferTo(file);
			
			// 업로드된 메인 이미지 파일 명을 BlogPost 객체에 저장
			post.setMainImg(saveName);
		}
		
		if(!addFile.isEmpty()) { // 첨부 파일이 존재하면
			
			// Request 객체를 이용해 파일이 저장될 실제 경로를 구한다.
			String filePath = 
					request.getServletContext().getRealPath(POST_FILE_PATH);
			
			// UUID(Universally Unique Identifier, 범용 고유 식별자)			
			UUID uid = UUID.randomUUID();
			String saveName = 
					uid.toString() + "_" + addFile.getOriginalFilename();
			
			File file = new File(filePath, saveName);
			System.out.println("newAddFile Name : " + file.getName());			
			
			// 업로드 되는 파일을 upload 폴더로 저장한다.
			addFile.transferTo(file);
			
			// 업로드된 첨부 파일 명을 BlogPost 객체에 저장
			post.setFile1(saveName);
		}
		
		blogService.insertPost(post);
		
		return "redirect:postList";
	}
	
	// 포스트 수정 폼 요청 처리 메소드
	@RequestMapping("/postUpdateForm")
	public String postUpdateForm(Model model, int postNo) {
		
		BlogPost post = blogService.postDetail(postNo);
		List<Category> categoryList = blogService.categoryList();
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryList);		
		
		return "/postUpdateForm";
	}
	
	// 포스트 수정 요청 처리 메소드
	@RequestMapping(value="/postUpdateProcess", method=RequestMethod.POST)
	public String postUpdateProcess(HttpServletRequest request, BlogPost post, 
			@RequestParam(value="addImg", required=false) MultipartFile addImg, 
			@RequestParam(value="addFile", required=false) MultipartFile addFile) 
				throws Exception {
		
		System.out.println("addImg originName : " + addImg.getOriginalFilename());
		System.out.println("addFile originName : " + addFile.getOriginalFilename());
		
		if(!addImg.isEmpty()) { // 메인 이미지 파일이 존재하면
			
			// Request 객체를 이용해 파일이 저장될 실제 경로를 구한다.
			String filePath = 
					request.getServletContext().getRealPath(POST_MAIN_IMG_PATH);
			
			// UUID(Universally Unique Identifier, 범용 고유 식별자)			
			UUID uid = UUID.randomUUID();
			String saveName = 
					uid.toString() + "_" + addImg.getOriginalFilename();
			
			File file = new File(filePath, saveName);
			System.out.println("newMainImg Name : " + file.getName());			
			
			// 업로드 되는 파일을 upload 폴더로 저장한다.
			addImg.transferTo(file);
			
			// 업로드된 메인 이미지 파일 명을 BlogPost 객체에 저장
			post.setMainImg(saveName);
		}
		
		if(!addFile.isEmpty()) { // 첨부 파일이 존재하면
			
			// Request 객체를 이용해 파일이 저장될 실제 경로를 구한다.
			String filePath = 
					request.getServletContext().getRealPath(POST_FILE_PATH);
			
			// UUID(Universally Unique Identifier, 범용 고유 식별자)			
			UUID uid = UUID.randomUUID();
			String saveName = 
					uid.toString() + "_" + addFile.getOriginalFilename();
			
			File file = new File(filePath, saveName);
			System.out.println("newAddFile Name : " + file.getName());			
			
			// 업로드 되는 파일을 upload 폴더로 저장한다.
			addFile.transferTo(file);
			
			// 업로드된 첨부 파일 명을 BlogPost 객체에 저장
			post.setFile1(saveName);
		}
		
		blogService.updatePost(post);
		
		return "redirect:postList";
	}
	
	// 포스트 삭제 요청 처리 메소드
	@RequestMapping("/deletePost")
	public String postDelete(int postNo) {
		
		blogService.deletePost(postNo);
		
		return "redirect:/postList";
	}
}
