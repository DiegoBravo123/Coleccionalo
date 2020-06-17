package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import pe.edu.upc.entity.Producto;
import pe.edu.upc.repository.IProductoRepository;
import pe.edu.upc.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoRepository pR;
	
	@Override
	@Transactional
	public Integer insert(Producto producto) {
		int rpta = pR.buscardesProducto(producto.getDesProducto());
		if (rpta == 0) {
			pR.save(producto);
		}
		return rpta;
	}
	
	@Override
	@Transactional
	public void delete(long idProducto) {
		pR.deleteById(idProducto);	
	}
	
	@Override
	public List<Producto> list() {
		// TODO Auto-generated method stub
		return pR.findAll(Sort.by(Sort.Direction.ASC, "desProducto"));
	}
}
