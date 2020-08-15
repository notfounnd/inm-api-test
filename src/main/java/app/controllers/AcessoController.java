package app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.AcessoDto;
import app.dto.LoginDto;
import app.models.AcessoModel;
import app.repositories.AcessoRepository;
import app.repositories.EmpregadoRepository;

@RestController
@RequestMapping("acesso")
public class AcessoController {
	
	@Autowired 
	private AcessoRepository acessoRepository;
	
	@Autowired 
	private EmpregadoRepository empregadoRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@GetMapping("list_all")
	public List<AcessoModel> listAllEmpregados() {
		return acessoRepository.findAll();
	}
	
	@GetMapping("list/{acessoId}")
	public ResponseEntity<Object> list(@PathVariable Integer acessoId) {
		Optional<AcessoModel> acesso = acessoRepository.findById(acessoId);
		if (acesso.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(acesso.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Acesso não cadastrado");
	}
	
	@GetMapping("login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto) {
		AcessoModel acessoModel = acessoRepository.findByEmail(loginDto.getEmail());
		if (acessoModel == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		} 
		if (!acessoModel.getPassword().equals(loginDto.getPassword())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(acessoModel);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody AcessoDto acessoDto) {
		if(empregadoRepository.findById(acessoDto.getEmpregadoId()).isPresent()) {
			AcessoModel acesso = modelMapper.map(acessoDto, AcessoModel.class);
			acesso.setAcessoId(null);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(acessoRepository.save(acesso));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empregado não cadastrado");
	}
	
	@PutMapping("alterar/{acessoId}")
	public ResponseEntity<Object> alterar(@Valid @RequestBody AcessoDto acessoDto, @PathVariable Integer acessoId) {
		if(empregadoRepository.findById(acessoDto.getEmpregadoId()).isPresent()) {
			AcessoModel acessoModel = acessoRepository.save(modelMapper.map(acessoDto, AcessoModel.class));
			acessoModel.setAcessoId(acessoId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(acessoRepository.save(acessoModel));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Acesso não cadastrado");
	}
	
	@DeleteMapping("deletar/{acessoId}")
	public ResponseEntity<Object> deletar(@PathVariable Integer acessoId) {
		Optional<AcessoModel> acesso = acessoRepository.findById(acessoId);
		if (acesso.isPresent()) {
			acessoRepository.deleteById(acessoId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Acesso não cadastrado");
	}
	
	
}
