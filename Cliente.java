package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	
	@NotEmpty(message = "Debe ingresar nombre del cliente*")
	@Column(name="desCliente",nullable = false,length = 50)
	private String desCliente;
	
	@Past(message="La fecha debe estar en pasado")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaCliente", nullable=false, length=45)
	private Date fechaCliente;
	
	@NotEmpty(message = "Debe ingresar correo del cliente*")
	@Column(name="emailCliente",nullable = false,length = 50)
	private String emailCliente;
	
	@Column(name="dniCliente",nullable = false,length = 50)
	private int dniCliente;	
	
	@NotEmpty(message = "Debe ingresar usuario del cliente*")
	@Column(name="usuarioCliente",nullable = false,length = 50)
	private String usuarioCliente;
	
	@NotEmpty(message = "Debe ingresar usuario del cliente*")
	@Column(name="contraseñaCliente",nullable = false,length = 50)
	private String contraseñaCliente;
	
	
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(Integer idCliente, String desCliente, Date fechaCliente, String emailCliente, int dniCliente,
			String usuarioCliente,String contraseñaCliente ) {
		super();
		this.idCliente=idCliente;
		this.desCliente=desCliente;
		this.fechaCliente=fechaCliente;
		this.emailCliente=emailCliente;
		this.dniCliente=dniCliente;
		this.usuarioCliente=usuarioCliente;
		this.contraseñaCliente=contraseñaCliente;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDesCliente() {
		return desCliente;
	}

	public void setDesCliente(String desCliente) {
		this.desCliente = desCliente;
	}

	public Date getFechaCliente() {
		return fechaCliente;
	}

	public void setFechaCliente(Date fechaCliente) {
		this.fechaCliente = fechaCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(String usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public String getContraseñaCliente() {
		return contraseñaCliente;
	}

	public void setContraseñaCliente(String contraseñaCliente) {
		this.contraseñaCliente = contraseñaCliente;
	}
	
	
}
