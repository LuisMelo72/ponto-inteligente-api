package br.com.udemy.pontointeligente.api.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;
import br.com.udemy.pontointeligente.api.repository.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	@MockBean private LancamentoRepository lancamentoRepository;
	
	@Autowired private LancamentoService lancamentoService;
	
	@Before
	public void setUp() throws Exception {
		
		BDDMockito.given(lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
		BDDMockito.given(lancamentoRepository.findOne(Mockito.anyLong())).willReturn(new Lancamento());
		BDDMockito.given(lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
			.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		
		
	}
	
	@Test
	public void devePersistirLancamento() {
		assertThat(lancamentoService.persiste(new Lancamento()), is(notNullValue()));
	}
	
	@Test
	public void deveBuscarPorFuncionarioId() {
		assertThat(lancamentoService.buscaPorFuncionarioId(1L, new PageRequest(0, 10)), is(notNullValue()));
	}
	
	@Test
	public void deveBuscarPorId() {
		assertThat(lancamentoService.buscaPorId(1L).isPresent(), is(notNullValue()));
	}
	
}
