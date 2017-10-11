package br.com.udemy.pontointeligente.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.pontointeligente.api.helper.FuncionarioDtoHelper;
import br.com.udemy.pontointeligente.api.helper.FuncionarioHelper;
import br.com.udemy.pontointeligente.api.modelo.dto.FuncionarioDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.modelo.response.Response;
import br.com.udemy.pontointeligente.api.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins="*")
public class FuncionarioController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired private FuncionarioService funcionarioService;
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Response<FuncionarioDto>> atualiza(@PathVariable("id") Long id,
			@Valid @RequestBody FuncionarioDto funcionarioDto, BindingResult result){
			
		LOG.info("Atualizando funcionario: {}", funcionarioDto.toString());
		
		Response<FuncionarioDto> response = new Response<>();
		
		Optional<Funcionario> funcionario = funcionarioService.buscaPorId(id);
		
		if(!funcionario.isPresent()) {
			
			result.addError(new ObjectError("funcionario", "Funcionário não encontrado"));
			
		} 
			
		validaDadosExistentes(funcionario.get(), funcionarioDto, result);
		
		if(result.hasErrors()) {
			
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			FuncionarioHelper.atualizaDadosFuncionario(funcionario.get(), funcionarioDto);
			
			funcionarioService.persiste(funcionario.get());
			
			response.setData(FuncionarioDtoHelper.toFuncionarioDto(funcionario.get()));
			
			return ResponseEntity.ok(response);
			
		}
		
	}
	
	public void validaDadosExistentes(Funcionario funcionario, FuncionarioDto funcionarioDto, BindingResult result) {
		
		if(funcionarioDto.getEmail().isPresent()
				&& !funcionarioDto.getEmail().get().equals(funcionario.getEmail())) {
			
			Optional<Funcionario> funcionarioCadastrado = funcionarioService.buscaPorEmail(funcionarioDto.getEmail().get());
			
			funcionarioCadastrado.ifPresent(
					func -> result.addError(new ObjectError("email", "Email já cadastrado")));
			
		}
		
	}

 }
