package br.com.apideteste.projeto.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.apideteste.projeto.model.Usuario;

// CrudRepository é uma interface para realizar operações CRUD (Create Read Update Delete) em um banco de dados
// ele contem métodos prontos para essas operações, facilitando o processo
// Pode utilizar tambem o JpaRepository
public interface IUsuario extends CrudRepository<Usuario, Integer>{
}
