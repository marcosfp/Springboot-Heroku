package des.springboot_heroku.dto;

public class ModuloDTO {

	public ModuloDTO(Long idModulo, String nombreModulo) {
		this.idModulo = idModulo;
		this.nombreModulo = nombreModulo;
	}

	private Long idModulo;

	private String nombreModulo;

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

}