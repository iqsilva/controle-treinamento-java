package Model;
import java.util.List;
import Bean.Edicao;
public interface IEdicaoDao {
	int save(Edicao edic);
	int update(Edicao edic);
	int remove(int cod_edicao);
	int removeOld(int cod_curso);
	List<Edicao> findAll(int cod_curso);
}
