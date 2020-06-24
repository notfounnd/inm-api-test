package app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoDto {
	
	@Email 
	@NotBlank 
	String email;
	
	@NotBlank 
	String password;
	
	@NotNull 
	Integer	empregadoId;
	
}
