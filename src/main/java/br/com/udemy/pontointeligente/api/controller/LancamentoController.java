package br.com.udemy.pontointeligente.api.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.pontointeligente.api.enumerate.LancamentoTipo;
import br.com.udemy.pontointeligente.api.helper.LancamentoDtoHelper;
import br.com.udemy.pontointeligente.api.helper.LancamentoHelper;
import br.com.udemy.pontointeligente.api.helper.StringHelper;
import br.com.udemy.pontointeligente.api.modelo.dto.LancamentoDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;
import br.com.udemy.pontointeligente.api.modelo.response.Response;
import br.com.udemy.pontointeligente.api.service.FuncionarioService;
import br.com.udemy.pontointeligente.api.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamento")
@CrossOrigin(origins="*")
public class LancamentoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LancamentoController.class);
	
	@Autowired private LancamentoService lancamentoService;
	@Autowired private FuncionarioService funcionarioService;
	
	@Value("${paginacao.quantidade_por_pagina}")
	private int quantidadePorPagina;
	
	@GetMapping(value="/funcionario/{funcionarioId}")
	public ResponseEntity<Response<Page<LancamentoDto>>> listaPorFuncionarioId(
			@PathVariable("funcionarioId") Long funcionarioId,
			@RequestParam(value="pagina", defaultValue="0") int pagina,
			@RequestParam(value="campoOrdenacao", defaultValue="id") String campoOrdenacao,
			@RequestParam(value="modoOrdenacao", defaultValue="DESC") String modoOrdenacao){
				
		LOG.info("Buscando lancamentos por ID do funcionario: {}, pagina: {}", funcionarioId, pagina);
		
		Response<Page<LancamentoDto>> response = new Response<>();
		
		PageRequest pageRequest = new PageRequest(pagina, quantidadePorPagina, Direction.valueOf(modoOrdenacao), campoOrdenacao);
		
		Page<Lancamento> lancamentos = lancamentoService.buscaPorFuncionarioId(funcionarioId, pageRequest);
		
		Page<LancamentoDto> lancamentosDto = lancamentos.map(lancamento -> LancamentoDtoHelper.toLancamentoDto(lancamento));
		
		response.setData(lancamentosDto);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Response<LancamentoDto>> listaPorId(@PathVariable("id") Long id){
		
		LOG.info("Buscando lancamento por ID: {}", id);
		
		Response<LancamentoDto> response = new Response<>();
		
		Optional<Lancamento> lancamento = lancamentoService.buscaPorId(id);
		
		if(!lancamento.isPresent()) {
			
			LOG.error("Lancamento nao encontrado para o ID: {}", id);
			
			response.getErros().add("Lançamento não encontrado para o id: " + id);
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			response.setData(LancamentoDtoHelper.toLancamentoDto(lancamento.get()));
			
			return ResponseEntity.ok(response);
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Response<LancamentoDto>> adiciona(
			@Valid @RequestBody LancamentoDto lancamentoDto, BindingResult result) throws ParseException{
		
		LOG.info("Adicionando lancamento: {}", lancamentoDto.toString());
		
		Response<LancamentoDto> response = new Response<>();
		
		validaFuncionario(lancamentoDto.getFuncionarioId(), result);
		
		validaLancamentoTipo(lancamentoDto.getTipo(), result);
		
		if(result.hasErrors()) {
			
			LOG.error("Erro validacao lancamento: {}", result.getAllErrors());
			
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			Optional<Funcionario> funcionario = funcionarioService.buscaPorId(lancamentoDto.getFuncionarioId());
			
			Lancamento lancamento = LancamentoHelper.toLancamento(lancamentoDto);
			lancamento.setFuncionario(funcionario.get());
			
			lancamentoService.persistir(lancamento);
			
			return ResponseEntity.ok(response);
			
		}
		
	}

	private void validaFuncionario(Long funcionarioId, BindingResult result) {
		
		LOG.info("Validando funcionario ID: {}", funcionarioId);
		
		if(funcionarioId == null) {
			
			result.addError(new ObjectError("funcionario", "Funcionário não informado"));
			
		} else {
			
			Optional<Funcionario> funcionario = funcionarioService.buscaPorId(funcionarioId);
			
			if(!funcionario.isPresent()) {
				
				result.addError(new ObjectError("funcionario", "Funcionário não encontrado. ID inexistente"));
				
			}
			
		}
		
	}
	
	private void validaLancamentoTipo(String lancamentoTipo, BindingResult result) {
		
		LOG.info("Validando lancamentoTipo: {}", lancamentoTipo);
		
		if(StringHelper.isNullOrEmpty(lancamentoTipo)) {
			
			result.addError(new ObjectError("tipo", "Tipo de lançamento não informado"));
			
		} else {
			
			Optional<LancamentoTipo> tipo = LancamentoTipo.fromString(lancamentoTipo);
			
			if(!tipo.isPresent()) {
				
				result.addError(new ObjectError("tipo", "Tipo de lançamento inxistente"));
				
			}
			
		}
		
		
	}

}
