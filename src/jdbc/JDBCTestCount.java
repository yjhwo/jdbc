package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//03.22(5)
public class JDBCTestCount {

	public static void main(String[] args) {
			
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			
			// 4. SQL 실행
			String sql = "select count(*) from pet";
			rs = stmt.executeQuery(sql);
			
			// 5. 데이터 받아오기
			int count = 0;
			if(rs.next()){
				count = rs.getInt(1);
			}
			System.out.println("전체 갯수: "+count);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다."+e);
		} catch(SQLException ex){
			System.out.println("SQL 오류"+ex);
		} finally{
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
