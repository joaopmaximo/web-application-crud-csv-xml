package br.com.apideteste.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import br.com.apideteste.projeto.model.Usuario;
// import br.com.apideteste.projeto.repository.IUsuario;
import br.com.apideteste.projeto.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")

// O pacote "controller" serve para fazer a comunicação da aplicação com o exterior (end-point), tendo (normalmente) uma classe para cada entidade.
public class UsuarioController {

	/*
	@Autowired // Classe da interface com as operações de banco de dados | Autoinjeção de dependências
	private IUsuario dao;
	*/

	// Terceirização dos serviços para maior organização
	private UsuarioService usuarioService;

	// Construtor passando o usuarioService por parametro para essa classe | injeção de dependência por construtor
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		// O ResponseEntity irá retornar o status 200 e a lista de todos os usuarios no body
		return ResponseEntity.status(200).body(usuarioService.listarUsuarios());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Optional<Usuario>> consultarUsuario (@PathVariable Integer id) {
		// O ResponseEntity irá retornar o status code 200 e o usuario solicitado no body
		return ResponseEntity.status(200).body(usuarioService.consultarUsuario(id));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario (@Valid @RequestBody Usuario novoUsuario) {
		// O ResponseEntity irá retornar o status code 201 e o usuario criado no body
		return ResponseEntity.status(201).body(usuarioService.criarUsuario(novoUsuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alterarUsuario (@PathVariable Integer id, @RequestBody Map<String, Object> atributos) {
		Optional<Usuario> usuarioOptional = usuarioService.consultarUsuario(id);

		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();

			// Verifica se cada atributo está presente no Map e atualiza o valor correspondente no usuário
			if (atributos.containsKey("nome")) {
				usuario.setNome((String) atributos.get("nome"));
			}
			if (atributos.containsKey("email")) {
				usuario.setEmail((String) atributos.get("email"));
			}
			if (atributos.containsKey("senha")) {
				usuario.setSenha((String) atributos.get("senha"));
			}
			if (atributos.containsKey("telefone")) {
				usuario.setTelefone((String) atributos.get("telefone"));
			}

			Usuario usuarioAlterado = usuarioService.alterarUsuario(usuario);
			return ResponseEntity.status(200).body(usuarioAlterado);
		} else {
			return ResponseEntity.status(404).build();
		}
	}

	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Integer id) {
		usuarioService.deletarUsuario(id);
		return ResponseEntity.status(204).build();
	}

	// Quando ocorrer uma BAD_REQUEST do tipo MethodArgumentNotValidException esse
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		return errors;
	}
	
}
