package des.springboot_heroku.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import des.springboot_heroku.entidades.Profesor;

@Repository
public interface ProfesorRepository extends PagingAndSortingRepository<Profesor, Long> {

	Profesor findByUsername(String username);
	
	Boolean existsByUsername(String Username);

	@Query("select c from Profesor c where c.idProfesor NOT IN (:idProfesores)")
	List<Profesor> BuscarProfesoresQueNoImparten(@Param("idProfesores")List<Long> idProfesores);

	

}
