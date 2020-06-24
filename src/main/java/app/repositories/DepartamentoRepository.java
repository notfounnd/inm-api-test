package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.models.DepartamentoModel;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoModel, Integer>{
	
}
