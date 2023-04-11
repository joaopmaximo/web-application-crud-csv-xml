package br.com.apideteste.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.apideteste.projeto.model.Usuario;
import br.com.apideteste.projeto.repository.IUsuario;

@Service
public class UsuarioService {
	
	// Importando o repository com as operações de banco de dados | Injeção de dependência
	private IUsuario repository;

	// Construtor passando o repository por parâmetro para essa classe
	public UsuarioService (IUsuario repository) {
		this.repository = repository;
	}
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = (List<Usuario>) repository.findAll();
		return lista;
	}
	
	public Optional<Usuario> consultarUsuario (Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario;
	}
	
	public Usuario criarUsuario (Usuario usuario) {
		Usuario novoUsuario = repository.save(usuario);
		return repository.save(novoUsuario);
	}
	
	public Usuario alterarUsuario (Usuario usuario) {
		Usuario usuarioAlterado = repository.save(usuario);
		return usuarioAlterado;
	}

	public Boolean deletarUsuario (Integer id) {
		repository.deleteById(id);
		return true;
	}
}
