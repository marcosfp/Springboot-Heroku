package des.springboot_heroku.servicios;

import org.springframework.web.multipart.MultipartFile;

import des.springboot_heroku.entidades.Imagen;

public interface ImagenServicio {

	public int guardarImagen(Imagen img);
	
	public Imagen obtenerImagen(Long id);
	
	public Boolean actualizarImagen(long idProfesor, MultipartFile file);
	
}
