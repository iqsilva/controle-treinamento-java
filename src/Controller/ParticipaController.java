package Controller;
import java.util.*;
import Bean.*;
import Model.*;
public class ParticipaController {
	private IParticipa dao;
	public ParticipaController(){
		this.dao=new ParticipaDao();
	}
	public int solicitaPart(Participa part,int cod_curso){
		return dao.save(part,cod_curso);
	}
	
	public int aprovaPart(int cod, String status){
		return dao.update(cod, status);
	}
	
	public int reprovaPart(int cod,String status){
		return dao.remove(cod, status);
	}
	
	public List<Participa> achaEspera(String status,int cod_func){
		return dao.findE(status,cod_func);
	}
	
	public List<Participa> achaConcluido(String status,int cod_func){
		return dao.findC(status, cod_func);
	}
	public List<Participa> achaAprovado(String status,int cod_func){
		return dao.findA(status, cod_func);
	}
}