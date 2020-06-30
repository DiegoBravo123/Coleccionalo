package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Producto;
import pe.edu.upc.entity.Tienda;
@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{


	@Query("select count(p.desProducto) from Producto p where p.desProducto =:desProducto")
	public int buscardesProducto(@Param("desProducto") String desProducto);
	
	Producto findByIdProducto(int idProducto);
	
	List<Producto> findAllByTienda(Tienda tienda);

	
	@Query("select p from Producto p where p.desProducto like %:desProducto%")
	List<Producto> findByDesProducto(String desProducto);
	
	@Query("select p from Producto p where p.tienda.vendedor.username like %?1%")
	List<Producto> findProductoByUsernameVendedor(String username);
}
