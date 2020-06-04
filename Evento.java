package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Evento")
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEvento;
	
	@Column(name="desEvento",nullable=false, length=45)
	private String desEvento;
	
	@Column(name="detalleEvento",nullable=false, length=100)
	private String detalleEvento;
	
	@Column(name="fechaEvento",nullable=false, length=45)
	private Date fechaEvento;
	
	@Column(name="precioEvento",nullable=false)
	private int precioEvento;

	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evento(Integer idEvento, String desEvento, String detalleEvento, Date fechaEvento, int precioEvento) {
		super();
		this.idEvento = idEvento;
		this.desEvento = desEvento;
		this.detalleEvento = detalleEvento;
		this.fechaEvento = fechaEvento;
		this.precioEvento = precioEvento;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getDesEvento() {
		return desEvento;
	}

	public void setDesEvento(String desEvento) {
		this.desEvento = desEvento;
	}

	public String getDetalleEvento() {
		return detalleEvento;
	}

	public void setDetalleEvento(String detalleEvento) {
		this.detalleEvento = detalleEvento;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public int getPrecioEvento() {
		return precioEvento;
	}

	public void setPrecioEvento(int precioEvento) {
		this.precioEvento = precioEvento;
	}
	
	

}
