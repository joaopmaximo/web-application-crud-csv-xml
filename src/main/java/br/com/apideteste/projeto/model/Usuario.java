package br.com.apideteste.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Entity define essa classe como uma entidade para fazer a interação com o banco de dados
@Table(name = "usuario") // Table define o nome da tabela. Caso o nome da classe seja igual não é necessário
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Diz que o campo id é auto incrementado
	@Column(name = "id") // Define a coluna e suas propriedades
	private Integer id;
	
	@Column(name = "nome_completo", length = 200, nullable = true)
	private String nome_completo;
	@Column(name = "username", length = 200, nullable = true)
	private String username;
	@Column(name = "email", length = 200, nullable = true)
	private String email;
	@Column(name = "senha", columnDefinition = "TEXT", nullable = true)
	private String senha;
	@Column(name = "telefone", length = 15, nullable = true)
	private String telefone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
		
}
