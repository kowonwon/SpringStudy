package com.springstudy.blog.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.blog.domain.BlogPost;
import com.springstudy.blog.domain.Category;
import com.springstudy.blog.domain.PostReply;

//이 클래스가 데이터 액세스(데이터 저장소) 계층의 컴포넌트(Bean) 임을 선언한다.
@Repository
public class BlogDaoImpl implements BlogDao {
	
	/* src/main/resources/repository/mappers/BlogMapper.xml에
	 * 정의한 Mapper namespace를 상수로 정의
	 **/
	private static final String NAME_SPACE = "com.springstudy.blog.mappers.BlogMapper";

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BlogPost> postList() {
		return sqlSession.selectList(NAME_SPACE + ".postList");
	}

	@Override
	public List<Category> categoryList() {		
		return sqlSession.selectList(NAME_SPACE + ".categoryList");
	}

	@Override
	public List<BlogPost> postListByCategory(int categoryNo) {		
		return sqlSession.selectList(NAME_SPACE + ".postListByCategory", categoryNo);
	}	

	@Override
	public BlogPost postDetail(int postNo) {		
		return sqlSession.selectOne(NAME_SPACE + ".postDetail", postNo);
	}
	
	@Override
	public List<PostReply> postReplyList(int postNo) {		
		return sqlSession.selectList(NAME_SPACE + ".postReplyList", postNo);
	}

	@Override
	public void insertPost(BlogPost post) {
		sqlSession.insert(NAME_SPACE + ".insertPost", post);
	}

	@Override
	public void updatePost(BlogPost post) {		
		sqlSession.update(NAME_SPACE + ".updatePost", post);
	}

	@Override
	public void deletePost(int postNo) {
		sqlSession.delete(NAME_SPACE + ".deletePost", postNo);		
	}
}
