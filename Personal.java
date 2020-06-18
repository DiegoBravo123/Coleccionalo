package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Personal")
public class Personal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersonal;
	
	@Column(name="desPersonal",nullable=false,length=60)
	private String desPersonal;
	
	@Column(name="fechaNacimientoPersonal",nullable=false,length=60)
	private Date fechaNacimientoPersonal;
	
	@Column(name="emailPersonal",nullable=false,length=60)
	private String emailPersonal;
	
	@Column(name="dniPersonal",nullable=false)
	private int dniPersonal;
	
	@Column(name="usuarioPersonal",nullable=false,length=60)
	private String usuarioPersonal;
	
	@Column(name="contraseñaPersonal",nullable=false,length=60)
	private String contraseñaPersonal;
	
	@ManyToOne
	@JoinColumn(name="idRol",nullable=false)
	private Rol rol;
	
	@OneToOne(mappedBy = "personal",cascade = CascadeType.ALL)
	private Tienda tienda;

	public Personal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personal(Integer idPersonal, String desPersonal, Date fechaNacimientoPersonal, String emailPersonal,
			int dniPersonal, String usuarioPersonal, String contraseñaPersonal, Rol rol) {
		super();
		this.idPersonal = idPersonal;
		this.desPersonal = desPersonal;
		this.fechaNacimientoPersonal = fechaNacimientoPersonal;
		this.emailPersonal = emailPersonal;
		this.dniPersonal = dniPersonal;
		this.usuarioPersonal = usuarioPersonal;
		this.contraseñaPersonal = contraseñaPersonal;
		this.rol = rol;
	}

	public Integer getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getDesPersonal() {
		return desPersonal;
	}

	public void setDesPersonal(String desPersonal) {
		this.desPersonal = desPersonal;
	}

	public Date getFechaNacimientoPersonal() {
		return fechaNacimientoPersonal;
	}

	public void setFechaNacimientoPersonal(Date fechaNacimientoPersonal) {
		this.fechaNacimientoPersonal = fechaNacimientoPersonal;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public int getDniPersonal() {
		return dniPersonal;
	}

	public void setDniPersonal(int dniPersonal) {
		this.dniPersonal = dniPersonal;
	}

	public String getUsuarioPersonal() {
		return usuarioPersonal;
	}

	public void setUsuarioPersonal(String usuarioPersonal) {
		this.usuarioPersonal = usuarioPersonal;
	}

	public String getContraseñaPersonal() {
		return contraseñaPersonal;
	}

	public void setContraseñaPersonal(String contraseñaPersonal) {
		this.contraseñaPersonal = contraseñaPersonal;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPersonal;
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
		Personal other = (Personal) obj;
		if (idPersonal != other.idPersonal)
			return false;
		return true;
	}
	

}
