package Table;
import java.util.*;
import javax.swing.table.*;
import Bean.*;
@SuppressWarnings("serial")
public class PConcluidoTableModel extends AbstractTableModel{
	private static final int col_desc=0;
	private static final int col_validade=1;
	private List<Participa> valores;
	public PConcluidoTableModel(List<Participa> valores){
		this.valores=valores;
	}
	public int getColumnCount() {
		return 2;
	}
	public int getRowCount() {
		return valores.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Participa part=valores.get(rowIndex);
		if(columnIndex==col_desc){
			return part.getDescricao_curso();
		}else if(columnIndex==col_validade){
			return part.getValidade();
		}
		return null;
	}
	public String getColumnName(int colunm){
		String coluna="";
		switch(colunm){
			case col_desc:
				coluna="Curso";
				break;
			case col_validade:
				coluna="Validade";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
		}
		return coluna;
	}
	public Class<?> getColunmClass(int columnIndex){
		if(columnIndex==col_desc){
			return String.class;
		}else if(columnIndex==col_validade){
			return String.class;
		}
		return null;
	}
	public Participa get(int row){
		return valores.get(row);
	}

}
