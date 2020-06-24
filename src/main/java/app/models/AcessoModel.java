package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity(name="acesso")
@JsonInclude(Include.NON_NULL)
public class AcessoModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer acessoId;
	String email;
	String password;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="empregado_id")
	EmpregadoModel empregado;
	
}
