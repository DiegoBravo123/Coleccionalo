package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.repository.IBoletaRepository;
import pe.edu.upc.service.IBoletaService;

@Service
public class BoletaServiceImpl implements IBoletaService{
	@Autowired
	private IBoletaRepository bR;
	
	
	@Override
	@Transactional
	public Integer insert(Boleta boleta) {
		int rpta = bR.buscardesBoleta(boleta.getDesBoleta());
		if (rpta == 0) {
			bR.save(boleta);
		}
		return rpta;
	}
	
	@Override
	@Transactional
	public void delete(int idBoleta) {
		bR.deleteById(idBoleta);	
	}
	
	@Override
	public List<Boleta> list() {
		// TODO Auto-generated method stub
		return bR.findAll(Sort.by(Sort.Direction.ASC, "desBoleta"));
	}
	
	@Override
	public Boleta findByIdBoleta(int idBoleta) {
		// TODO Auto-generated method stub
		return bR.findByIdBoleta(idBoleta);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Boleta> listarId(int idBoleta) {
		return bR.findById(idBoleta);
	}
}
