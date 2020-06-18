package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Tienda;

@Repository
public interface ITiendaRepository extends JpaRepository<Tienda, Integer>{
	
	@Query("select count(t.desTienda) from Tienda t where t.desTienda =:desTienda")
	public int buscardesTienda(@Param("desTienda") String desTienda);
	
	@Query("select t from Tienda t where t.desTienda like %:desTienda%")
	List<Tienda> findBydesTienda(String desTienda);

}
