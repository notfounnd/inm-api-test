package app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpregadoDto {

	@NotBlank
	@Pattern(regexp="[\\wÀ-ú ]+", message="{not.utilize.chars}")
	String nome;
	
	@NotBlank 
	@Pattern(regexp="m|f|i", message="{sex.pattern}") 
	String sexo;
	
	@NotBlank 
	@Pattern(regexp="[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message="{cpf.pattern}")
	String cpf;
	
	@NotBlank
	@Pattern(regexp="[\\wÀ-ú ]+", message="{not.utilize.chars}")
	String cargo;
	
	@NotBlank
	@Pattern(regexp="[0-9]{2}/[0-9]{2}/[0-9]{4}", message="{date.pattern}")
	String admissao;
	
	@NotBlank
	@Pattern(regexp="([0-9]{1,3}\\.)*([0-9]{1,3}),[0-9]{2}", message="{monetary.pattern}")
	String salario;
	
	@NotBlank
	@Pattern(regexp="([0-9]{1,3}\\.)*([0-9]{1,3}),[0-9]{2}", message="{monetary.pattern}")
	String comissao;
	
	@NotBlank 
	@Pattern(regexp="pj|clt", message="{hiring.pattern}") 
	String tipoContratacao;
	
	@NotNull 
	Integer departamentoId;

}
