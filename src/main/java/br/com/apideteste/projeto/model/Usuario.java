package br.com.apideteste.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// O pacote "model" se refere às entidades, modelos, todos que se comunicam com o banco de dados, da aplicação

@NoArgsConstructor // Lombok cria construtor sem argumentos (se torna o construtor padrão)
@AllArgsConstructor // Lombok cria construtor com todos os argumentos
@Data // Lombok cria os métodos getters, setters, toString para todos os atributos
@Entity // Entity define essa classe como uma entidade para fazer a interação com o banco de dados
@Table(name = "usuario") // Table define o nome da tabela. Caso o nome da classe seja igual não é necessário
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Diz que o campo id é auto incrementado
	@Column(name = "id") // Define a coluna e suas propriedades
	private Integer id;

	// @NotNull // Não permite que o campo seja nulo, porém, se for uma string vazia, permite
	// @NotEmpty // Obriga que tenha pelo menos 1 caracter, mas ainda pode ter espaços vazios
	@NotBlank(message = "O nome é obrigatório") // Verifica tanto se for nulo quanto se houver apenas caractéres vazios (recomendado)
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	@Column(name = "email", length = 200, nullable = false)
	private String email;
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
}
