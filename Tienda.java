package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tienda")
public class Tienda implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTienda;
	
	@Column(name = "desTienda",nullable=false, length= 60)
	private String desTienda;
	
	@Column(name = "rucTienda",nullable=false)
	private int rucTienda;
	
	@Column(name = "telefonoTienda",nullable=false)
	private int telefonoTienda;
	
	@OneToOne
	@JoinColumn(name="idPersonal",nullable=false)
	private Personal personal;

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tienda(int idTienda, String desTienda, int rucTienda, int telefonoTienda, Personal personal) {
		super();
		this.idTienda = idTienda;
		this.desTienda = desTienda;
		this.rucTienda = rucTienda;
		this.telefonoTienda = telefonoTienda;
		this.personal = personal;
	}

	public int getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public String getDesTienda() {
		return desTienda;
	}

	public void setDesTienda(String desTienda) {
		this.desTienda = desTienda;
	}

	public int getRucTienda() {
		return rucTienda;
	}

	public void setRucTienda(int rucTienda) {
		this.rucTienda = rucTienda;
	}

	public int getTelefonoTienda() {
		return telefonoTienda;
	}

	public void setTelefonoTienda(int telefonoTienda) {
		this.telefonoTienda = telefonoTienda;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTienda;
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
		Tienda other = (Tienda) obj;
		if (idTienda != other.idTienda)
			return false;
		return true;
	}
	

}
