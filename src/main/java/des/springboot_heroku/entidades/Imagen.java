package des.springboot_heroku.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGEN")
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMAGEN")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Lob
	@Column(name = "IMAGEN")
	private byte[] imagen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;

	public Imagen() {
		super();
	}

	public Imagen(String name, byte[] image) {
		super();
		this.nombre = name;
		this.imagen = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
			return 2020;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        
		
		return id != null && id.equals(((Imagen) obj).id);

	}

}