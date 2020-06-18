package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
	
	@Query("select count(r.desRol) from Rol r where r.desRol =:desRol")
	public int buscardesRol(@Param("desRol") String desRol);
	
	@Query("select r from Rol r where r.desRol like %:desRol%")
	List<Rol> findBydesRol(String desRol);

}
