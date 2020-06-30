package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Personal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
