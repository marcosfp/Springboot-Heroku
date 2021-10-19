package des.springboot_heroku.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import des.springboot_heroku.dao.ModuloRepository;
import des.springboot_heroku.dao.ProfesorRepository;
import des.springboot_heroku.dto.ModuloDTO;
import des.springboot_heroku.entidades.Modulo;
import des.springboot_heroku.entidades.Profesor;

@Transactional
@Service
public class ModuloServicioImpl implements ModuloServicio {

	@Autowired
	ModuloRepository moduloRepository;

	@Autowired
	ProfesorRepository profesorRepository;

	@Override
	public Page<Modulo> listarModulosPaginados(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		
		return moduloRepository.findAll(pageable);
	}

	@Override
	public List<ModuloDTO> listarModulosDTOPorNombre(String nombreModulo) {
		return moduloRepository.findModuloDTOByNombreModulo("%"+nombreModulo+"%");
	}

	@Override
	public Modulo obtenerModulo(long idModulo) {
		return moduloRepository.findById(idModulo).orElse(null);
	}

	@Override
	public Modulo matricularProfesor(long idModulo, long idProfesor) {

		Modulo m = moduloRepository.findById(idModulo).orElse(null);

		for (Profesor p : m.getProfesores()) {
			if (p.getIdProfesor() == idProfesor) {
				return null;
			}
		}
		Profesor profesor = profesorRepository.findById(idProfesor).orElse(null);
		if (profesor == null)
			return null;
		profesor.anadirModulo(m);
		profesorRepository.save(profesor);
		m.addProfesor(profesor);
		
		return m;
	}

	@Override
	public Modulo desmatricularProfesor(long idModulo, long idProfesor) {

		Profesor profesor = profesorRepository.findById(idProfesor).orElse(null);
		if (profesor == null)
			return null;
		Modulo m = moduloRepository.findById(idModulo).orElse(null);

		if (m == null) return null;
		
		profesor.eliminarModulo(m);
		profesorRepository.save(profesor);

		return null;
	}

	@Override
	public Modulo crearModulo(Modulo modulo) {
		return moduloRepository.save(modulo);
	}

	@Override
	public Modulo matricularProfesores(long idModulo, List<Long> idProfesores) {
		Modulo m = moduloRepository.findById(idModulo).orElse(null);
		if (m == null) return null;

		for (Profesor p : m.getProfesores()) {
			if (idProfesores.contains(p.getIdProfesor())) {
				return null;
			}
		}
		for (Long idProfesor : idProfesores) {
			Profesor profesor = profesorRepository.findById(idProfesor).orElse(null);
			if (profesor == null)
				return null;
			profesor.anadirModulo(m);
			profesorRepository.save(profesor);
			m.addProfesor(profesor);
		}

		return m;
	}

	@Override
	public void eliminarModulo(long idModulo) {

		Modulo modulo = moduloRepository.findById(idModulo).orElse(null);

		if (!modulo.getProfesores().isEmpty()) {
			List<Profesor> lProfesores = new ArrayList<Profesor>(modulo.getProfesores());
			for (Profesor p : lProfesores) {
				p.eliminarModulo(modulo);
				profesorRepository.save(p);
			}
		}
		modulo.setProfesores(null);

		moduloRepository.delete(modulo);
	}

	@Override
	public Integer ContarModulos() {
		return moduloRepository.countAll();
	}

	@Override
	public List<Modulo> listarModulos() {
		return null;
//		moduloRepository.findAll();
	}

}