package br.org.generation.blogpessoal.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
    
    public Usuario usuario;
    public Usuario usuarioErro = new Usuario();

	@Autowired
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Validator validator = factory.getValidator();

	@BeforeEach
	public void start() {
        
		usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278");

	}

	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {

		/*faz a validacao do usuario, se for vazio ele vai mostrar o isEmpty True
		lembrando que, a linha 45 é referente a linha 48 ou seja
		caso tenha algum erro na validacao, ele vai salvar na linha 46 e vai jogar dentro da minha linha 48*/
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		
		System.out.println(violacao.toString());

		assertTrue(violacao.isEmpty());
	}
    
    @Test
	@DisplayName("✖ Não Valida Atributos Nulos")
	void  testNaoValidaAtributos() {

    	/*nessa parte ele nao esta com nenhum parametro no meu usuarioErro, logo ele vai retorna um erro
    	 * pois ele encontrara vario parametros que nao foram construidos corretamente
    	 * isso porque, devemos seguir um processo de construcao do meu banco de dados(referente a minha model Usuario nesse caso)
    	 * logo ele vai salvar na linha 60 varios erros de validacao, isso vai acarretar 
    	 * que no final da execucao ira dar um erro.*/
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
		System.out.println(violacao.toString());

		assertTrue(violacao.isEmpty());
	}

}
