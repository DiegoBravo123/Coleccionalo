package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Rol;
import pe.edu.upc.repository.IRolRepository;
import pe.edu.upc.service.IRolService;

@Service
public class RolServiceImpl implements IRolService, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private IRolRepository rR;
	
	
	@Override
	@Transactional
	public Integer insertar(Rol rol) {
		// TODO Auto-generated method stub
		int rpta = rR.buscardesRol(rol.getDesRol());
		if (rpta == 0) {
			rR.save(rol);
		}
		return rpta;
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> list() {
		// TODO Auto-generated method stub
		return rR.findAll(Sort.by(Sort.Direction.DESC, "desRol"));
	}
	
	@Override
	public List<Rol> findByName(String desCategoria) {
		// TODO Auto-generated method stub
		return rR.findBydesRol(desCategoria);
	}
	
	@Override
	public Optional<Rol> listarId(Integer idRol) {
		// TODO Auto-generated method stub
		return rR.findById(idRol);
	}
	
	@Override
	@Transactional
	public void delete(int idRol) {
		rR.deleteById(idRol);
		
	}

}
