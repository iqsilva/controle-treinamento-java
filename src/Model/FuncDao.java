package Model;
import java.sql.*;
import java.util.*;
import Bean.Funcionario;
import Service.DBConnect;
public class FuncDao implements IFuncDao{
	private static final String SQLInsert="INSERT INTO Funcionario(Nome_Func,Funcao,Registro_Empregado,Nome_Usuario,Senha,Status,Email_Func) values(?,?,?,?,?,?,?)";
	private static final String SQLVerInsert="SELECT * FROM Funcionario WHERE Nome_Usuario=?";
	private static final String SQLUpdate="UPDATE Funcionario SET Nome_Func=?,Funcao=?,Registro_Empregado=? WHERE Cod_Func=?";
	private static final String SQLUpdate2="UPDATE Funcionario SET Email_Func=?,Senha=? WHERE Cod_Func=?";
	private static final String SQLRemove="UPDATE Funcionario SET Status=? WHERE Cod_Func=?";
	private static final String SQLSelect="SELECT * FROM Funcionario WHERE Status=? ORDER BY Nome_Func";
	private static final String SQLLogin="SELECT * FROM Funcionario WHERE Nome_Usuario=? AND Senha=? AND Status=?";
	public int save(Funcionario func) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			pstm=conn.prepareStatement(SQLVerInsert);
			pstm.setString(1, func.getNome_user());
			rs=pstm.executeQuery();
			if(rs.next()){
				result=0;
			}else{
				pstm=conn.prepareStatement(SQLInsert);
				pstm.setString(1, func.getNome_func());
				pstm.setString(2, func.getFuncao_func());
				pstm.setString(3, func.getRe_func());
				pstm.setString(4, func.getNome_user());
				pstm.setString(5, func.getSenha_user());
				pstm.setString(6, "T");
				pstm.setString(7, func.getEmail_func());
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
	
	public int update(Funcionario func) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(SQLUpdate);
			pstm.setString(1, func.getNome_func());
			pstm.setString(2, func.getFuncao_func());
			pstm.setString(3, func.getRe_func());
			pstm.setInt(4, func.getCod_user());
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
	
	public int remove(int cod_user) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(SQLRemove);
			pstm.setString(1, "F");
			pstm.setInt(2, cod_user);
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
	
	public List<Funcionario> findAll() {
		Connection conn = DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Funcionario> funcionarios= new ArrayList<Funcionario>();
		try {
			pstm=conn.prepareStatement(SQLSelect);
			pstm.setString(1, "T");
			rs=pstm.executeQuery();
			while(rs.next()){
				Funcionario func = new Funcionario();
				func.setCod_user(rs.getInt("Cod_Func"));
				func.setFuncao_func(rs.getString("Funcao"));
				func.setNome_func(rs.getString("Nome_Func"));
				func.setNome_user(rs.getString("Nome_Usuario"));
				func.setRe_func(rs.getString("Registro_Empregado"));
				func.setSenha_user(rs.getString("Senha"));
				func.setStatus_func(rs.getString("Status"));
				funcionarios.add(func);
			}
		} catch (SQLException e) {
			try {
				if(conn != null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally{
				DBConnect.close(conn, pstm, rs);
			}
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
	public int login(String nome_user, String senha_user) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			pstm=conn.prepareStatement(SQLLogin);
			pstm.setString(1, nome_user);
			pstm.setString(2, senha_user);
			pstm.setString(3, "T");
			rs=pstm.executeQuery();
			if(rs.next()){		
				result=1;
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
	public List<Funcionario> session(String nome_user,String senha_user) {
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Funcionario> sessao = new ArrayList<Funcionario>();
		try {
			pstm=conn.prepareStatement(SQLLogin);
			pstm.setString(1, nome_user);
			pstm.setString(2, senha_user);
			pstm.setString(3, "T");
			rs=pstm.executeQuery();
			while(rs.next()){
				Funcionario func=new Funcionario();
				func.setCod_user(rs.getInt("Cod_Func"));
				func.setEmail_func(rs.getString("Email_Func"));
				func.setFuncao_func(rs.getString("Funcao"));
				func.setNome_func(rs.getString("Nome_Func"));
				func.setNome_user(rs.getString("Nome_Usuario"));
				func.setRe_func(rs.getString("Registro_Empregado"));
				func.setSenha_user(rs.getString("Senha"));
				func.setStatus_func(rs.getString("Status"));
				sessao.add(func);
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
		return sessao;
	}

	public int update2(Funcionario func) {
		int result=0;
		Connection conn=DBConnect.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(SQLUpdate2);
			pstm.setString(1, func.getEmail_func());
			pstm.setString(2, func.getSenha_user());
			pstm.setInt(3, func.getCod_user());
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
			e.printStackTrace();
			}
		}
		return result;
	}
}
