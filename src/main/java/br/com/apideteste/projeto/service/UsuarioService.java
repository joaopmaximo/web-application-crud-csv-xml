package br.com.apideteste.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.apideteste.projeto.model.Usuario;
import br.com.apideteste.projeto.repository.IUsuario;

// O pacote "service" seria toda a lógica da programação, regra de negócio, processamento, relacionado à aplicação
@Service
public class UsuarioService {
	
	private IUsuario repository; // Importando o repository com as operações de banco de dados | Injeção de dependência
	// PasswordEncoder passwordEncoder; // Importando o passwordEncoder | Injeção de dependência

	// Construtor passando o repository por parâmetro para essa classe
	public UsuarioService (IUsuario repository) {
		this.repository = repository;
		//this.passwordEncoder = new BCryptPasswordEncoder(); // Quando chamar essa instancia vai conseguir acessar os métodos de criptografia
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
		Usuario novoUsuario = repository.save(usuario); // Acresenta o usuário ao banco
		return novoUsuario; // Retorna o novo usuario
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
