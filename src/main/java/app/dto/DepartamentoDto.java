package app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDto {
	
	@NotBlank
	@Pattern(regexp="[\\wÀ-ú ]+", message="{not.utilize.chars}")
	String nomeDepartamento;
	
	@NotBlank 
	@Pattern(regexp="[\\wÀ-ú ]+", message="{not.utilize.chars}")
	String local;
    
	@NotBlank
	@Pattern(regexp="([0-9]{1,3}\\.)*([0-9]{1,3}),[0-9]{2}", message="{monetary.pattern}") 
    String orcamento;

}
