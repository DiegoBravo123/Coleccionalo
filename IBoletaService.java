package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Boleta;


public interface IBoletaService {
	
	public Integer insert(Boleta boleta);
	
	public void delete(int idBoleta);
	
	List<Boleta> list();
	
	Boleta findByIdBoleta(int idBoleta);
	
	Optional<Boleta> listarId(int idBoleta);
}
