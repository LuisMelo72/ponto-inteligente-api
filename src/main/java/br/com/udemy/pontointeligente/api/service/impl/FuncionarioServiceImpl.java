package br.com.udemy.pontointeligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.pontointeligente.api.modelo.entity.Funcionario;
import br.com.udemy.pontointeligente.api.repository.FuncionarioRepository;
import br.com.udemy.pontointeligente.api.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired private FuncionarioRepository funcionarioRepository;

	@Override
	public Funcionario persiste(Funcionario funcionario) {
		
		LOG.info("Persistindo funcionario {}", funcionario);
		
		funcionarioRepository.save(funcionario);
		
		return funcionario;
	}

	@Override
	public Optional<Funcionario> buscaPorCpf(String cpf) {
		
		LOG.info("Buscando funcionario por CPF {}", cpf);
		
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		
		return Optional.ofNullable(funcionario);
		
	}

	@Override
	public Optional<Funcionario> buscaPorEmail(String email) {
		
		LOG.info("Buscando funcionario por Email {}", email);
		
		Funcionario funcionario = funcionarioRepository.findByEmail(email);
		
		return Optional.ofNullable(funcionario);
		
	}

	@Override
	public Optional<Funcionario> buscaPorId(Long id) {
		
		LOG.info("Buscando funcionario por ID {}", id);
		
		Funcionario funcionario = funcionarioRepository.findOne(id);
		
		return Optional.ofNullable(funcionario);
		
	}

}
