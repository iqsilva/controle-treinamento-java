package Model;	
import java.util.List;

import Bean.*;
public interface ICursoDao {
	int save(Curso curso);
	int update(Curso curso);
	int remove(int cod_curso);
	List<Curso> findAll();
}
