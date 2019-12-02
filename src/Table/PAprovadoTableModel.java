package Table;
import java.util.*;
import javax.swing.table.*;
import Bean.*;
@SuppressWarnings("serial")
public class PAprovadoTableModel extends AbstractTableModel{
	private static final int col_desc=0;
	private static final int col_inicio=1;
	private static final int col_fim=2;
	private static final int col_validade=3;
	private List<Participa> valores;
	
	public PAprovadoTableModel(List<Participa> valores){
		this.valores=valores;
	}
	public int getColumnCount() {
		return 4;
	}
	public int getRowCount() {
		return valores.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Participa part=valores.get(rowIndex);
		if(columnIndex==col_desc){
			return part.getDescricao_curso();
		}else if(columnIndex==col_inicio){
			return part.getData_inicio();
		}else if(columnIndex==col_fim){
			return part.getData_fim();
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
			case col_inicio:
				coluna="Data Inicial";
				break;
			case col_fim:
				coluna="Data Final";
				break;
			case col_validade:
				coluna="Validade";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
		}
		return coluna;
	}
	public Participa get(int row){
		return valores.get(row);
	}
}
