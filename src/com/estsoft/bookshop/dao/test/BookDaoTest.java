package com.estsoft.bookshop.dao.test;

import java.util.List;

import com.estsoft.bookshop.dao.BookDAO;
import com.estsoft.bookshop.vo.BookVO;

public class BookDaoTest {

	public static void main(String[] args) {
		
		// insert test
//		insertTest();
//		
		// updateState test
		updateStateTest();		
		
		// getList test
		getListTest();
		
		// get test
		
	}
	
	public static void updateStateTest(){
		BookVO bookVo = new BookVO();
		bookVo.setNo(5L);
		bookVo.setState("rent");
		
		new BookDAO().updateState(bookVo);
	}
	
	public static void getListTest(){
		List<BookVO> list = new BookDAO().getList();
		
		for(BookVO bookvo : list){
			System.out.println(bookvo);
		}
	}
	
	public static void insertTest(){
		BookVO bookVo = new BookVO();
		BookDAO bookDao = new BookDAO();
		
		bookVo.setTitle("트와일라잇");
		bookVo.setAuthorNo(1L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("뉴문");
		bookVo.setAuthorNo(1L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("이클립스");
		bookVo.setAuthorNo(1L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("브레이킹던");
		bookVo.setAuthorNo(1L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("아리랑");
		bookVo.setAuthorNo(2L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("젊은그들");
		bookVo.setAuthorNo(3L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("아프니까 청춘이다");
		bookVo.setAuthorNo(4L);
		bookDao.insert(bookVo);
		
		bookVo.setTitle("귀천");
		bookVo.setAuthorNo(5L);
		bookDao.insert(bookVo);
	
	}
}
