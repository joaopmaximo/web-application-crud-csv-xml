package br.com.apideteste.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import aj.org.objectweb.asm.Opcodes;
import br.com.apideteste.projeto.DAO.IUsuario;
import br.com.apideteste.projeto.model.Usuario;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired // Classe da interface com as operações de banco de dados
	private IUsuario dao;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		
		//Criando a variavel lista do tipo lista de usuario que contem todos os usuarios do banco de dados
		List<Usuario> lista = (List<Usuario>) dao.findAll()
				;
		// O ResponseEntity irá retornar o status 200 e a variavel lista no body
		return ResponseEntity.status(200).body(lista);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Optional<Usuario>> consultar (@PathVariable Integer id) {
		Optional<Usuario> usuario = dao.findById(id);
		return ResponseEntity.status(200).body(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> incluir (@RequestBody Usuario usuario) {
		Usuario novoUsuario = dao.save(usuario);
		return ResponseEntity.status(201).body(novoUsuario);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> alterar (@RequestBody Usuario usuario) {
		Usuario usuarioAlterado = dao.save(usuario);
		return ResponseEntity.status(201).body(usuarioAlterado);
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Integer id) {
		dao.deleteById(id);
		return ResponseEntity.status(204).build();
	}
	
}
