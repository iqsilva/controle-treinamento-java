package Controller;
import java.util.*;
import Bean.*;
import Model.*;
public class EdicaoController {
	private IEdicaoDao dao;
	public EdicaoController(){
		this.dao=new EdicaoDao();
	}
	public int addEdicao(Edicao edic){
		return dao.save(edic);
	}
	public int altEdicao(Edicao edic){
		return dao.update(edic);
	}
	public int removeEdicao(int cod_edicao){
		return dao.remove(cod_edicao);
	}
	public List<Edicao> findEdicao(int cod_curso){
		return dao.findAll(cod_curso);
	}
	public int AtualizarEd(int cod_curso){
		return dao.removeOld(cod_curso);
	}
}
