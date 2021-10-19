package des.springboot_heroku.servicios;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import des.springboot_heroku.dao.ProfesorRepository;
import des.springboot_heroku.entidades.Profesor;
import des.springboot_heroku.entidades.Rol;

@Transactional
@Service
public class CustomUserDetailsService implements  UserDetailsService {

	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Override
	@Transactional()
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

		Profesor profesor = profesorRepository.findByUsername(nombre);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Rol rol : profesor.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}

		return new org.springframework.security.core.userdetails.User(profesor.getUsername(), profesor.getPassword(),
				grantedAuthorities);
	}
}