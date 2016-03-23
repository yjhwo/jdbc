package com.estsoft.ticketing.app;

import java.util.List;
import java.util.Scanner;

import com.estsoft.ticketing.dao.CustomerDAO;
import com.estsoft.ticketing.dao.MovieDAO;
import com.estsoft.ticketing.vo.CustomerVO;
import com.estsoft.ticketing.vo.MovieVO;

public class TicketingSystem {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int chk = 0;
		
		do{			
			System.out.println("== Ticketing System ==");
			System.out.println("1. Reserve");
			System.out.println("2. Cancel");
			System.out.println("3. Lookup");
			System.out.println("4. Exit");
			System.out.print(">>");
			
			chk = sc.nextInt();
			
			if(chk == 1){	// 예약
				reservation();
			}else if(chk == 2){	// 취소
				cancel();
			}else if(chk == 3){	// 예약 정보
				lookup();
			}
			
		}while(chk!= 4);
		
		
		sc.close();
	}

	private static void cancel() {
		
		// 1. 고객의 전화 번호 입력 받기
		System.out.print("phone> ");
		Scanner sc = new Scanner(System.in);
		String phone = sc.next();
		
		// 2. 예매 리스트 출력 (lookup)
		List<CustomerVO> list = lookup(phone);
		
		// 3. 예매 번호 입력 하면 취소 
		System.out.print(">");
		int num = sc.nextInt();	

		// 4. 영화 번호 유추하기.............
		Long movieNo = list.get(num).getMovieNo();
		CustomerDAO customerDao = new CustomerDAO();
		int chk = customerDao.remove(phone, movieNo);
		
		if(chk>0)
			System.out.println("Canceled");
		else
			System.out.println("취소에 실패하였습니다.");
		
	}

	private static List<CustomerVO> lookup(String phone) {
		System.out.println("== Lookup ==");
		
		// 2. dao에서 번호로 get....
		CustomerDAO customerDao = new CustomerDAO();
		List<CustomerVO> list = customerDao.get(phone);
		for(int i=0; i<list.size();i++){
			System.out.println((i+1)+"."+list.get(i).getMovieName()+":"+list.get(i).getReservation());
		}
		
		return list;
		
	}

	private static void lookup() {
		// 고객의 영화 예매 목록 출력
		
		// 1. 핸드폰 번호 입력
		System.out.print("phone> ");
		Scanner sc = new Scanner(System.in);
		String phone = sc.next();
		
		System.out.println("== Lookup ==");
		
		// 2. dao에서 번호로 get....
		CustomerDAO customerDao = new CustomerDAO();
		List<CustomerVO> list = customerDao.get(phone);

		for(int i=0; i<list.size();i++){
			System.out.println((i+1)+"."+list.get(i).getMovieName()+":"+list.get(i).getReservation());
		}
				
		
	}

	private static void reservation() {
		
		// 1. 영화 목록 출력
		List<MovieVO> list = new MovieDAO().getList();
		for (MovieVO vo : list) {
			System.out.println(vo);
		}
		
		Scanner sc = new Scanner(System.in);
		// 2. 예약할 영화 입력 받기
		System.out.print("> ");
		int movieNo = sc.nextInt();
		
		// 3. 고객 핸드폰 번호 입력 받기
		System.out.print("phone> ");
		String phone = sc.next();
		
		// 4. 예약 매 수 입력 받기
		System.out.print("예약할 매 수: ");
		int reservation = sc.nextInt();
		
		// 5. 예약 처리(insert)
		CustomerVO customerVo = new CustomerVO(null, phone, reservation, (long)movieNo);
		CustomerDAO customerDao = new CustomerDAO();
		customerDao.insert(customerVo);
		
	}

}
