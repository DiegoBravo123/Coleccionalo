package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Personal;

public interface IPersonalService {
	
	public Integer insertar(Personal personal);

	public List<Personal> list();

	Optional<Personal> listarId(Integer idPersonal);

	List<Personal> findByName(String desPersonal);

	public void delete(int idPersonal);

}
