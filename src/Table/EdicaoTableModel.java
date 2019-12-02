package Table;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Bean.Edicao;
@SuppressWarnings("serial")
public class EdicaoTableModel extends AbstractTableModel{
	private static final int col_cod_curso=0;
	private static final int col_data_inicio=1;
	private static final int col_data_fim=2;
	private static final int col_validade=3;
	private List<Edicao> valores;
	
	public EdicaoTableModel(List<Edicao> valores){
		this.valores=valores;
	}
	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return valores.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Edicao edic = valores.get(rowIndex);
		if(columnIndex==col_cod_curso){
			return edic.getCod_Curso();
		}else if(columnIndex==col_data_inicio){
			return edic.getData_Inicio();
		}else if(columnIndex==col_data_fim){
			return edic.getData_Fim();
		}else if(columnIndex==col_validade){
			return edic.getValidade();
		}
		return null;
	}
	public String getColumnName(int colunm){
		String coluna="";
		switch(colunm){
			case col_cod_curso:
				coluna="Código";
				break;
			case col_data_inicio:
				coluna="Início";
				break;
			case col_data_fim:
				coluna="Término";
				break;
			case col_validade:
				coluna="Validade";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
			}
		return coluna;	
	}
	public Edicao get(int row){
		return valores.get(row);
		
	}

}
