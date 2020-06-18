package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Tienda;
import pe.edu.upc.repository.ITiendaRepository;
import pe.edu.upc.service.ITiendaService;

@Service
public class TiendaServiceImpl implements ITiendaService, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITiendaRepository tR;
	
	
	@Override
	@Transactional
	public Integer insertar(Tienda tienda) {
		// TODO Auto-generated method stub
		int rpta =tR.buscardesTienda(tienda.getDesTienda());
		if (rpta == 0) {
			tR.save(tienda);
		}
		return rpta;
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<Tienda> list() {
		// TODO Auto-generated method stub
		return tR.findAll(Sort.by(Sort.Direction.DESC, "desTienda"));
	}
	
	@Override
	public List<Tienda> findByName(String desTienda) {
		// TODO Auto-generated method stub
		return tR.findBydesTienda(desTienda);
	}
	
	@Override
	public Optional<Tienda> listarId(Integer idTienda) {
		// TODO Auto-generated method stub
		return tR.findById(idTienda);
	}
	
	@Override
	@Transactional
	public void delete(int idTienda) {
		tR.deleteById(idTienda);
		
	}

}
