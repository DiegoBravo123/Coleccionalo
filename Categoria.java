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
@Table(name = "categorias")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	
	@NotEmpty(message = "Debe ingresar nombre de categoria*")
	@Column(name="desCategoria", nullable=false, length=45)
	private String desCategoria;
	
	
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categoria(Integer idCategoria, String desCategoria) {
		super();
		this.idCategoria=idCategoria;
		this.desCategoria=desCategoria;
	}
		
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDesCategoria() {
		return desCategoria;
	}

	public void setDesCategoria(String desCategoria) {
		this.desCategoria = desCategoria;
	}
	
	
}
