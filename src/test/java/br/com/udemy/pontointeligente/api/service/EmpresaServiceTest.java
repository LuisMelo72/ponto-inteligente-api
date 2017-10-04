package br.com.udemy.pontointeligente.api.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.repository.EmpresaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {

	@MockBean private EmpresaRepository empresaRepository;
	
	@Autowired private EmpresaService empresaService;
	
	private static final String CNPJ = "12509071000101";
	
	@Before
	public void setUp() throws Exception {
		
		BDDMockito.given(empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
		
	}
	
	@Test
	public void deveBuscarEmpresaPorCnpj() {
		assertThat(empresaService.buscaPorCnpj(CNPJ).isPresent(), equalTo(true));
	}
	
	@Test
	public void devePersistirEmpresa() {
		assertThat(empresaService.persiste(new Empresa()), is(notNullValue()));
	}
	
}
