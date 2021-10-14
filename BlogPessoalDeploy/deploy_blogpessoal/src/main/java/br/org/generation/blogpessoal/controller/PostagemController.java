package br.org.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")// libera o acesso ao frontend
public class PostagemController {

	@Autowired //faz a injecao da nossa dependencia jpa
	private PostagemRepository postagemRepository;
		
	@GetMapping
	public ResponseEntity <List <Postagem>> getAll(){
		
		return ResponseEntity.ok(postagemRepository.findAll());
		
		//select * from tb_postagens; basicamente equivalente a isso no Banco de dados
		//mas estou fazendo isso através da injecao de dependencia	
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity <Postagem> findByID(@PathVariable long id){
		
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity <Postagem> postMetodo(@RequestBody Postagem postagem){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity <Postagem> putMetodo(@RequestBody Postagem postagem){
		
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void deleteRepositorio(@PathVariable long id) {
		
		postagemRepository.deleteById(id);
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
}
