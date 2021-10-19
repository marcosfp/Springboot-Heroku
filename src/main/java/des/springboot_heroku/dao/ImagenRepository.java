package des.springboot_heroku.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import des.springboot_heroku.entidades.Imagen;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long>{

}
