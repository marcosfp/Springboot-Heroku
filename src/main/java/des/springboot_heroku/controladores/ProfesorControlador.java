package des.springboot_heroku.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_heroku.entidades.Email;
import des.springboot_heroku.entidades.Imagen;
import des.springboot_heroku.entidades.Modulo;
import des.springboot_heroku.entidades.Profesor;
import des.springboot_heroku.servicios.ModuloServicio;
import des.springboot_heroku.servicios.ProfesorServicio;

@Controller
@RequestMapping(value = "/profesor")
public class ProfesorControlador {

	@Autowired
	ProfesorServicio profesorService;

	@Autowired
	ModuloServicio moduloServicio;

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ModelAndView listarPorfesores() {

		ModelAndView mav = new ModelAndView();

		Iterable<Profesor> lProfesor = profesorService.listarProfesores();

		mav.addObject("profesores", lProfesor);
		mav.setViewName("profesor/lista");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myprofile")
	public String perfilPersonal(HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session != null && session.getAttribute("idUsuario") != null)
			return "redirect:/profesor/perfil/" + session.getAttribute("idUsuario");
		else
			return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/perfil/{id}")
	public ModelAndView perfilProfesor(@PathVariable("id") long idProfesor, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		Profesor profesor = profesorService.obtenerProfesor(idProfesor);
		Boolean propietario = false;

		if (request.getSession().getAttribute("idUsuario") != null) {
			long idSession = (long) request.getSession().getAttribute("idUsuario");
			propietario = idSession == idProfesor;
		}
		Imagen img = null;
		if (!profesor.getImagen().isEmpty()) {
			for (Imagen i : profesor.getImagen()) {
				img = i;
				break;
			}

		}
		mav.addObject("imagen", img);
		mav.addObject("propietario", propietario);
		mav.addObject("profesor", profesor);
		mav.setViewName("profesor/perfil");
		return mav;
	}

	@GetMapping("/perfil/actualizar/{id}")
	public ModelAndView mostrarActualizarPerfilProfesor(@PathVariable("id") long idProfesor,
			HttpServletRequest request) {

		Profesor profesor = profesorService.obtenerProfesor(idProfesor);
		List<Modulo> lModulos = moduloServicio.listarModulos();
		ModelAndView mav = new ModelAndView();
		mav.addObject("profesor", profesor);
		mav.addObject("modulos", lModulos);
		mav.setViewName("profesor/actualizar");
		return mav;
	}

	@PostMapping("/perfil/actualizar/{id}")
	public String actualizarPerfilProfesor(@PathVariable("id") long idProfesor, Profesor profesorFormulario,
			BindingResult bindingResult, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		long idUsuarioSession = (long) request.getSession().getAttribute("idUsuario");
		if (idUsuarioSession != idProfesor) {
			return "redirect:/index";
		}

		if (bindingResult.hasErrors()) {
			return "profesor_perfil_actualizar";
		}

		// Con este metodo obtenemos al porfesor, un objeto Persistent
		Profesor p = profesorService.obtenerProfesor(idProfesor);

		// Modificamos el objeto pasa a un estado detached
		p.setUsername(profesorFormulario.getUsername());
		p.setNombreProfesor(profesorFormulario.getNombreProfesor());
		p.setApellidosProfesor(profesorFormulario.getApellidosProfesor());

		// guardamos los cambios el oibjeto vuevle a ser Persistent
		Profesor profesormodificado = profesorService.modificarProfesor(p);

		return "redirect:/profesor/perfil/" + idUsuarioSession;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/anadir_email/{id}")
	public String anadirEmail(@PathVariable("id") long idProfesor, HttpServletRequest request) {

		String direccion_email = request.getParameter("nuevoemail");

		Email email = new Email();
		email.setDireccionEmail(direccion_email);

		Profesor profesor = profesorService.anadirEmail(idProfesor, email);

		return "redirect:/profesor/perfil/" + idProfesor;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/eliminar_email/{idProfesor}/{idEmail}")
	public String eliminarEmail(@PathVariable("idEmail") long idEmail, @PathVariable("idProfesor") long idProfesor) {


		profesorService.eliminarEmail(idProfesor, idEmail);

		return "redirect:/profesor/perfil/" + idProfesor;
	}

	@GetMapping("/crear")
	public String showForm() {
		return "profesor/crear";
	}

	@PostMapping("/crear")
	public String crearUsuario(HttpServletRequest request) {

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String username = request.getParameter("nombreUsuario");
		String direccionemail = request.getParameter("email");

		Profesor p = new Profesor();
		p.setNombreProfesor(nombre);
		p.setApellidosProfesor(apellidos);
		p.setUsername(username);

		Email email = new Email();
		email.setDireccionEmail(direccionemail);
		p.anadirEmail(email);
		Profesor prof = profesorService.crearPorfesor(p);

		return "redirect:/profesor/lista";
	}

	
	
	@RequestMapping(value = "/disponibleUsername/{username}", method = RequestMethod.POST)
	@ResponseBody
	 public String checkUsernameAvailability(@PathVariable("username") String username) {

		if (profesorService.nombreDeUsuarioLibre(username)) {		
			return "true";
		}else {
			return "false";
		}
	}
	
}