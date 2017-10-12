package br.com.udemy.pontointeligente.api.controller;

import java.text.ParseException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.pontointeligente.api.enumerate.LancamentoTipo;
import br.com.udemy.pontointeligente.api.helper.DateHelper;
import br.com.udemy.pontointeligente.api.helper.LancamentoDtoHelper;
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
		
		Lancamento lancamento = convertToLancamento(lancamentoDto, result);
		
		if(result.hasErrors()) {
			
			LOG.error("Erro validacao lancamento: {}", result.getAllErrors());
			
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			lancamentoService.persiste(lancamento);
			
			response.setData(LancamentoDtoHelper.toLancamentoDto(lancamento));
			
			return ResponseEntity.ok(response);
			
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Response<LancamentoDto>> atualiza(@PathVariable("id") Long id, 
			@Valid @RequestBody LancamentoDto lancamentoDto, BindingResult result) throws ParseException{
		
		LOG.info("Atualizando lancamento: {}", lancamentoDto.toString());
		
		Response<LancamentoDto> response = new Response<>();
		
		validaFuncionario(lancamentoDto.getFuncionarioId(), result);
		
		lancamentoDto.setId(Optional.of(id));
		
		Lancamento lancamento = convertToLancamento(lancamentoDto, result);
		
		if(result.hasErrors()) {
			
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			lancamento.setDataCriacao(new Date());
			
			lancamentoService.persiste(lancamento);
			
			response.setData(LancamentoDtoHelper.toLancamentoDto(lancamento));
			
			return ResponseEntity.ok(response);
			
		}
		
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Response<String>> remove(@PathVariable("id") Long id){
		
		LOG.info("Removendo lancamento ID: {}", id);
		
		Response<String> response = new Response<>();
		
		Optional<Lancamento> lancamento = lancamentoService.buscaPorId(id);
		
		if(!lancamento.isPresent()) {
			
			LOG.info("Erro ao remover devido ao lancamento ID: {}", id);
			
			response.getErros().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
			
			return ResponseEntity.badRequest().body(response);
			
		} else {
			
			lancamentoService.remove(id);
			
			return ResponseEntity.ok(response);
			
		}
		
	}
	
	private Lancamento convertToLancamento(LancamentoDto lancamentoDto, BindingResult result) throws ParseException {
		
		Lancamento lancamento = new Lancamento();
		
		if(lancamentoDto.getId().isPresent()) {
			
			Optional<Lancamento> lanc = lancamentoService.buscaPorId(lancamentoDto.getId().get());
			
			if(lanc.isPresent()) {
				
				lancamento = lanc.get();
				
			} else {
				
				result.addError(new ObjectError("lancamento", "Lançamento não encontrado"));
				
			}
			
		} else {
			
			lancamento.setFuncionario(new Funcionario());
			lancamento.getFuncionario().setId(lancamentoDto.getFuncionarioId());
			
		}
		
		lancamento.setData(DateHelper.toDate(lancamentoDto.getData(), "yyyy-MM-dd HH:mm:ss"));
		lancamento.setDescricao(lancamentoDto.getDescricao());
		lancamento.setLocalizacao(lancamentoDto.getLocalizacao());
		
		if(LancamentoTipo.isValidEnum(lancamentoDto.getTipo())) {
			
			lancamento.setTipo(LancamentoTipo.valueOf(lancamentoDto.getTipo()));
			
		} else {
			
			result.addError(new ObjectError("tipo", "Tipo inválido"));
			
		}
		
		
		return lancamento;
		
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
	
}
