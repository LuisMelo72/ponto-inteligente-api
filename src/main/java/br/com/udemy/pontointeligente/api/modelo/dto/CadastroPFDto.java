package br.com.udemy.pontointeligente.api.modelo.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroPFDto {
	
	private Long id;
	
	@NotEmpty(message="Nome não pode ser vazio")
	@Length(min=3, max=200, message="Nome deve conter entre 3 e 200 caracteres")
	private String nome;
	
	@NotEmpty(message="Email não pode ser vazio")
	@Length(min=3, max=200, message="Email deve conter entre 3 e 200 caracteres")
	private String email;
	
	@NotEmpty(message="Senha não pode ser vazia")
	private String senha;
	
	@NotEmpty(message="CPF não pode ser vazio")
	@CPF(message="CPF inválido")
	private String cpf;
	
	@NotEmpty(message="CNPJ não pode ser vazio")
	@CNPJ(message="CNPJ inválido")
	private String cnpj;
	
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> quantidadeHorasTrabalhoDia = Optional.empty();
	private Optional<String> quantidadeHorasAlmoco = Optional.empty();
	
	@Override
	public String toString() {
		return "CadastroPFDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", cnpj=" + cnpj + ", valorHora=" + valorHora + ", quantidadeHorasTrabalhoDia="
				+ quantidadeHorasTrabalhoDia + ", quantidadeHorasAlmoco=" + quantidadeHorasAlmoco + "]";
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
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Optional<String> getValorHora() {
		return valorHora;
	}
	
	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}
	
	public Optional<String> getQuantidadeHorasTrabalhoDia() {
		return quantidadeHorasTrabalhoDia;
	}
	
	public void setQuantidadeHorasTrabalhoDia(Optional<String> quantidadeHorasTrabalhoDia) {
		this.quantidadeHorasTrabalhoDia = quantidadeHorasTrabalhoDia;
	}
	
	public Optional<String> getQuantidadeHorasAlmoco() {
		return quantidadeHorasAlmoco;
	}
	
	public void setQuantidadeHorasAlmoco(Optional<String> quantidadeHorasAlmoco) {
		this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
	}
	
}
