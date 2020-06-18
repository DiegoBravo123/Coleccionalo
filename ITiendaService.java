package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Tienda;

public interface ITiendaService {
	
	public Integer insertar(Tienda tienda);

	public List<Tienda> list();

	Optional<Tienda> listarId(Integer idTienda);

	List<Tienda> findByName(String desTienda);

	public void delete(int idTienda);


}
