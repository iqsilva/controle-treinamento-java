package Table;
import javax.swing.table.*;
import java.util.*;
import Bean.*;
@SuppressWarnings("serial")
public class CursoTableModel extends AbstractTableModel{
	private static final int col_nome_curso=0;
	private static final int col_vigencia_curso=1;
	private static final int col_carga_curso=2;
	private List<Curso> valores;
	public CursoTableModel(List<Curso> valores){
		this.valores=valores;
	}
	public int getColumnCount() {
		return 3;
	}
	public int getRowCount(){
		return valores.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Curso curso=valores.get(rowIndex);
		if(columnIndex==col_nome_curso){
			return curso.getDesc_curso();
		}else if(columnIndex==col_vigencia_curso){
			return curso.getVigencia();
		}else if(columnIndex==col_carga_curso){
			return curso.getCarga_horaria();
		}
		return null;
	}
	public String getColumnName(int colunm){
		String coluna="";
		switch(colunm){
			case col_nome_curso:
				coluna="Nome do Curso";
				break;
			case col_vigencia_curso:
				coluna="Vigência(Anos)";
				break;
			case col_carga_curso:
				coluna="Carga Horária(Dias)";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
		}
		return coluna;
	}
	
	public Class<?> getColunmClass(int columnIndex){
		if(columnIndex==col_nome_curso){
			return String.class;
		}else if(columnIndex==col_carga_curso){
			return int.class;
		}else if(columnIndex==col_vigencia_curso){
			return int.class;
		}
		return null;
	}
	public Curso get(int row){
		return valores.get(row);
	}
}