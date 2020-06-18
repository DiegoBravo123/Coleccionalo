package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DetalleBoleta")
public class DetalleBoleta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalleBoleta;
	
	@Column(name="cantidad", nullable=false, length=10)
	private int cantidad;
	
	@Column(name="desComentario", nullable=false, length=250)
	private String desComentario;
	
	/*@Column(name="totalMonto", nullable=false, length=10)
	private int totalMonto;*/
	
	@ManyToOne
	@JoinColumn(name="idBoleta",nullable=false)
	private Boleta boleta;

	@ManyToOne
	@JoinColumn(name="idProducto",nullable = false)
	private Producto producto;

	public DetalleBoleta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleBoleta(Integer idDetalleBoleta, int cantidad, String desComentario,/*int totalMonto,*/ Boleta boleta, Producto producto) {
		super();
		this.idDetalleBoleta = idDetalleBoleta;
		this.cantidad = cantidad;
		//this.totalMonto = totalMonto;
		this.desComentario = desComentario;
		this.boleta = boleta;
		this.producto = producto;
	}

	public Integer getIdDetalleBoleta() {
		return idDetalleBoleta;
	}

	public void setIdDetalleBoleta(Integer idDetalleBoleta) {
		this.idDetalleBoleta = idDetalleBoleta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/*public int getTotalMonto() {
		return totalMonto;
	}*/

	/*public void setTotalMonto(int totalMonto) {
		this.totalMonto = totalMonto;
	}*/
	

	public Boleta getBoleta() {
		return boleta;
	}

	public String getDesComentario() {
		return desComentario;
	}

	public void setDesComentario(String desComentario) {
		this.desComentario = desComentario;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

}
