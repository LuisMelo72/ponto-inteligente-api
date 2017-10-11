package br.com.udemy.pontointeligente.api.modelo.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class FuncionarioDto {

	private Long id;
	
	@Length(min=3, max=200, message="Nome deve conter entre 3 e 200 caracteres")
	private Optional<String> nome;
	
	@Length(min=3, max=200, message="Email deve conter entre 3 e 200 caracteres")
	@Email(message="Email inválido")
	private Optional<String> email;
	
	private Optional<String> senha;
	private Optional<String> valorHora;
	private Optional<String> quantidadeHorasTrabalhoDia;
	private Optional<String> quantidadeHorasAlmoco;
	
	public FuncionarioDto() {
		nome = Optional.empty();
		email = Optional.empty();
		senha = Optional.empty();
		valorHora = Optional.empty();
		quantidadeHorasTrabalhoDia = Optional.empty();
		quantidadeHorasAlmoco = Optional.empty();
	}
	
	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", valorHora="
				+ valorHora + ", quantidadeHorasTrabalhoDia=" + quantidadeHorasTrabalhoDia + ", quantidadeHorasAlmoco="
				+ quantidadeHorasAlmoco + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
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

	public Optional<String> getEmail() {
		return email;
	}

	public void setEmail(Optional<String> email) {
		this.email = email;
	}

	public Optional<String> getNome() {
		return nome;
	}

	public void setNome(Optional<String> nome) {
		this.nome = nome;
	}
	
}
