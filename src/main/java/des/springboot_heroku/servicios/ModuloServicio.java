package des.springboot_heroku.servicios;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;

import des.springboot_heroku.dto.ModuloDTO;
import des.springboot_heroku.entidades.Modulo;

public interface ModuloServicio {
	
	public Modulo crearModulo(Modulo modulo);

	public Modulo obtenerModulo(long idModulo);
	
	public void eliminarModulo(long idModulo);

	public List<Modulo> listarModulos();
	
	public Page<Modulo> listarModulosPaginados(int page, int size);
	
	public Integer ContarModulos();
	
	public List<ModuloDTO> listarModulosDTOPorNombre(String nombreModulo);
	
	public Modulo matricularProfesor(long idModulo, long idProfesor);

	public Modulo matricularProfesores(long idModulo, List<Long>idProfesores);
	
	public Modulo desmatricularProfesor(long idModulo, long idProfesor);
}
