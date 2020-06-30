package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.entity.Categoria;
import pe.edu.upc.repository.ICategoriaRepository;
import pe.edu.upc.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService, Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	private ICategoriaRepository cR;
	
	
	@Override
	@Transactional
	public Integer insertar(Categoria categoria) {
		// TODO Auto-generated method stub
		int rpta = cR.buscardesCategoria(categoria.getDesCategoria());
		if (rpta == 0) {
			cR.save(categoria);
		}
		return rpta;
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> list() {
		// TODO Auto-generated method stub
		return cR.findAll(Sort.by(Sort.Direction.DESC, "desCategoria"));
	}
	
	@Override
	public List<Categoria> findByName(String desCategoria) {
		// TODO Auto-generated method stub
		return cR.findBydesCategoria(desCategoria);
	}
	
	@Override
	public Optional<Categoria> listarId(Integer idCategoria) {
		// TODO Auto-generated method stub
		return cR.findById(idCategoria);
	}
	
	@Override
	@Transactional
	public void delete(int idCategoria) {
		cR.deleteById(idCategoria);
		
	}
}
