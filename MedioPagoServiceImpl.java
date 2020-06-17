package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import pe.edu.upc.entity.Mediopago;
import pe.edu.upc.repository.IMediopagoRepository;
import pe.edu.upc.service.IMediopagoService;

@Service
public class MedioPagoServiceImpl implements IMediopagoService, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private IMediopagoRepository mR;
	
	@Override
	@Transactional
	public Integer insertar(Mediopago mediopago) {
		
		int rpta = 0;
		if (rpta == 0) {
			mR.save(mediopago);
		}
		return rpta;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Mediopago> list() {
		// TODO Auto-generated method stub
		return mR.findAll(Sort.by(Sort.Direction.DESC, "desMedioPago"));
	}
	
	@Override
	public List<Mediopago> findByName(String medioPago) {
		// TODO Auto-generated method stub
		return mR.findBydesMediopago(medioPago);
	}
	
	@Override
	public Optional<Mediopago> listarId(Integer idMedioPago) {
		// TODO Auto-generated method stub
		return mR.findById(idMedioPago);
	}
	
	@Override
	@Transactional
	public void delete(int idMedioPago) {
		mR.deleteById(idMedioPago);
		
	}
}
