package com.estsoft.ticketing.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.ticketing.vo.CustomerVO;


public class CustomerDAO {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;

		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다." + e);
		} 

		return conn;
	}
	
	public int remove(String phone, Long movieNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "delete from customer where phone=? and movie_no=?";	// 폰번호가 같고  
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setString(1, phone);
			pstmt.setLong(2, movieNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally {
			try {
				if (pstmt != null)			pstmt.close();
				if (conn != null)			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	public List<CustomerVO> get(String phone){	// 핸드폰 번호로 정보 얻음
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		CustomerVO customerVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {	
			conn = getConnection();

			// 3. Statement 준비
			String sql = "SELECT b.title, a.reservation, a.movie_no FROM customer a, movie b "+
						"WHERE a.movie_no = b.no AND a.phone = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String title = rs.getString(1);
				int reservation = rs.getInt(2);
				Long movieNo = rs.getLong(3);
				
				customerVo = new CustomerVO(title, reservation, movieNo);
				list.add(customerVo);
			}

		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally { // 6.자원정리
			try {
				if(rs != null)		rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<CustomerVO> getList(){
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT a.no, a.phone, a.reservation, a.movie_no, b.title FROM customer a, movie b "+
						"WHERE a.movie_no = b.no ORDER BY a.no ASC";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String phone = rs.getString(2);
				int reservation = rs.getInt(3);
				Long movie_no = rs.getLong(4);
				String title = rs.getString(5);
				
				CustomerVO customerVo = new CustomerVO(no, phone, reservation, movie_no, title);				
				list.add(customerVo);
			}
			
		}catch(SQLException ex){
			System.out.println("error:" + ex);
		}finally { // 6.자원정리
			try {
				if (rs != null)			rs.close();
				if (stmt != null)		stmt.close();
				if (conn != null)		conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insert(CustomerVO customerVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "insert into customer values(null,?,?,?)";	// 번호, 연락처,예매 수, 영화번호
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setString(1, customerVo.getPhone());
			pstmt.setInt(2, customerVo.getReservation());
			pstmt.setLong(3,customerVo.getMovieNo());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt<1)
				System.out.println("삽입에 실패하였습니다.");
			else
				System.out.println("삽입에 성공하였습니다.");
			
		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally { // 6.자원정리
			try {
				if (pstmt != null)			pstmt.close();
				if (conn != null)			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
