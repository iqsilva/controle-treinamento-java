package Table;
import java.util.*;
import javax.swing.table.*;
import Bean.*;
@SuppressWarnings("serial")
public class FuncTableModel extends AbstractTableModel{
	//private static final int col_cod_func=0;
	private static final int col_nome_func=0;
	private static final int col_funcao_func=1;
	private static final int col_re_func=2;
	//private static final int col_email_func=4;
	private List<Funcionario> valores;
	public FuncTableModel(List<Funcionario> valores){
		this.valores=valores;
	}
	public int getColumnCount() {
		return 3;
	}
	public int getRowCount() {
		return valores.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Funcionario func=valores.get(rowIndex);
		/*if(columnIndex==col_cod_func){
			return func.getCod_user();
		}else if(columnIndex==col_email_func){
			return func.getEmail_func();
		}else*/ 
		
		if(columnIndex==col_funcao_func){
			return func.getFuncao_func();
		}else if(columnIndex==col_nome_func){
			return func.getNome_func();
		}else if(columnIndex==col_re_func){
			return func.getRe_func();
		}
		return null;
	}
	public String getColumnName(int colunm){
		String coluna="";
		switch(colunm){
			/*case col_cod_func:
				coluna="Código";
				break;
			case col_email_func:
				coluna="Email";
				break;
				*/
			case col_funcao_func:
				coluna="Função";
				break;
			case col_nome_func:
				coluna="Nome";
				break;
			case col_re_func:
				coluna="Registro do Empregado";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
		}
		return coluna;
	}
	public Class<?> getColunmClass(int columnIndex){
		/*if(columnIndex==col_cod_func){
			return int.class;
		}else if(columnIndex==col_email_func){
			return String.class;
		}else*/ 
		if(columnIndex==col_funcao_func){
			return String.class;
		}else if(columnIndex==col_nome_func){
			return String.class;
		}else if(columnIndex==col_re_func){
			return String.class;
		}
		return null;
	}
	public Funcionario get(int row){
		return valores.get(row);
	}
}
