package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Cliente;



@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("select count(c.desCliente) from Cliente c where c.desCliente =:desCliente")
	public int buscardesCliente(@Param("desCliente") String desCliente);
	
	@Query("select c from Cliente c where c.desCliente like %:desCliente%")
	List<Cliente> findBydesCliente(String desCliente);
}
