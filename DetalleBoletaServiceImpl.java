package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.DetalleBoleta;
import pe.edu.upc.repository.IDetalleBoletaRepository;
import pe.edu.upc.service.IDetalleBoletaService;

@Service
public class DetalleBoletaServiceImpl implements IDetalleBoletaService, Serializable{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDetalleBoletaRepository dbR;
	
	@Override
	public Integer insertar(DetalleBoleta detalleBoleta) {
		int rpta = dbR.buscardesComentario(detalleBoleta.getDesComentario());
		if (rpta == 0) {
			dbR.save(detalleBoleta);
		}
		return rpta;
		
	}

	@Override
	public List<DetalleBoleta> list() {
		// TODO Auto-generated method stub
		return dbR.findAll();//Sort.by(Sort.Direction.ASC, "desBoleta"));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DetalleBoleta> listarId(int idDetalleBoleta) {
		// TODO Auto-generated method stub
		return dbR.findById(idDetalleBoleta);
	}

}
