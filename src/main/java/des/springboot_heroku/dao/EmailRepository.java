package des.springboot_heroku.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import des.springboot_heroku.entidades.Email;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<Email, Long> {

}
