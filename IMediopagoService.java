package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.entity.Mediopago;

public interface IMediopagoService {
	public Integer insertar(Mediopago mediopago);
	
	public List<Mediopago> list();
	
	Optional<Mediopago> listarId(Integer idMedioPago);

	List<Mediopago> findByName(String desMedioPago);
	
	public void delete (int idMedioPago);
}
