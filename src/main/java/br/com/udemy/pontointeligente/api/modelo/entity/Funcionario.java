package br.com.udemy.pontointeligente.api.modelo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.udemy.pontointeligente.api.enumerate.Perfil;

@Entity
@Table(name="funcionario")
public class Funcionario implements DataCriavel, DataAtualizavel {
	
	private static final long serialVersionUID = -5064947394723511850L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String senha;
	
	@Column(nullable=false)
	private String cpf;
	
	@Column(name="valor_hora", nullable=false)
	private BigDecimal valorHora;
	
	@Column(name="quantidade_horas_trabalho_dia", nullable=false)
	private Float quantidadeHorasTrabalhoDia;
	
	@Column(name="quantidade_horas_almoco", nullable=false)
	private Float quantidadeHorasAlmoco;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Perfil perfil;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao", nullable=false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_atualizacao", nullable=false)
	private Date dataAtualizacao;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;
	
	@OneToMany(mappedBy="funcionario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
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
	
	@Transient
	public Optional<BigDecimal> getValorHoraOpt(){
		return Optional.ofNullable(valorHora);
	}
	
	@Transient
	public Optional<Float> getQuantidadeHorasTrabalhoDiaOpt(){
		return Optional.ofNullable(quantidadeHorasTrabalhoDia);
	}
	
	@Transient
	public Optional<Float> getQuantidadeHorasAlmocoOpt(){
		return Optional.ofNullable(quantidadeHorasAlmoco);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getValorHora() {
		return valorHora;
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public Float getQuantidadeHorasTrabalhoDia() {
		return quantidadeHorasTrabalhoDia;
	}

	public void setQuantidadeHorasTrabalhoDia(Float quantidadeHorasTrabalhoDia) {
		this.quantidadeHorasTrabalhoDia = quantidadeHorasTrabalhoDia;
	}

	public Float getQuantidadeHorasAlmoco() {
		return quantidadeHorasAlmoco;
	}

	public void setQuantidadeHorasAlmoco(Float quantidadeHorasAlmoco) {
		this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
