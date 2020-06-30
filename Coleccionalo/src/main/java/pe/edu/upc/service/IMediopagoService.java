package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Mediopago;

public interface IMediopagoService {
	public Integer insertar(Mediopago mediopago);
	public List<Mediopago> list();
}
