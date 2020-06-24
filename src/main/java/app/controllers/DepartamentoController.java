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

import app.dto.DepartamentoDto;
import app.models.DepartamentoModel;
import app.repositories.DepartamentoRepository;

@RestController
@RequestMapping("departamento")
public class DepartamentoController {
	
	@Autowired 
	private DepartamentoRepository departamentoRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@GetMapping("list_all")
	public List<DepartamentoModel> listAllDeptos() {
		return departamentoRepository.findAll();
	}
	
	@GetMapping("list/{departamentoId}")
	public ResponseEntity<Object> list(@PathVariable Integer departamentoId) {
		Optional<DepartamentoModel> departamento = departamentoRepository.findById(departamentoId);
		if (departamento.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(departamento.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento não cadastrado");
	}
	
	@PostMapping("cadastrar")
	public DepartamentoModel cadastrar(@Valid @RequestBody DepartamentoDto departamentoDto) {
		 return departamentoRepository.save(modelMapper.map(departamentoDto, DepartamentoModel.class));
	}
	
	@PutMapping("alterar/{departamentoId}")
	public ResponseEntity<Object> DepartamentoModel (@Valid @RequestBody DepartamentoDto departamentoDto, @PathVariable Integer departamentoId) {
		if(departamentoRepository.findById(departamentoId).isPresent()) {
			DepartamentoModel departamentoModel = modelMapper.map(departamentoDto, DepartamentoModel.class);
			departamentoModel.setDepartamentoId(departamentoId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(departamentoRepository.save(departamentoModel));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empregado não cadastrado");
	}
	
	@DeleteMapping("deletar/{departamentoId}")
	public ResponseEntity<Object> deletar(@PathVariable Integer departamentoId) {
		Optional<DepartamentoModel> departamento = departamentoRepository.findById(departamentoId);
		if (departamento.isPresent()) {
			departamentoRepository.deleteById(departamentoId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento não cadastrado");
	}
	
}
