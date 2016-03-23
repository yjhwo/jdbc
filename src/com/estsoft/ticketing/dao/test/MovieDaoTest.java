package com.estsoft.ticketing.dao.test;

import java.util.List;

import com.estsoft.ticketing.dao.MovieDAO;
import com.estsoft.ticketing.vo.MovieVO;

public class MovieDaoTest {

	public static void main(String[] args) {
		
		//insertTest();
		
		getListTest();
	}
	
	public static void getListTest(){
		List<MovieVO> list = new MovieDAO().getList();
		
		for(MovieVO vo : list){
			System.out.println(vo);
		}
	}
	
	
	public static void insertTest(){
		MovieVO movieVo = new MovieVO(null,"The Lord of the Rings");
		MovieDAO movieDao = new MovieDAO();
		movieDao.insert(movieVo);
		
		movieVo = new MovieVO(null,"The Matrix");
		movieDao.insert(movieVo);
		
		movieVo = new MovieVO(null,"Pride & Prejudice");
		movieDao.insert(movieVo);
	}
}
