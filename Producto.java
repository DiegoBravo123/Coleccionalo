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
@Table(name="productos")
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	
	@NotEmpty(message="Complete la descripcion")
	@Column(name="desProducto",nullable =false,length=45,unique=true)
	private String desProducto;
	
	
	@Column(name="precioProducto", nullable=false)
	private int precioProducto;
	
	@Past(message="La fecha debe estar en pasado")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaProducto", nullable=false, length=45)
	private Date fechaProducto;
	
	@ManyToOne
	@JoinColumn(name="idCategoria",nullable=false)
	private Categoria categoria;
	
	@NotEmpty(message="Complete la procedencia")
	@Column(name="procedenciaProducto",nullable =false,length=45,unique=true)
	private String procedenciaProducto;
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Producto(long idProducto, String desProducto, int precioProducto, Date fechaProducto ,Categoria categoria,
			String procedenciaProducto) {
		super();
		this.idProducto = idProducto;
		this.desProducto = desProducto;
		this.precioProducto = precioProducto;
		this.fechaProducto = fechaProducto;
		this.categoria=categoria;
		this.procedenciaProducto=procedenciaProducto;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDesProducto() {
		return desProducto;
	}

	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

	public int getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}

	public Date getFechaProducto() {
		return fechaProducto;
	}

	public void setFechaProducto(Date fechaProducto) {
		this.fechaProducto = fechaProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getProcedenciaProducto() {
		return procedenciaProducto;
	}

	public void setProcedenciaProducto(String procedenciaProducto) {
		this.procedenciaProducto = procedenciaProducto;
	}
	
	
}
