package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Producto;
@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{

	@Query("select count(p.desProducto) from Producto p where p.desProducto =:desProducto")
	public int buscardesProducto(@Param("desProducto") String desProducto);
}
