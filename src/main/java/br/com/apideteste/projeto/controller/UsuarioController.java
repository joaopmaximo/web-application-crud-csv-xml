package br.com.apideteste.projeto.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.apideteste.projeto.service.DownloadService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.apideteste.projeto.model.Usuario;
import br.com.apideteste.projeto.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)

// O pacote "controller" serve para fazer a comunicação da aplicação com o exterior (end-point), tendo (normalmente) uma classe para cada entidade.
public class UsuarioController {

	// Terceirização dos serviços para maior organização
	private final UsuarioService usuarioService;
	private final DownloadService downloadService;

	// Construtor passando o usuarioService por parametro para essa classe | injeção de dependência por construtor
	public UsuarioController(UsuarioService usuarioService, DownloadService downloadService) {
		this.usuarioService = usuarioService;
		this.downloadService = downloadService;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		// O ResponseEntity irá retornar o status 200 e a lista de todos os usuarios no body
		return ResponseEntity.status(200).body(usuarioService.listarUsuarios());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> consultarUsuario (@PathVariable Integer id) {
		Optional<Usuario> usuarioOptional = usuarioService.consultarUsuario(id);

		if (usuarioOptional.isPresent()) {
			// O ResponseEntity irá retornar o status code 200 e o usuario solicitado no body
			return ResponseEntity.status(200).body(usuarioService.consultarUsuario(id));
		}

		return ResponseEntity.status(404).build();
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
		}

		return ResponseEntity.status(404).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario (@PathVariable Integer id) {
		Optional<Usuario> usuarioOptional = usuarioService.consultarUsuario(id);

		if (usuarioOptional.isPresent()) {
			usuarioService.deletarUsuario(id);
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/csv")
	public void exportarCsv(HttpServletResponse servletResponse) throws IOException {
		// Configurar o cabeçalho de resposta
		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition","attachment; filename=usuarios.csv");
		downloadService.exportarCsv(servletResponse.getWriter());
	}

	@GetMapping("/xml")
	public void exportaXml(HttpServletResponse servletResponse) throws IOException {
		// Configurar o cabeçalho de resposta
		servletResponse.setContentType("text/xml");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=usuarios.xml");
		downloadService.exportarXml(servletResponse.getWriter());
	}

}
