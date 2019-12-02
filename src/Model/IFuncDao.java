package Model;
import java.util.List;
import Bean.Funcionario;
public interface IFuncDao {
	int save(Funcionario func);
	int update(Funcionario func);
	int remove(int cod_user);
	int login(String nome_user, String senha_user);
	int update2(Funcionario func);
	List<Funcionario> findAll();
	List<Funcionario> session(String nome_user,String senha_user);
}
