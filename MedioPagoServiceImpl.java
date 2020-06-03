package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Mediopago;
import pe.edu.upc.repository.IMediopagoRepository;
import pe.edu.upc.service.IMediopagoService;

public class MedioPagoServiceImpl implements IMediopagoService{
	
	@Autowired
	private IMediopagoRepository mR;
	
	@Override
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
		return mR.findAll();
	}
}
