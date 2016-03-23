package com.estsoft.bookshop.dao;

import java.sql.*;
import java.util.*;
import com.estsoft.bookshop.vo.BookVO;

public class BookDAO {
	
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
	
	public void updateState(BookVO bookVO){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
			String sql = "UPDATE book SET state = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bookVO.getState());
			pstmt.setLong(2, bookVO.getNo());
			
			pstmt.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("error: "+ex);
		}finally { // 6.자원정리
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void insert(BookVO bookVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "insert into book values(null,?,'available',?)";
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setString(1, bookVO.getTitle());
			pstmt.setLong(2, bookVO.getAuthorNo());

			pstmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally { // 6.자원정리
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public BookVO get(Long no){
		BookVO bookVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {	
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "SELECT a.no, a.title, IF(a.state='rent','대여중','재고있음'), b.name FROM book a, author b "+
						"WHERE a.author_no = b.no AND a.no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Long no2 = rs.getLong(1);
				String title = rs.getString(2);
				String state = rs.getString(3);
				String authorName = rs.getString(4);
				
				bookVo = new BookVO();
				bookVo.setNo(no2);
				bookVo.setTitle(title);
				bookVo.setState(state);
				bookVo.setAuthorName(authorName);
			}

		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally { // 6.자원정리
			try {
				if(rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bookVo;
	}
	
	public List<BookVO> getList(){
		List<BookVO> list = new ArrayList<BookVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT a.no, a.title, IF(a.state='rent','대여중','재고있음'), b.name FROM book a, author b "+
						"WHERE a.author_no = b.no ORDER BY a.no ASC";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String state = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVO bookVo = new BookVO();
				bookVo.setNo(no);
				bookVo.setTitle(title);
				bookVo.setState(state);
				bookVo.setAuthorName(authorName);
				
				list.add(bookVo);
			}
			
		}catch(SQLException ex){
			System.out.println("error:" + ex);
		}finally { // 6.자원정리
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
}
