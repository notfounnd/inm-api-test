package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.models.EmpregadoModel;

@Repository
public interface EmpregadoRepository extends JpaRepository<EmpregadoModel, Integer>{
	
}
