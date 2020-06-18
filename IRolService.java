package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Rol;

public interface IRolService {

	public Integer insertar(Rol rol);

	public List<Rol> list();

	Optional<Rol> listarId(Integer idRol);

	List<Rol> findByName(String desRol);

	public void delete(int idRol);

}
