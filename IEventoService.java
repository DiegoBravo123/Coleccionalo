package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Evento;

public interface IEventoService {
	
	public Integer insertar(Evento evento);
	
	public List<Evento> list();

}
