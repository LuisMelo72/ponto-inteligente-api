package br.com.udemy.pontointeligente.api.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import br.com.udemy.pontointeligente.api.builder.EmpresaBuilderTest;
import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String CNPJ = "71027380000104";
	
	@Before
	public void setUp() {
		
		Empresa empresa = new EmpresaBuilderTest().create().build();
		empresa.setCnpj(CNPJ);
		empresa.setRazaoSocial("Empresa de Exemplo");
		
		empresaRepository.save(empresa);
		
	}
	
	@After
	public void tearDown() {
		empresaRepository.deleteAll();
	}
	
	@Test
	public void deveBuscarPorCnpj() {
		
		assertThat(empresaRepository.findByCnpj(CNPJ).getCnpj(), equalTo(CNPJ));
		
	}

}
