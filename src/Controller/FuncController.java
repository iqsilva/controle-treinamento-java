package Controller;
import java.util.*;

import Bean.*;
import Model.*;
public class FuncController {
	private IFuncDao dao;
	public FuncController(){
		this.dao=new FuncDao();
	}
	public int addFunc(Funcionario func){
		return dao.save(func);
	}
	public int alteraFunc(Funcionario func){
		return dao.update(func);
	}
	public int removeFunc(int cod_user){
		return dao.remove(cod_user);
	}
	public List<Funcionario> findFunc(){
		return dao.findAll();
	}
	public int login(String nome_user,String senha_user){
		return dao.login(nome_user, senha_user);
	}
	public List<Funcionario> sessao(String nome_user,String senha_user){
		return dao.session(nome_user, senha_user);
	}
	public int alteraFunc2(Funcionario func){
		return dao.update2(func);
	}
}
