package com.atguigu.service;


import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//import javax.inject.Inject;


@Service
public class BookService {

	@Resource(name="bookDao2")
//	@Inject
	// 优先级比名字高，及时看起来装备的是bookDao2，明确指定
//	@Qualifier("bookDao2")
//	@Autowired(required = false)
	private BookDao bookDao;
//
	public void print(){
		System.out.println(bookDao);
	}
//
	@Override
	public String toString() {
		return "BookService [bookDao=" + bookDao + "]";
	}


}
