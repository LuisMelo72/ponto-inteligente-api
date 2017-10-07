package br.com.udemy.pontointeligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.repository.EmpresaRepository;
import br.com.udemy.pontointeligente.api.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscaPorCnpj(String cnpj) {
		
		LOG.info("Buscando uma empresa para o CNPJ {}", cnpj);
		
		Empresa empresa = empresaRepository.findByCnpj(cnpj);
		
		return Optional.ofNullable(empresa);
		
	}

	@Override
	public Empresa persiste(Empresa empresa) {
		
		LOG.info("Persistindo empresa: {}", empresa);
		
		empresaRepository.save(empresa);
		
		return empresa;
		
	}

}
