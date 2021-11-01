package des.springboot_heroku.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_heroku.entidades.Imagen;
import des.springboot_heroku.entidades.Profesor;
import des.springboot_heroku.servicios.ImagenServicio;
import des.springboot_heroku.servicios.ProfesorServicio;

@Controller
@RequestMapping(value = "/imagenes")
public class ImagenControlador {

	@Autowired
	private ImagenServicio imgServicio;

	@Autowired
	ProfesorServicio profesorService;

	@GetMapping("/cargar/{idProfesor}")
	public ModelAndView actualizarFotoPerfil(HttpServletRequest request, @PathVariable("idProfesor") long idProfesor) {

		ModelAndView mav = new ModelAndView();

		Profesor profesor = profesorService.obtenerProfesor(idProfesor);
		Imagen img = null;
		if (!profesor.getImagen().isEmpty()) {
			for (Imagen i : profesor.getImagen()) {
				img = i;
				break;
			}
		}
		mav.addObject("imagen", img);
		mav.addObject("profesor", profesor);
		mav.setViewName("imagen/imagen_subir");
		return mav;
	}

	@PostMapping("/cargar/{idProfesor}")
	public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			@PathVariable("idProfesor") long idProfesor) {
		try {
			byte[] image = file.getBytes();
			Imagen img = new Imagen("foto", image);
			Boolean saveImage = imgServicio.actualizarImagen(idProfesor, file);
			if (saveImage) {
				return "redirect:/profesor/perfil/" + idProfesor;
			} else {
				return "redirect:/imagenes/cargar/" + idProfesor;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/imagenes/cargar/" + idProfesor;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getImageAsResponseEntity(@PathVariable String id) {

		try {
			Imagen imagesObj = imgServicio.obtenerImagen(Long.parseLong(id));
			byte[] media = imagesObj.getImagen();
			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());

			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
			return responseEntity;

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
