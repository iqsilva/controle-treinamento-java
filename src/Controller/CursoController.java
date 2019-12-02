package Controller;
import java.util.*;
import Bean.*;
import Model.*;
public class CursoController {
	private ICursoDao dao;
	public CursoController(){
		this.dao=new CursoDao();
	}
	public int addCurso(Curso curso){
		return dao.save(curso);
	}
	public int alteraCurso(Curso curso){
		return dao.update(curso);
	}
	public int removerCurso(int cod_curso){
		return dao.remove(cod_curso);
	}
	public List<Curso> findCurso(){
		return dao.findAll();
	}
}
