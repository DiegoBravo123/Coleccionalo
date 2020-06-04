package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Evento;

public interface IEventoRepository extends JpaRepository<Evento, Integer>{

}
