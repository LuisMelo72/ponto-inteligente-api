package br.com.udemy.pontointeligente.api.controller;

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

import br.com.udemy.pontointeligente.api.helper.CadastroPJDtoHelper;
import br.com.udemy.pontointeligente.api.helper.EmpresaHelper;
import br.com.udemy.pontointeligente.api.helper.FuncionarioHelper;
import br.com.udemy.pontointeligente.api.modelo.dto.CadastroPJDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.modelo.response.Response;
import br.com.udemy.pontointeligente.api.service.EmpresaService;
import br.com.udemy.pontointeligente.api.service.FuncionarioService;

@RestController
@RequestMapping("/api/cadastrarpj")
@CrossOrigin(origins="*")
public class CadastroPJController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired private FuncionarioService funcionarioService;
	@Autowired private EmpresaService empresaService;
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastra(
			@Valid @RequestBody CadastroPJDto cadastroPJDto, BindingResult result){
		
		LOG.info("Cadastrando PJ: {}", cadastroPJDto.toString());
		
		Response<CadastroPJDto> response = new Response<>();
		
		validarDadosExistentes(cadastroPJDto, result);
		
		if(result.hasErrors()) {
			
			LOG.error("Erro validacao dados de cadastro PJ: {}", result.getAllErrors());
			
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			Empresa empresa = EmpresaHelper.toEmpresa(cadastroPJDto);
			
			Funcionario funcionario = FuncionarioHelper.toFuncionario(cadastroPJDto);
			
			empresaService.persiste(empresa);
			
			funcionario.setEmpresa(empresa);
			
			funcionarioService.persiste(funcionario);
			
			response.setData(CadastroPJDtoHelper.toCadastroPJDto(funcionario));
			
			return ResponseEntity.ok(response);
			
		}
		
	}

	private void validarDadosExistentes(CadastroPJDto cadastroPJDto, BindingResult result) {
		
		empresaService.buscaPorCnpj(cadastroPJDto.getCnpj()).ifPresent(
				emp -> result.addError(new ObjectError("empresa", "Empresa já cadastrada")));
		
		funcionarioService.buscaPorCpf(cadastroPJDto.getCpf()).ifPresent(
				func -> result.addError(new ObjectError("funcionario", "CPF já cadastrado")));
		
		funcionarioService.buscaPorEmail(cadastroPJDto.getEmail()).ifPresent(
				func -> result.addError(new ObjectError("funcionario", "Email já cadastrado")));
		
	}

}
