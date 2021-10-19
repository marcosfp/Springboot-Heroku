package des.springboot_heroku.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "EMAIL")
public class Email implements Serializable {

	private static final long serialVersionUID = -7929690108419204859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMAIL")
	private Long idEmail;

	@NotNull
	@Column(name = "DIRECCION_EMAIL")
	private String direccionEmail;

	@ManyToOne
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;

	public Long getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	public String getDireccionEmail() {
		return direccionEmail;
	}

	public void setDireccionEmail(String direccionEmail) {
		this.direccionEmail = direccionEmail;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
	
}