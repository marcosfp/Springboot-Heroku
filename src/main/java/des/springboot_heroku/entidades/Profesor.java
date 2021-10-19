package des.springboot_heroku.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROFESOR")
public class Profesor implements Serializable {

	private static final long serialVersionUID = -8668594760203621162L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROFESOR")
	private Long idProfesor;

	@Column(name = "NICKNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NOMBRE")
	private String nombreProfesor;

	@Column(name = "APELLIDOS")
	private String apellidosProfesor;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "profesor", orphanRemoval = true)
	private Set<Imagen> imagen = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Email> emails = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "PROFESOR_MODULO", joinColumns = @JoinColumn(name = "ID_PROFESOR"), inverseJoinColumns = @JoinColumn(name = "ID_MODULO"))
	private Set<Modulo> modulos = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "PROFESOR_ROL", joinColumns = @JoinColumn(name = "ID_PROFESOR"), inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> roles = new HashSet<>();

	// getters y setters
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public Set<Imagen> getImagen() {
		return imagen;
	}

	public void setImagen(Set<Imagen> imagen) {
		this.imagen = imagen;
	}

	public boolean anadirEmail(Email email) {
		email.setProfesor(this);
		return getEmails().add(email);
	}

	public void eliminarEmail(Email email) {
		email.setProfesor(null);
		this.emails.remove(email);
		
	}

	// métodos para facilitar la matriculación y desmatriculación de módulos
	public boolean anadirModulo(Modulo modulo) {
		modulo.addProfesor(this);
		return getModulos().add(modulo);
	}

	public void eliminarModulo(Modulo modulo) {
		this.modulos.remove(modulo);
		modulo.getProfesores().remove(this);
	}

	
	public boolean anadirRol(Rol rol) {
	    rol.anadirProfesor(this);
		return getRoles().add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
		rol.getProfesores().remove(this);
	}
	
	
	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public String getApellidosProfesor() {
		return apellidosProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public void setApellidosProfesor(String apellidosProfesor) {
		this.apellidosProfesor = apellidosProfesor;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addImagen(Imagen img) {
		this.imagen.add(img);
		img.setProfesor(this);
	}

	public void removeImagen(Imagen img) {
		img.setProfesor(null);
		this.imagen.remove(img);
	}

	public void removeImagenes() {
		Iterator<Imagen> iterator = this.imagen.iterator();
		while (iterator.hasNext()) {
			Imagen img = iterator.next();
			img.setProfesor(null);
			iterator.remove();
		}
	}

	@Override
	public String toString() {
		return "Profesor [idProfesor=" + idProfesor + ", username=" + username + ", password=" + password
				+ ", nombreProfesor=" + nombreProfesor + ", apellidosProfesor=" + apellidosProfesor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidosProfesor == null) ? 0 : apellidosProfesor.hashCode());
		result = prime * result + ((idProfesor == null) ? 0 : idProfesor.hashCode());
		result = prime * result + ((nombreProfesor == null) ? 0 : nombreProfesor.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (apellidosProfesor == null) {
			if (other.apellidosProfesor != null)
				return false;
		} else if (!apellidosProfesor.equals(other.apellidosProfesor))
			return false;
		if (idProfesor == null) {
			if (other.idProfesor != null)
				return false;
		} else if (!idProfesor.equals(other.idProfesor))
			return false;
		if (nombreProfesor == null) {
			if (other.nombreProfesor != null)
				return false;
		} else if (!nombreProfesor.equals(other.nombreProfesor))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}