package des.springboot_heroku.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import des.springboot_heroku.dto.ModuloDTO;
import des.springboot_heroku.entidades.Modulo;

@Repository
public interface ModuloRepository extends PagingAndSortingRepository<Modulo, Long> {

	Page<Modulo> findAll(Pageable pageable);

	List<Modulo> findByNombreModuloContaining(String nombreModulo);

	@Query("SELECT count(*) FROM Modulo")
	Integer countAll();

	@Query("SELECT new des.springboot_heroku.dto.ModuloDTO(u.idModulo, u.nombreModulo) FROM Modulo u WHERE u.nombreModulo LIKE :name")
	List<ModuloDTO> findModuloDTOByNombreModulo(@Param("name") String name);

}
