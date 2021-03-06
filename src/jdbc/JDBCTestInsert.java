package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//03.22(2)
public class JDBCTestInsert {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			
			// 4. SQL 실행
			String sql = "insert into pet values('마음이','찬이','dog','m','2006-09-10','0000-00-00')";
			int res = stmt.executeUpdate(sql);
			if(res <1)
				System.out.println("삽입에 실패하였습니다.");
			else
				System.out.println("삽입에 성공하였습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다."+e);
		} catch(SQLException ex){
			System.out.println("SQL 오류"+ex);
		} finally{
			try {
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
