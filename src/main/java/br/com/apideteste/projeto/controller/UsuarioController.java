package br.com.apideteste.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apideteste.projeto.DAO.IUsuario;
import br.com.apideteste.projeto.model.Usuario;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired // Classe da interface com as operações do banco de dados
	private IUsuario dao;
	
	@GetMapping
	public List<Usuario> listar() {
		return (List<Usuario>) dao.findAll();
	}
	
	@GetMapping ("/{id}")
	public Optional<Usuario> consultar (@PathVariable Integer id) {
		return dao.findById(id);
	}
	
	@PostMapping
	public Usuario incluir (@RequestBody Usuario usuario) {
		Usuario novoUsuario = dao.save(usuario);
		return novoUsuario;
	}
	
	@PutMapping
	public Usuario alterar (@RequestBody Usuario usuario) {
		Usuario alterado = dao.save(usuario);
		return alterado;
	}
	
	@DeleteMapping ("/{id}")
	public void deletar (@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
}
