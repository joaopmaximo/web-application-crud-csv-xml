package br.com.apideteste.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.apideteste.projeto.model.Usuario;
import br.com.apideteste.projeto.repository.IUsuario;

// O pacote "service" seria toda a lógica da programação, regra de negócio, processamento, relacionado à aplicação
@Service
public class UsuarioService {
	
	private final IUsuario repository; // Importando o repository com as operações de banco de dados | Injeção de dependência

	// Construtor passando o repository por parâmetro para essa classe
	public UsuarioService (IUsuario repository) {
		this.repository = repository;
	}
	
	public List<Usuario> listarUsuarios() {
		return (List<Usuario>) repository.findAll();
	}
	
	public Optional<Usuario> consultarUsuario (Integer id) {
		return repository.findById(id);
	}
	
	public Usuario criarUsuario (Usuario usuario) {
		repository.save(usuario);
		return usuario;
	}
	
	public Usuario alterarUsuario (Usuario usuario) {
		repository.save(usuario);
		return usuario;
	}

	public Boolean deletarUsuario (Integer id) {
		repository.deleteById(id);
		return true;
	}

}
