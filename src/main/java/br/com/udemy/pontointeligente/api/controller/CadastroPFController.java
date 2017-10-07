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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.pontointeligente.api.helper.CadastroPFDtoHepler;
import br.com.udemy.pontointeligente.api.helper.FuncionarioHelper;
import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPFDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.modelo.response.Response;
import br.com.udemy.pontointeligente.api.service.EmpresaService;
import br.com.udemy.pontointeligente.api.service.FuncionarioService;

@RestController
@RequestMapping("/api/cadastrarpf")
@CrossOrigin(origins="*")
public class CadastroPFController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CadastroPFController.class);
	
	@Autowired EmpresaService empresaService;
	@Autowired FuncionarioService funcionarioService;
	
	@PostMapping
	public ResponseEntity<Response<CadastroPFDto>> cadastra(
			@Valid @RequestBody CadastroPFDto cadastroPFDto, BindingResult result){
		
		LOG.info("Cadastrando PF: {}", cadastroPFDto.toString());
		
		Response<CadastroPFDto> response = new Response<>();
		
		validarDadosExistentes(cadastroPFDto, result);
		
		if(result.hasErrors()) {
			
			LOG.error("Erro validacao dados do cadastro PF: {}", result.getAllErrors());
			
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			Funcionario funcionario = FuncionarioHelper.toFuncionario(cadastroPFDto);
			
			Optional<Empresa> empresa = empresaService.buscaPorCnpj(cadastroPFDto.getCnpj());
			
			empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
			
			funcionarioService.persiste(funcionario);
			
			response.setData(CadastroPFDtoHepler.toCadastroPFDto(funcionario));
			
			return ResponseEntity.ok(response);
			
		}
		
	}

	private void validarDadosExistentes(CadastroPFDto cadastroPFDto, BindingResult result) {
		
		Optional<Empresa> empresa = empresaService.buscaPorCnpj(cadastroPFDto.getCnpj());
		
		if(!empresa.isPresent()) {
			
			result.addError(new ObjectError("empresa", "Empresa não cadastrada"));
			
		}
		
		funcionarioService.buscaPorCpf(cadastroPFDto.getCpf()).ifPresent(
			func -> result.addError(new ObjectError("funcionario", "CPF já existente")));
		
		funcionarioService.buscaPorEmail(cadastroPFDto.getEmail()).ifPresent(
			func -> result.addError(new ObjectError("funcionario", "Email já existente")));
		
	}

}
