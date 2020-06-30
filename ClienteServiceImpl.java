package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import pe.edu.upc.entity.Cliente;
import pe.edu.upc.repository.IClienteRepository;
import pe.edu.upc.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService, Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	private IClienteRepository cR;
	
	@Override
	@Transactional
	public Integer insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		int rpta = 0; //cR.buscardesCliente(cliente.getDesCliente());
		if (rpta == 0) {
			cR.save(cliente);
		}
		return rpta;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> list() {
		// TODO Auto-generated method stub
		return cR.findAll(Sort.by(Sort.Direction.DESC, "desCliente"));
	}
	
	/*@Override
	public List<Cliente> findByName(String desCliente) {
		// TODO Auto-generated method stub
		return cR.findBydesCliente(desCliente);
	}*/
	
	@Override
	public Optional<Cliente> listarId(Integer idCliente) {
		// TODO Auto-generated method stub
		return cR.findById(idCliente);
	}
	
	@Override
	@Transactional
	public void delete(int idCliente) {
		cR.deleteById(idCliente);
	}
	
	
}
