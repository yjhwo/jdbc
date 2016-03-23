package com.estsoft.ticketing.dao.test;

import java.util.List;
import com.estsoft.ticketing.dao.CustomerDAO;
import com.estsoft.ticketing.vo.CustomerVO;

public class CustomerDaoTest {

	public static void main(String[] args) {
			
		//insertTest();
		
		getListTest();
	}

	public static void getListTest(){
		List<CustomerVO> list = new CustomerDAO().getList();
		
		for(CustomerVO customervo : list){
			System.out.println(customervo);
		}
	}
	
	public static void insertTest(){
		CustomerVO customerVo = new CustomerVO(null, "010-1111-2222", 1, 1L);
		CustomerDAO customerDao = new CustomerDAO();
		customerDao.insert(customerVo);
		

	}
}
