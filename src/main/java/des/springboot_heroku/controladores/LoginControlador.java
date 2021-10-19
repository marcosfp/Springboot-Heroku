package des.springboot_heroku.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import des.springboot_heroku.entidades.Email;
import des.springboot_heroku.entidades.Profesor;
import des.springboot_heroku.servicios.ProfesorServicio;

@Controller
public class LoginControlador {

	@Autowired
	ProfesorServicio profesorServicio;

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

	@PostMapping("/crearProfesor")
	public String createUser(@ModelAttribute("profesor") Profesor elProfesor) {
		Profesor profesor = profesorServicio.crearPorfesor(elProfesor);

		return "redirect:/index";
	}

	@GetMapping("/signup")
	public String showForm() {
		return "auth/signup";
	}

	@PostMapping("/signup")
	public String crearUsuario(HttpServletRequest request) {

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String username = request.getParameter("nombreUsuario");
		String direccionemail = request.getParameter("email");
		String password = request.getParameter("password");
		
		Profesor p = new Profesor();
		p.setNombreProfesor(nombre);
		p.setApellidosProfesor(apellidos);
		p.setPassword(password);
		p.setUsername(username);
		
		Email email = new Email();
		email.setDireccionEmail(direccionemail);
		p.anadirEmail(email);
		System.out.println("Profe Profe "+p.toString());
		profesorServicio.crearPorfesor(p);
		

		return "redirect:/";
	}

}