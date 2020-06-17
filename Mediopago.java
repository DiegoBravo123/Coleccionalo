package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "medios")
public class Mediopago implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedioPago;
	
	@NotEmpty(message = "Debe ingresar medio de pago*")
	@Column(name="desMedioPago",nullable = false,length = 50)
	private String desMedioPago;
	
	@NotEmpty(message = "Debe ingresar numero de seguridad*")
	@Column(name="seguridad",nullable = false,length = 50)
	private String seguridad;

	public Mediopago() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Mediopago(Integer idMedioPago, String desMedioPago, String seguridad) {
		super();
		this.idMedioPago=idMedioPago;
		this.desMedioPago=desMedioPago;
		this.seguridad=seguridad;
	}
	
	
	
	public Integer getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Integer idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public String getDesMedioPago() {
		return desMedioPago;
	}

	public void setDesMedioPago(String desMedioPago) {
		this.desMedioPago = desMedioPago;
	}

	public String getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}
	
	
}
