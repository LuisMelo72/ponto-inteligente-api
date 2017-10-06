package br.com.udemy.pontointeligente.api.modelo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="empresa")
public class Empresa implements DataCriavel, DataAtualizavel {
	
	private static final long serialVersionUID = -579108758159771658L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="razao_social", nullable=false)
	private String razaoSocial;
	
	@Column(nullable=false)
	private String cnpj;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao", nullable=false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_atualizacao", nullable=false)
	private Date dataAtualizacao;
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Funcionario> funcionarios;
	
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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
