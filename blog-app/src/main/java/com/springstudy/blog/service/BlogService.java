package com.springstudy.blog.service;

import java.util.List;

import com.springstudy.blog.domain.BlogPost;
import com.springstudy.blog.domain.Category;
import com.springstudy.blog.domain.PostReply;

public interface BlogService {
	
	// 블로그 포스트 리스트를 읽어와 반환하는 메소드
	public abstract List<BlogPost> postList();	

	// 카테고리 리스트를 읽어와 반환하는 메소드
	public abstract List<Category> categoryList();
	
	// 카테고리에 해당하는 블로그 포스트 리스트를 읽어와 반환하는 메소드
	public abstract List<BlogPost> postListByCategory(int categoryNo);	

	// 포스트 번호에 해당하는 포스트 세부내용을 읽어와 반환하는 메소드 
	public abstract BlogPost postDetail(int postNo);
	
	// 포스트 번호에 해당하는 댓글 리스트를 읽어와 반환하는 메소드
	public abstract List<PostReply> postReplyList(int postNo);

	// 입력한 포스트를 DB 테이블에 추가하는 메소드 
	public abstract void insertPost(BlogPost post);
	
	// 수정한 포스트를 DB 테이블에서 수정하는 메소드 
	public abstract void updatePost(BlogPost post);
	
	// 포스트를 DB 테이블에서 삭제하는 메소드 
	public abstract void deletePost(int postNo);
}
