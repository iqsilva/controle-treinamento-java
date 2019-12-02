package Table;
import java.util.*;
import javax.swing.table.*;
import Bean.*;
@SuppressWarnings("serial")
public class PEsperaTableModel extends AbstractTableModel{
	private static final int col_nome_func=0;
	private static final int col_desc_curso=1;
	private static final int col_data_inicio=2;
	private List<Participa> valores;
	public PEsperaTableModel(List<Participa> valores){
		this.valores=valores;
	}
	public int getColumnCount() {
		return 3;
	}
	public int getRowCount() {
		return valores.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Participa part=valores.get(rowIndex);
		if(columnIndex==col_nome_func){
			return part.getNome_func();
		}else if(columnIndex==col_desc_curso){
			return part.getDescricao_curso();
		}else if(columnIndex==col_data_inicio){
			return part.getData_inicio();
		}
		return null;
	}
	public String getColumnName(int colunm){
		String coluna="";
		switch(colunm){
			case col_nome_func:
				coluna="Funcionário";
				break;
			case col_desc_curso:
				coluna="Curso";
				break;
			case col_data_inicio:
				coluna="Data de Início";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
		}
		return coluna;
	}
	public Class<?> getColunmClass(int columnIndex){
		if(columnIndex==col_nome_func){
			return String.class;
		}else if(columnIndex==col_desc_curso){
			return String.class;
		}else if(columnIndex==col_data_inicio){
			return String.class;
		}
		return null;
	}
	public Participa get(int row){
		return valores.get(row);
	}
}