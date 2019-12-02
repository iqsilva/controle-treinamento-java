package Model;
import java.util.List;
import Bean.Participa;
public interface IParticipa {
	int save(Participa part,int cod_curso);
	int update(int cod,String status);
	int remove(int cod,String status);
	List<Participa> findE(String status,int cod_func);
	List<Participa> findC(String status,int cod_func);
	List<Participa> findA(String status,int cod_func);
}

