package com.estsoft.bookshop.dao.test;

import java.util.List;

import com.estsoft.bookshop.dao.AuthorDAO;
import com.estsoft.bookshop.vo.AuthorVO;

public class AuthorDaoTest {

	public static void main(String[] args) {
		// 1. insert test
		//insertTest();
		
		// 2. getList test
		getListTest();
		
	}

	public static void getListTest(){
		AuthorDAO authorDao = new AuthorDAO();
		List<AuthorVO> list = authorDao.getList();
		
		for(AuthorVO vo : list){
			System.out.println(vo);
		}
	}
	
	public static void insertTest(){
		AuthorVO authorVo = new AuthorVO();
//		authorVo.setName("스테파니메이어");
		AuthorDAO authorDao = new AuthorDAO();
//		authorDao.insert(authorVo);
		
		authorVo.setName("조정래");
		authorDao.insert(authorVo);
		
		authorVo.setName("김동인");
		authorDao.insert(authorVo);
		
		authorVo.setName("김난도");
		authorDao.insert(authorVo);
		
		authorVo.setName("천상병");
		authorDao.insert(authorVo);
	}
}
