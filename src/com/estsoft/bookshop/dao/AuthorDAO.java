package com.estsoft.bookshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookshop.vo.AuthorVO;

public class AuthorDAO {

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

	public List<AuthorVO> getList() {
		List<AuthorVO> list = new ArrayList<AuthorVO>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			// 3. Statement 생성
			stmt = conn.createStatement();

			// 4. SQL 실행
			String sql = "select no,name from author";
			rs = stmt.executeQuery(sql);

			// 5. 데이터 받아오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				AuthorVO authorVo = new AuthorVO();
				authorVo.setNo(no);
				authorVo.setName(name);

				list.add(authorVo);
			}

		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally {
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

	public void insert(AuthorVO authorVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "insert into author values(null,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setString(1, authorVO.getName());

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
}
