package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Evento;
import pe.edu.upc.repository.IEventoRepository;
import pe.edu.upc.service.IEventoService;

@Service
public class EventoServiceImpl implements IEventoService{

	@Autowired
	private IEventoRepository eR;
	
	@Override
	public Integer insertar(Evento evento) {
		int rpta = 0;
		if (rpta == 0) {
		eR.save(evento);
		}
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> list() {
		// TODO Auto-generated method stub
		return eR.findAll();
	}

}
