package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
		
}
