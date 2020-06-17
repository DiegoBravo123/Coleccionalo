package pe.edu.upc.service;

import java.util.List;
import pe.edu.upc.entity.Producto;

public interface IProductoService {

	public Integer insert(Producto producto);
	
	public void delete(long idProducto);
	
	List<Producto> list();
}
