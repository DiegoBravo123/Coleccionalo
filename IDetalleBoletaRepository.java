package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.entity.DetalleBoleta;

@Repository
public interface IDetalleBoletaRepository extends JpaRepository<DetalleBoleta, Integer>{
	
	@Query("select count(db.desComentario) from DetalleBoleta db where db.desComentario =:desComentario")
	public int buscardesComentario(@Param("desComentario") String desComentario);
	
	Boleta findByIdDetalleBoleta(Integer idDetalleBoleta);

}
