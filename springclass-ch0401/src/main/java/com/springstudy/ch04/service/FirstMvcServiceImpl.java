package com.springstudy.ch04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.ch04.dao.FirstMvcDao;

@Service
public class FirstMvcServiceImpl implements FirstMvcService {

	private FirstMvcDao firstMvcDao;
	
	@Autowired
	public void setFirstMvcDao(FirstMvcDao firstMvcDao) {
		this.firstMvcDao = firstMvcDao;
	}

	@Override
	public String getMessage(int no, String id) {
		return firstMvcDao.getMessage(no, id);
	}
}