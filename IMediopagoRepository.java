package pe.edu.upc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Cliente;
import pe.edu.upc.entity.Mediopago;

@Repository
public interface IMediopagoRepository extends JpaRepository<Mediopago, Integer>{

	@Query("select count(m.desMedioPago) from Mediopago m where m.desMedioPago =:desMedioPago")
	public int buscardesMediopago(@Param("desMedioPago") String desMedioPago);
	
	@Query("select m from Mediopago m where m.desMedioPago like %:desMedioPago%")
	List<Mediopago> findBydesMediopago(String desMedioPago);
	
	List<Mediopago> findAllByCliente(Cliente cliente);
}
