package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.entity.Cliente;

public interface IClienteService {
	
	public Integer insertar(Cliente cliente);
	
	public List<Cliente> list();

	Optional<Cliente> listarId(Integer idCliente);

	//List<Cliente> findByName(String desCliente);
	
	public void delete (int idCliente);
}
