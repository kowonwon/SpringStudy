package com.springstudy.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 우리 컨트롤러가 호출되기 전에 실행됨 
		// 조건에 맞으면 true 다음으로 연결, 아니면 false 다음으로 연결이 않됨		
		if(request.getSession().getAttribute("isLogin") == null) {
			response.sendRedirect("loginForm");
			return false;
		} 
		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		// 우리 컨트롤러가 호출된 후에 실행됨
		System.out.println("postHandle : " + modelAndView.getViewName());
	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex)
			throws Exception {
		// 뷰가 완성된 후에 실행됨
		
	}

}
