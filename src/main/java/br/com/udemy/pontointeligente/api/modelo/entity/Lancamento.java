package br.com.udemy.pontointeligente.api.modelo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.udemy.pontointeligente.api.enumerate.LancamentoTipo;

@Entity
@Table(name="lancamento")
public class Lancamento implements Criavel, Atualizavel {
	
	private static final long serialVersionUID = -7111624292387087313L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false)
	private String localizacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao", nullable=false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_atualizacao", nullable=false)
	private Date dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	private LancamentoTipo tipo;
	
	@ManyToOne
	@JoinColumn(name="funcionario_id")
	private Funcionario funcionario;

	@PrePersist
	@Override
	public void prePersist() {
		
		final Date atual = new Date();
		
		dataCriacao = atual;
		dataAtualizacao = atual;
		
	}
	
	@PreUpdate
	@Override
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public LancamentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(LancamentoTipo tipo) {
		this.tipo = tipo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
