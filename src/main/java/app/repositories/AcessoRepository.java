package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.models.AcessoModel;

@Repository
public interface AcessoRepository extends JpaRepository<AcessoModel, Integer>{

	AcessoModel findByEmail(String email); 

}
