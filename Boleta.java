package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="boletas")
public class Boleta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBoleta;
	
	@NotEmpty(message="Complete la descripcion")
	@Column(name="desBoleta",nullable =false,length=45)
	private String desBoleta;
	
	@Past(message="La fecha debe estar en pasado")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaBoleta", nullable=false, length=45)
	private Date fechaBoleta;
	
	/*@ManyToOne
	@JoinColumn(name="idCliente",nullable=false)
	private Cliente cliente;*/
	
	/*@ManyToOne
	@JoinColumn(name="idMedioPago",nullable=false)
	private Mediopago mediopago;*/
	
	/*@ManyToOne
	@JoinColumn(name="idTienda",nullable=false)
	private Tienda tienda;*/
	
	
	public Boleta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Boleta(Integer idBoleta, String desBoleta, Date fechaBoleta) {
		super();
		this.idBoleta = idBoleta;
		this.desBoleta = desBoleta;
		this.fechaBoleta = fechaBoleta;
		/*this.cliente = cliente;
		this.mediopago = mediopago;
		this.tienda=tienda;*/
	}

	public Integer getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(Integer idBoleta) {
		this.idBoleta = idBoleta;
	}

	public Date getFechaBoleta() {
		return fechaBoleta;
	}

	public void setFechaBoleta(Date fechaBoleta) {
		this.fechaBoleta = fechaBoleta;
	}
/*
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mediopago getMediopago() {
		return mediopago;
	}

	public void setMediopago(Mediopago mediopago) {
		this.mediopago = mediopago;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	} */

	public String getDesBoleta() {
		return desBoleta;
	}

	public void setDesBoleta(String desBoleta) {
		this.desBoleta = desBoleta;
	}
	
	
}
