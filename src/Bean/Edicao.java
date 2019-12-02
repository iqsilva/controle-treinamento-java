package Bean;
public class Edicao {
	private int Cod_Curso,Cod_Edicao;
	private String Data_Inicio,Data_Fim,Validade;
	public int getCod_Edicao() {
		return Cod_Edicao;
	}
	public void setCod_Edicao(int cod_Edicao) {
		Cod_Edicao = cod_Edicao;
	}
	public int getCod_Curso() {
		return Cod_Curso;
	}
	public void setCod_Curso(int cod_Curso) {
		Cod_Curso = cod_Curso;
	}

	public String getData_Inicio() {
		return Data_Inicio;
	}

	public void setData_Inicio(String data_Inicio) {
		Data_Inicio = data_Inicio;
	}

	public String getData_Fim() {
		return Data_Fim;
	}

	public void setData_Fim(String data_Fim) {
		Data_Fim = data_Fim;
	}

	public String getValidade() {
		return Validade;
	}

	public void setValidade(String validade) {
		Validade = validade;
	}
	
}	
