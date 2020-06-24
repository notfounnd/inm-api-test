package app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name="departamento")
@JsonInclude(Include.NON_NULL)
public class DepartamentoModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer departamentoId;
	String nomeDepartamento;
	String local;
	String orcamento;
	
	@JsonInclude(Include.NON_EMPTY)
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="departamento", fetch=FetchType.LAZY)
	List<EmpregadoModel> empregados;
	
}
