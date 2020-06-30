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
@Table(name= "roles")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	
	@NotEmpty(message = "Ingrese el nombre del rol*")
	@Column(name="des_rol", nullable = false, length = 20)
	private String desRol;
	
	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getDesRol() {
		return desRol;
	}
	
	public void setDesRol(String desRol) {
		this.desRol = desRol;
	}
	

}
