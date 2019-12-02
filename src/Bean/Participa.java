package Bean;

public class Participa {
	int cod_edicao;
	int cod_func;
	int cod_participa;
	int cod_curso;
	String status;
	String nome_func,descricao_curso,data_inicio,validade;
	String data_fim;
	public String getData_fim() {
		return data_fim;
	}
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	public String getNome_func() {
		return nome_func;
	}
	public void setNome_func(String nome_func) {
		this.nome_func = nome_func;
	}
	public String getDescricao_curso() {
		return descricao_curso;
	}
	public void setDescricao_curso(String descricao_curso) {
		this.descricao_curso = descricao_curso;
	}
	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public int getCod_edicao() {
		return cod_edicao;
	}
	public void setCod_edicao(int cod_edicao) {
		this.cod_edicao = cod_edicao;
	}
	public int getCod_func() {
		return cod_func;
	}
	public void setCod_func(int cod_func) {
		this.cod_func = cod_func;
	}
	public int getCod_participa() {
		return cod_participa;
	}
	public void setCod_participa(int cod_participa) {
		this.cod_participa = cod_participa;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCod_curso() {
		return cod_curso;
	}
	public void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}
	
}
