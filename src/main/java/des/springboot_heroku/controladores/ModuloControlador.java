package des.springboot_heroku.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_heroku.dto.ModuloDTO;
import des.springboot_heroku.entidades.Modulo;
import des.springboot_heroku.entidades.Profesor;
import des.springboot_heroku.servicios.ModuloServicio;
import des.springboot_heroku.servicios.ProfesorServicio;

@Controller
@RequestMapping(value = "/modulo")
public class ModuloControlador {

	@Autowired
	ModuloServicio moduloServicio;

	@Autowired
	ProfesorServicio profesorServicio;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView perfilModulo(@PathVariable("id") long idModulo) {

		ModelAndView mav = new ModelAndView();

		Modulo modulos = moduloServicio.obtenerModulo(idModulo);
		List<Profesor> lProfesores = profesorServicio.listarPorfesoresQueNoImparten(idModulo);

		mav.addObject("profesor", new Profesor());
		mav.addObject("profesores", lProfesores);
		mav.addObject("modulo", modulos);
		mav.setViewName("modulo/perfil");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "borrar/{id}")
	public String borrarModulo(@PathVariable("id") long idModulo) {

		moduloServicio.eliminarModulo(idModulo);

		return "redirect:/modulo/lista";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ModelAndView listarModulos(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		ModelAndView mav = new ModelAndView();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Page<Modulo> modulos = moduloServicio.listarModulosPaginados(currentPage - 1, pageSize);

		Double totalPages =  Math.ceil(moduloServicio.ContarModulos()/pageSize);
		if ((moduloServicio.ContarModulos()%pageSize) >0) {
			totalPages++;
		}
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages.intValue()).boxed().collect(Collectors.toList());
			mav.addObject("pageNumbers", pageNumbers);
		}

		mav.addObject("modulos", modulos);
		mav.addObject("modulo_nuevo", new Modulo());
		mav.setViewName("modulo/lista");
		return mav;
	}

	@PostMapping("/crear")
	public String actualizarPerfilProfesor(Model model, @ModelAttribute("modulo_nuevo") Modulo modulo) {

		modulo.setProfesores(null);
		moduloServicio.crearModulo(modulo);

		return "redirect:/modulo/lista";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/desmatricularProfesor/{idProfesor}/{idModulo}")
	public String desmatricularProfesor(@PathVariable("idModulo") long idModulo,
			@PathVariable("idProfesor") long idProfesor) {

		Modulo modulo = moduloServicio.desmatricularProfesor(idModulo, idProfesor);

		return "redirect:/modulo/" + idModulo;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/matricular/{idModulo}")
	public String matricularPorfesore(@PathVariable("idModulo") long idModulo, HttpServletRequest request,
			@RequestParam("profesoresseleccionados") List<Long> profesores) {

		ModelAndView mav = new ModelAndView();
		moduloServicio.matricularProfesores(idModulo, profesores);

		return "redirect:/modulo/" + idModulo;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public @ResponseBody List<ModuloDTO> buscarModulo(@RequestParam("term") String nombre) {
		List<ModuloDTO> tagList = moduloServicio.listarModulosDTOPorNombre(nombre);
		return tagList;
	}

}
