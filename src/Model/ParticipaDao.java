package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Bean.Participa;
import Service.DBConnect;
@SuppressWarnings("serial")
public class ParticipaDao extends ManipulateDates implements IParticipa {
	private static final String SQLInsert="INSERT INTO Participa(Cod_Edicao,Cod_Func,Status) VALUES(?,?,?)";
	private static final String SQLVerInsert="SELECT p.Cod_Func,c.Cod_Curso,p.Cod_Edicao FROM Participa p LEFT JOIN Edicao e ON e.Cod_Edicao=p.Cod_Edicao LEFT JOIN Curso c ON e.Cod_Curso=c.Cod_Curso WHERE c.Cod_Curso=? AND  p.Cod_Func=? AND (p.Status=? OR p.Status=? or p.Status=?)";
	private static final String SQLUpdate="UPDATE Participa SET Status=? WHERE Cod_Participa=?";
	private static final String SQLRemove="UPDATE Participa SET Status=? WHERE Cod_Participa=?";
	
	private static final String SQLEspera="SELECT f.Nome_Func,c.Descricao,e.Data_Inicio,p.Cod_Participa FROM Funcionario f LEFT JOIN Participa p  "
			+ "ON p.Cod_Func=f.Cod_Func LEFT JOIN Edicao e ON p.Cod_Edicao=e.Cod_Edicao LEFT JOIN "
			+ "Curso c ON e.Cod_Curso=c.Cod_Curso WHERE p.Status=? AND e.Data_Inicio>GETDATE() ORDER BY e.Data_Inicio ";
	private static final String SQLEspera2="SELECT f.Nome_Func,c.Descricao,e.Data_Inicio,p.Cod_Participa,e.Status_Edicao FROM Funcionario f LEFT JOIN Participa p  "
			+ "ON p.Cod_Func=f.Cod_Func LEFT JOIN Edicao e ON p.Cod_Edicao=e.Cod_Edicao LEFT JOIN "
			+ "Curso c ON e.Cod_Curso=c.Cod_Curso WHERE p.Status=? AND f.Cod_Func=? AND e.Data_Inicio>GETDATE() ORDER BY e.Data_Inicio ";
	
	private static final String SQLConcluido="UPDATE Participa SET Status=? FROM Edicao e LEFT JOIN Participa p ON p.Cod_Edicao=e.Cod_Edicao WHERE (e.Data_Fim=CAST(GETDATE() AS DATE) OR CAST(GETDATE() AS DATE)>e.Data_Fim) AND p.Status=? AND p.Cod_Func=? ";
	private static final String SQLConcluido2="SELECT c.Descricao, e.Validade, p.Cod_Participa from Participa p LEFT JOIN Edicao e ON p.Cod_Edicao=e.Cod_Edicao LEFT JOIN Curso c ON e.Cod_Curso=c.Cod_Curso "
			+ "WHERE p.Cod_Func=? AND p.Status=? ORDER BY e.Validade";
	
