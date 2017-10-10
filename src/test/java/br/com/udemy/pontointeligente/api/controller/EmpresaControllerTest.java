package br.com.udemy.pontointeligente.api.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.udemy.pontointeligente.api.modelo.entity.Empresa;
import br.com.udemy.pontointeligente.api.service.EmpresaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmpresaControllerTest {
	
	@Autowired private MockMvc mvc;

	@MockBean private EmpresaService empresaService;
	
	private static final String PATH = "/api/empresa/cnpj/";
	private static final Long ID = 1L;
	private static final String CNPJ = "76217030000116";
	private static final String RAZAO_SOCIAL = "Empresa Exemplo";
	
	@Test
	public void deveRetornarErroParaCnpjInvalido() throws Exception {
		
		BDDMockito.given(empresaService.buscaPorCnpj(Mockito.anyString())).willReturn(Optional.empty());
		
		mvc.perform(MockMvcRequestBuilders.get(PATH + CNPJ).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.erros").value("Empresa não encontrada para o CNPJ " + CNPJ));
		
	}
	
	@Test
	public void deveRetornarSucessoParaCnpjValido() throws Exception {
		
		Empresa empresa = new Empresa();
		empresa.setId(ID);
		empresa.setCnpj(CNPJ);
		empresa.setRazaoSocial(RAZAO_SOCIAL);
		
		BDDMockito.given(empresaService.buscaPorCnpj(Mockito.anyString())).willReturn(Optional.of(empresa));
		
		mvc.perform(MockMvcRequestBuilders.get(PATH + CNPJ).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id").value(ID))
			.andExpect(jsonPath("$.data.cnpj", equalTo(CNPJ)))
			.andExpect(jsonPath("$.data.razaoSocial", equalTo(RAZAO_SOCIAL)))
			.andExpect(jsonPath("$.erros").isEmpty());
		
	}
	
}
