package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Boleta;

@Repository
public interface IBoletaRepository extends JpaRepository<Boleta, Integer>{

	@Query("select count(b.desBoleta) from Boleta b where b.desBoleta =:desBoleta")
	public int buscardesBoleta(@Param("desBoleta") String desBoleta);
	
	Boleta findByIdBoleta(int idBoleta);
}
