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

import app.dto.EmpregadoDto;
import app.models.EmpregadoModel;
import app.repositories.DepartamentoRepository;
import app.repositories.EmpregadoRepository;

@RestController
@RequestMapping("empregado")
public class EmpregadoController {
	
	@Autowired 
	private EmpregadoRepository empregadoRepository;
	
	@Autowired 
	private DepartamentoRepository departamentoRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@GetMapping("list_all")
	public List<EmpregadoModel> listAllEmpregados() {
		return empregadoRepository.findAll();
	}
	
	@GetMapping("list/{empregadoId}")
	public ResponseEntity<Object> list(@PathVariable Integer empregadoId) {
		Optional<EmpregadoModel> empregado = empregadoRepository.findById(empregadoId);
		if (empregado.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(empregado.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empregado não cadastrado");
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody EmpregadoDto empregadoDto) {
		if(departamentoRepository.findById(empregadoDto.getDepartamentoId()).isPresent()) {
			EmpregadoModel empregadoModel = modelMapper.map(empregadoDto, EmpregadoModel.class);
			empregadoModel.setEmpregadoId(null);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(empregadoRepository.save(empregadoModel));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento não cadastrado");
	}
	
	@PutMapping("alterar/{empregadoId}")
	public ResponseEntity<Object> alterar(@Valid @RequestBody EmpregadoDto empregadoDto, @PathVariable Integer empregadoId) {
		if(departamentoRepository.findById(empregadoDto.getDepartamentoId()).isPresent()) {
			EmpregadoModel empregadoModel = modelMapper.map(empregadoDto, EmpregadoModel.class);
			empregadoModel.setEmpregadoId(empregadoId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(empregadoRepository.save(empregadoModel));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empregado não cadastrado");
	}
	
	@DeleteMapping("deletar/{empregadoId}")
	public ResponseEntity<Object> deletar(@PathVariable Integer empregadoId) {
		Optional<EmpregadoModel> empregado = empregadoRepository.findById(empregadoId);
		if (empregado.isPresent()) {
			empregadoRepository.deleteById(empregadoId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empregado não cadastrado");
	}
	
}
