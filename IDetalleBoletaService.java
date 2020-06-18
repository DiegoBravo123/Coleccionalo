package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.DetalleBoleta;

public interface IDetalleBoletaService {

	public Integer insertar(DetalleBoleta detalleBoleta);

	public List<DetalleBoleta> list();
	
	Optional<DetalleBoleta> listarId(int idDetalleBoleta);

}
