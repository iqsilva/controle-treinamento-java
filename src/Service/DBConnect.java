package Service;
import java.sql.*;
public class DBConnect {
	Connection conn;
	private static final String connectionUrl= "jdbc:sqlserver://localhost:1433;"
			+"databaseName=bdAzul;";
	public static Connection getConnection(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			return DriverManager.getConnection(connectionUrl,"sa", "1234");
			
		}catch(SQLException ex){
			System.out.println("SqlException: "+ex.getMessage());
			System.out.println("SqlState: "+ex.getSQLState());
			System.out.println("Error: "+ex.getErrorCode());
		}catch(Exception e){
			System.out.println("Não foi possível conectar ao banco: "+e);
		}
		return null;	
	}
	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
