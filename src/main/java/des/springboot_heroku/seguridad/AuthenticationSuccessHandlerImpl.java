package des.springboot_heroku.seguridad;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import des.springboot_heroku.entidades.Profesor;
import des.springboot_heroku.servicios.ProfesorServicio;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
  
	
	@Autowired
	private ProfesorServicio profesorServicio;
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		HttpSession session = request.getSession();
		Profesor authUser = profesorServicio.buscarPorNombreUsuario(userDetails.getUsername());
		session.setAttribute("username", authUser.getUsername());
		session.setAttribute("nombre", authUser.getNombreProfesor());
		session.setAttribute("idUsuario", authUser.getIdProfesor());

		boolean isProfesor = false;
		boolean isAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROL_PROFESOR")) {
				isProfesor = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROL_ADMIN")) {
				isAdmin = true;
				break;
			}
		}

		String targetUrl;
		if (isProfesor) {
			targetUrl = "/profesor/myprofile";
		} else if (isAdmin) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    

}