package Model;
import java.sql.*;
import java.util.*;
import Bean.Curso;
import Service.DBConnect;
@SuppressWarnings("serial")
public class CursoDao extends ManipulateDates implements ICursoDao{
	private static final String SQLInsert="INSERT INTO Curso(Descricao,Vigencia,Status,Carga_Horaria) VALUES(?,?,?,?)";
	private static final String SQLVerInsert="SELECT * FROM Curso WHERE Descricao=?";
	private static final String SQLUpdate="UPDATE Curso SET Descricao=?,Vigencia=?,Status=?,Carga_Horaria=? WHERE Cod_Curso=?";
	private static final String SQLRemove="UPDATE Curso SET Status=? WHERE Cod_Curso=?";
	private static final String SQLSelect="SELECT * FROM Curso WHERE Status = ? ORDER BY Descricao";
	public int save(Curso curso) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			pstm=conn.prepareStatement(SQLVerInsert);
			pstm.setString(1, curso.getDesc_curso());
			rs=pstm.executeQuery();
			if(rs.next()){
				result=0;
			}else{
				pstm=conn.prepareStatement(SQLInsert);
				pstm.setString(1, curso.getDesc_curso());
				pstm.setInt(2,curso.getVigencia());
				pstm.setString(3, "T");
				pstm.setInt(4, curso.getCarga_horaria());
				result=pstm.executeUpdate();
			}
		} catch (SQLException e) {
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					DBConnect.close(conn, pstm, rs);
				}
			}
			e.printStackTrace();
		}
		return result;
	}
	public int update(Curso curso) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//pstm.setDate(2,super.StrToDate(curso.getVigencia()));
			pstm=conn.prepareStatement(SQLUpdate);
			pstm.setString(1, curso.getDesc_curso());
			pstm.setInt(2, curso.getVigencia());
			pstm.setString(3, "T");
			pstm.setInt(4, curso.getCarga_horaria());
			pstm.setInt(5, curso.getCod_curso());
			result=pstm.executeUpdate();
		} catch (SQLException e) {
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					DBConnect.close(conn, pstm, rs);
				}
			}
			e.printStackTrace();
		}
		return result;
		
	}
	public int remove(int cod_curso) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			pstm=conn.prepareStatement(SQLRemove);
			pstm.setString(1, "F");
			pstm.setInt(2, cod_curso);
			result=pstm.executeUpdate();
		} catch (SQLException e) {
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					DBConnect.close(conn, pstm, rs);
				}
			}
			e.printStackTrace();
		}
		return result;
	}
	public List<Curso> findAll() {
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Curso> cursos = new ArrayList<Curso>();
		try {
			pstm=conn.prepareStatement(SQLSelect);
			pstm.setString(1, "T");
			rs=pstm.executeQuery();
			while(rs.next()){
				Curso curso=new Curso();
				curso.setCod_curso(rs.getInt("Cod_Curso"));
				curso.setDesc_curso(rs.getString("Descricao"));
				curso.setVigencia(rs.getInt("Vigencia"));
				//curso.setVigencia(super.DateToStr(rs.getDate("Vigencia")));
				curso.setCarga_horaria(rs.getInt("Carga_Horaria"));
				curso.setStatus_curso('T');
				cursos.add(curso);
			}
		} catch (SQLException e) {
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					DBConnect.close(conn, pstm, rs);
				}
			}
			e.printStackTrace();
		}
		return cursos;
	}

	
}

