package br.com.udemy.pontointeligente.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.pontointeligente.api.helper.LancamentoDtoHelper;
import br.com.udemy.pontointeligente.api.modelo.dto.LancamentoDto;
import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;
import br.com.udemy.pontointeligente.api.modelo.response.Response;
import br.com.udemy.pontointeligente.api.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamento")
@CrossOrigin(origins="*")
public class LancamentoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LancamentoController.class);
	
	@Autowired private LancamentoService lancamentoService;
	
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

}