	private static final String SQLConcluido3="UPDATE Participa SET Status=? FROM Edicao e LEFT JOIN Participa p ON p.Cod_Edicao=e.Cod_Edicao WHERE CAST(GETDATE() AS DATE)>=e.Validade ";
	private static final String SQLAprovado="SELECT c.Descricao, e.Data_Inicio,e.Data_Fim,e.Validade,e.Status_Edicao,p.Cod_Participa FROM Participa p LEFT JOIN Edicao e ON p.Cod_Edicao=e.Cod_Edicao "
			+ "LEFT JOIN Curso c ON c.Cod_Curso=e.Cod_Curso "
			+ "WHERE p.Cod_Func=? AND p.Status=? ORDER BY e.Data_Inicio";	
	public int save(Participa part , int cod_curso) {
		//MÉTODO PARA SOLICITAR UM CURSO
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			pstm=conn.prepareStatement(SQLVerInsert);
			pstm.setInt(1, cod_curso);
			pstm.setInt(2, part.getCod_func());
			pstm.setString(3, "E");
			pstm.setString(4, "A");
			pstm.setString(5, "C");
			rs=pstm.executeQuery();
			if(rs.next()){
				result=0;
			}else{
				pstm=conn.prepareStatement(SQLInsert);
				pstm.setInt(1, part.getCod_edicao());
				pstm.setInt(2, part.getCod_func());
				pstm.setString(3, "E");
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

	public int update(int cod, String status) {
		//MÉTODO PARA APROVAR A SOLICITAÇÃO DE UM CURSO
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(SQLUpdate);
			pstm.setString(1, status);
			pstm.setInt(2, cod);
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
	
	public int remove(int cod, String status) {
		//MÉTODO PARA REPROVAR UMA SOLICTIAÇÃO DE CURSO
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(SQLRemove);
			pstm.setString(1, status);
			pstm.setInt(2, cod);
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

	public List<Participa> findE(String status,int cod_func) {
		//MÉTODO PARA ACHAR OS CURSOS EM ESPERA
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Participa> participacoes=new ArrayList<Participa>();
		try {
			if(cod_func==0){
				pstm=conn.prepareStatement(SQLEspera);
				pstm.setString(1, status);
				rs=pstm.executeQuery();
				while(rs.next()){
					Participa p = new Participa();
					p.setNome_func(rs.getString("Nome_Func"));
					p.setDescricao_curso(rs.getString("Descricao"));
					p.setData_inicio(DateToStr(rs.getDate("Data_Inicio")));
					p.setCod_participa(rs.getInt("Cod_Participa"));
					participacoes.add(p);
				}
			}else{
				pstm=conn.prepareStatement(SQLEspera2);
				pstm.setString(1, status);
				pstm.setInt(2, cod_func);
				rs=pstm.executeQuery();
				while(rs.next()){
					if(rs.getString("Status_Edicao").equals("F")){
						pstm=conn.prepareStatement("UPDATE Participa SET Status=? WHERE Cod_Participa=?");
						pstm.setString(1, "R");
						pstm.setInt(2, rs.getInt("Cod_Participa"));
						pstm.executeUpdate();
					}else{
						Participa p = new Participa();
						p.setNome_func(rs.getString("Nome_Func"));
						p.setDescricao_curso(rs.getString("Descricao"));
						p.setData_inicio(DateToStr(rs.getDate("Data_Inicio")));
						p.setCod_participa(rs.getInt("Cod_Participa"));
						participacoes.add(p);
					
					}
				}
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
		return participacoes;
	}
	public List<Participa> findC(String status,int cod_func) {
		//MÉTODO PARA ACHAR OS CURSOS CONCLUIDOS
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Participa> participacoes=new ArrayList<Participa>();
		try {
			pstm=conn.prepareStatement(SQLConcluido);
			pstm.setString(1, "C");
			pstm.setString(2, "A");
			pstm.setInt(3, cod_func);
			pstm.executeUpdate();
			pstm.close();
			
			pstm=conn.prepareStatement(SQLConcluido3);
			pstm.setString(1, "R");
			pstm.executeUpdate();
			pstm.close();
			
			pstm=conn.prepareStatement(SQLConcluido2);
			pstm.setInt(1, cod_func);
			pstm.setString(2, status);
			rs=pstm.executeQuery();
			while(rs.next()){
				Participa p = new Participa();
				p.setDescricao_curso(rs.getString("Descricao"));
				p.setValidade(DateToStr(rs.getDate("Validade")));
				p.setCod_participa(rs.getInt("Cod_Participa"));
				participacoes.add(p);
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
		return participacoes;
	}
	public List<Participa> findA(String status, int cod_func) {
		//MÉTODO QUE TRAZ OS CURSOS APROVADOS
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Participa> participacoes=new ArrayList<Participa>();
		try {
			pstm=conn.prepareStatement(SQLAprovado);
			pstm.setInt(1, cod_func);
			pstm.setString(2, status);
			rs=pstm.executeQuery();
			while(rs.next()){
				if(rs.getString("Status_Edicao").equals("F")){
					pstm=conn.prepareStatement("UPDATE Participa SET Status=? WHERE Cod_Participa=?");
					pstm.setString(1, "R");
					pstm.setInt(2, rs.getInt("Cod_Participa"));
					pstm.executeUpdate();
				}else{
					Participa p = new Participa();
					p.setDescricao_curso(rs.getString("Descricao"));
					p.setData_inicio(DateToStr(rs.getDate("Data_Inicio")));
					p.setData_fim(DateToStr(rs.getDate("Data_Fim")));
					p.setValidade(DateToStr(rs.getDate("Validade")));
					participacoes.add(p);
				}
				
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
		return participacoes;
	}
}