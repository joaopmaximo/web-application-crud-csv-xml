package br.com.apideteste.projeto.controller;

import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apideteste.projeto.model.Usuario;
// import br.com.apideteste.projeto.repository.IUsuario;
import br.com.apideteste.projeto.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

	/*
	@Autowired // Classe da interface com as operações de banco de dados | Autoinjeção de dependências
	private IUsuario dao;
	*/

	// Terceirização dos serviços para maior organização
	private UsuarioService usuarioService;

	// Construtor passando o usuarioService por parametro para essa classe
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
	public ResponseEntity<Usuario> criarUsuario (@RequestBody Usuario novoUsuario) {
		// O ResponseEntity irá retornar o status code 201 e o usuario criado no body
		return ResponseEntity.status(201).body(usuarioService.criarUsuario(novoUsuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> alterarUsuario (@RequestBody Usuario usuario) {
		// O ResponseEntity irá retornar o status code 201 e o usuario alterado no body
		return ResponseEntity.status(200).body(usuarioService.alterarUsuario(usuario));
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Integer id) {
		usuarioService.deletarUsuario(id);
		return ResponseEntity.status(204).build();
	}
	
}
