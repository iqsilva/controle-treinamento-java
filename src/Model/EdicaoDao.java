package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Bean.Edicao;
import Service.DBConnect;
@SuppressWarnings("serial")
public class EdicaoDao extends ManipulateDates implements IEdicaoDao{
	private static final String SQLInsert="INSERT INTO Edicao(Data_Inicio,Cod_Curso,Status_Edicao) VALUES(?,?,?)";
	private static final String SQLVerInsert="SELECT * FROM Edicao WHERE Data_Inicio=? AND Cod_Curso=? AND Status_Edicao=?";
	private static final String SQLUpdate="UPDATE Edicao SET Data_Inicio=? WHERE Cod_Edicao=?";
	private static final String SQLRemoveOld="UPDATE Edicao SET Status_Edicao=? WHERE Data_Inicio<=GETDATE() AND Cod_Curso=?";
	private static final String SQLRemove="UPDATE Edicao SET Status_Edicao=? WHERE Cod_Edicao=?";
	private static final String SQLSelect="SELECT e.Cod_Edicao,e.Cod_Curso,e.Data_Inicio ,e.Data_Fim,e.Validade FROM Edicao e WHERE e.Status_Edicao=? AND e.cod_curso=? ORDER BY e.Data_Inicio";
	public int save(Edicao edic) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			pstm=conn.prepareStatement(SQLVerInsert);
			pstm.setDate(1, super.StrToDate(edic.getData_Inicio()));
			pstm.setInt(2, edic.getCod_Curso());
			pstm.setString(3,"T");
			rs=pstm.executeQuery();
			if(rs.next()){
				result=0;
			}else{
				pstm=conn.prepareStatement(SQLInsert);
				pstm.setDate(1, super.StrToDate(edic.getData_Inicio()));
				pstm.setInt(2, edic.getCod_Curso());
				pstm.setString(3, "T");
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
	
	public int update(Edicao edic) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			pstm=conn.prepareStatement(SQLVerInsert);
			pstm.setDate(1, super.StrToDate(edic.getData_Inicio()));
			pstm.setInt(2, edic.getCod_Curso());
			pstm.setString(3,"T");
			rs=pstm.executeQuery();
			if(rs.next()){
				result=0;
			}else{
				pstm=conn.prepareStatement(SQLUpdate);
				pstm.setDate(1, super.StrToDate(edic.getData_Inicio()));
				pstm.setInt(2, edic.getCod_Edicao());
				result=pstm.executeUpdate();
			}
		} catch (SQLException e) {
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					DBConnect.close(conn, pstm, null);
				}
			}
			e.printStackTrace();
		}
		return result;
	}
	public int remove(int cod_edicao) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			pstm=conn.prepareStatement(SQLRemove);
			pstm.setString(1, "F");
			pstm.setInt(2, cod_edicao);
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

	public List<Edicao> findAll(int cod_curso) {
		
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Edicao> edicoes=new ArrayList<Edicao>();
		try {
			pstm=conn.prepareStatement(SQLSelect);
			pstm.setString(1, "T");
			pstm.setInt(2, cod_curso);
			rs=pstm.executeQuery();
			while(rs.next()){
				Edicao ed = new Edicao();
				ed.setCod_Edicao(rs.getInt("Cod_Edicao"));
				ed.setCod_Curso(rs.getInt("Cod_Curso"));
				ed.setData_Inicio(super.DateToStr(rs.getDate("Data_Inicio")));
				ed.setData_Fim(super.DateToStr(rs.getDate("Data_Fim")));
				ed.setValidade(super.DateToStr(rs.getDate("Validade")));
				edicoes.add(ed);
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
		return edicoes;
	}
	public int removeOld(int cod_curso) {
	
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(SQLRemoveOld);
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
					DBConnect.close(conn, pstm, null);
				}
			}
			e.printStackTrace();
		}
		return result;
	}
}
