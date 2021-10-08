package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//executa o teste por classe, ao inves de metodos
public class UsuarioRepositoryTest {
    
    @Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		Usuario usuario = new Usuario(0, "João da Silva", "joao@email.com.br", "13465278");
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		
		usuario = new Usuario(0, "Manuel da Silva", "manuel@email.com.br", "13465278");
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		
		usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "13465278");
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

        usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278");
        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            usuarioRepository.save(usuario);
	}
	
    // Testa o método findByNome()
	@Test
	@DisplayName("💾 Retorna o nome")
	public void findByNomeRetornaNome() throws Exception {

		//verifica se existe um usuario com nome Joao da Silva, no meu banco de dados
		Usuario usuario = usuarioRepository.findByNome("João da Silva");
		assertTrue(usuario.getNome().equals("João da Silva"));
	}
	
    // Testa o método findAllByNomeContainingIgnoreCase()
	@Test
	@DisplayName("💾 Retorna 3 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		//verifica se existe a quantidade de 3 usuarios com o nome joao da silva, no meu banco de dados
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
	}

	//dropa o banco de dados
	@AfterAll
	public void end() {
		
		usuarioRepository.deleteAll();
		
	}
}
