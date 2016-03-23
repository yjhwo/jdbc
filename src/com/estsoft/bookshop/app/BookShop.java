package com.estsoft.bookshop.app;

import java.util.*;

import com.estsoft.bookshop.dao.BookDAO;
import com.estsoft.bookshop.vo.BookVO;

public class BookShop {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요: ");
		int no = sc.nextInt();
		
		BookVO bookVo = new BookVO();
		bookVo.setNo((long)no);
		bookVo.setState("rent");
		new BookDAO().updateState(bookVo);
		
		sc.close();
		printBookList();
	}

	public static void printBookList(){
		BookDAO bookDao = new BookDAO();
		List<BookVO> list = bookDao.getList();
		
		System.out.println("*****도서정보 출력하기*****");
		for(BookVO bookVo : list){
			System.out.println("책 제목:"+bookVo.getTitle() +",작가:"+bookVo.getAuthorName()+",대여 유무:"+bookVo.getState());
		}
	}
}
