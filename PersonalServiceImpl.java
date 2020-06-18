package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Personal;
import pe.edu.upc.repository.IPersonalRepository;
import pe.edu.upc.service.IPersonalService;

@Service
public class PersonalServiceImpl implements IPersonalService, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private IPersonalRepository peR;
	
	
	@Override
	@Transactional
	public Integer insertar(Personal personal) {
		// TODO Auto-generated method stub
		int rpta =peR.buscardesPersonal(personal.getDesPersonal());
		if (rpta == 0) {
			peR.save(personal);
		}
		return rpta;
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<Personal> list() {
		// TODO Auto-generated method stub
		return peR.findAll(Sort.by(Sort.Direction.DESC, "desPersonal"));
	}
	
	@Override
	public List<Personal> findByName(String desPersonal) {
		// TODO Auto-generated method stub
		return peR.findBydesPersonal(desPersonal);
	}
	
	@Override
	public Optional<Personal> listarId(Integer idPersonal) {
		// TODO Auto-generated method stub
		return peR.findById(idPersonal);
	}
	
	@Override
	@Transactional
	public void delete(int idPersonal) {
		peR.deleteById(idPersonal);
		
	}


}
