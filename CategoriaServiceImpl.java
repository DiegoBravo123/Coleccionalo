package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Categoria;
import pe.edu.upc.repository.ICategoriaRepository;
import pe.edu.upc.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	@Autowired
	private ICategoriaRepository cR;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}
	
}
