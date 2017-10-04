package br.com.udemy.pontointeligente.api.builder;

import java.util.Date;

import br.com.udemy.pontointeligente.api.enumerate.LancamentoTipo;
import br.com.udemy.pontointeligente.api.modelo.entity.Lancamento;

public class LancamentoBuilderTest {
	
	private Lancamento lancamento;
	
	public LancamentoBuilderTest create() {
		
		lancamento = new Lancamento();
		lancamento.setDescricao("Lancamento Exemplo");
		lancamento.setLocalizacao("Localizacao Exemplo");
		lancamento.setData(new Date());
		lancamento.setTipo(LancamentoTipo.ALMOCO_INICIO);
		lancamento.setFuncionario(new FuncionarioBuilderTest().create().buid());
		
		return this;
		
	}
	
	public Lancamento build() {
		
		return lancamento;
		
	}

}
