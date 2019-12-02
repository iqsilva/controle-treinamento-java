package Bean;
public class Curso {
	private int cod_curso;
	private char status_curso;
	private String desc_curso;
	private int carga_horaria;
	private int vigencia;
	public int getCod_curso() {
		return cod_curso;
	}
	public void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}
	public int getStatus_curso() {
		return status_curso;
	}
	public void setStatus_curso(char status_curso) {
		this.status_curso = status_curso;
	}
	public String getDesc_curso() {
		return desc_curso;
	}
	public void setDesc_curso(String desc_curso) {
		this.desc_curso = desc_curso;
	}
	public int getCarga_horaria() {
		return carga_horaria;
	}
	public void setCarga_horaria(int carga_horaria) {
		this.carga_horaria = carga_horaria;
	}
	public int getVigencia() {
		return vigencia;
	}
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
}
