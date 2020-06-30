package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{

}
