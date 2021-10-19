package des.springboot_heroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringbootHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateApplication.class, args);
	}

}
