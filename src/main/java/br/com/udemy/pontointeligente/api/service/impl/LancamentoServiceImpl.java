package br.com.udemy.pontointeligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;
import br.com.udemy.pontointeligente.api.repository.LancamentoRepository;
import br.com.udemy.pontointeligente.api.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired private LancamentoRepository lancamentoRepository;

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		
		LOG.info("Persistindo lancamento {}", lancamento);
		
		lancamentoRepository.save(lancamento);
		
		return lancamento;
		
	}

	@Override
	public void remove(Long id) {
		
		LOG.info("Removendo o lancamento ID {}", id);
		
		lancamentoRepository.delete(id);

	}

	@Override
	public Optional<Lancamento> buscaPorId(Long id) {
		
		LOG.info("Buscando um lancamento pelo ID {}", id);
	
		Lancamento lancamento = lancamentoRepository.findOne(id);
		
		return Optional.ofNullable(lancamento);
		
	}

	@Override
	public Page<Lancamento> buscaPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		
		LOG.info("Buscando lancamentos para funcionario ID {}", funcionarioId);
		
		Page<Lancamento> pageFuncionario = lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
		
		return pageFuncionario;
		
	}

}
