package app.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name="empregado")
@JsonInclude(Include.NON_NULL)
public class EmpregadoModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer empregadoId;
	String nome;
	String sexo;
	String cpf;
	String cargo;
	String admissao;
	String salario;
	String comissao;
	String tipoContratacao;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="departamento_id")
	DepartamentoModel departamento;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL, mappedBy="empregado", fetch=FetchType.LAZY)
	AcessoModel acesso;
	
}
