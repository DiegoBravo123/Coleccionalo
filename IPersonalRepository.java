package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Personal;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Integer>{
	
	@Query("select count(pe.desPersonal) from Personal pe where pe.desPersonal =:desPersonal")
	public int buscardesPersonal(@Param("desPersonal") String desPersonal);
	
	@Query("select pe from Personal pe where pe.desPersonal like %:desPersonal%")
	List<Personal> findBydesPersonal(String desPersonal);

}
