package com.springstudy.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.blog.dao.BlogDao;
import com.springstudy.blog.domain.BlogPost;
import com.springstudy.blog.domain.Category;
import com.springstudy.blog.domain.PostReply;

//이 클래스가 서비스 계층(비즈니스 로직)의 컴포넌트(Bean) 임을 선언하고 있다.
@Service
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;

	@Autowired
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	@Override
	public List<BlogPost> postList() {		
		return blogDao.postList();
	}

	@Override
	public List<Category> categoryList() {		
		return blogDao.categoryList();
	}

	@Override
	public List<BlogPost> postListByCategory(int categoryNo) {		
		return blogDao.postListByCategory(categoryNo);
	}
	
	@Override
	public BlogPost postDetail(int postNo) {		
		return blogDao.postDetail(postNo);
	}
	
	@Override
	public List<PostReply> postReplyList(int postNo) {
		return blogDao.postReplyList(postNo);
	}

	@Override
	public void insertPost(BlogPost post) {
		blogDao.insertPost(post);		
	}

	@Override
	public void updatePost(BlogPost post) {
		blogDao.updatePost(post);
	}

	@Override
	public void deletePost(int postNo) {
		blogDao.deletePost(postNo);
	}
}
