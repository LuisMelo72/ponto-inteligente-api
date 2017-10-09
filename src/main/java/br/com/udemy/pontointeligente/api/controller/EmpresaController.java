package br.com.udemy.pontointeligente.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.pontointeligente.api.modelo.dto.EmpresaDto;
import br.com.udemy.pontointeligente.api.modelo.dto.EmpresaDtoHelper;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.modelo.response.Response;
import br.com.udemy.pontointeligente.api.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins="*")
public class EmpresaController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired private EmpresaService empresaService;
	
	@GetMapping(value="/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscaPorCnpj(@PathVariable("cnpj") String cnpj){
		
		LOG.info("Buscando emporesa por CNPJ: {}", cnpj);
		
		Response<EmpresaDto> response = new Response<>();
		
		Optional<Empresa> empresa = empresaService.buscaPorCnpj(cnpj);
		
		if(!empresa.isPresent()) {
			
			LOG.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			
			response.getErros().add("Empresa não encontrada para o CNPJ " + cnpj);
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			response.setData(EmpresaDtoHelper.toEmpresaDto(empresa.get()));
			
			return ResponseEntity.ok(response);
			
		}
		
	}

}
