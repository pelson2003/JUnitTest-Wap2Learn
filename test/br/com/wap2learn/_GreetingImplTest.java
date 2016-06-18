package br.com.wap2learn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class _GreetingImplTest {
	
	private Greetings greeting;

	@Before
	public void setup(){
		System.out.println("Metodo de Setup, Roda Antes de Todo Método de Teste");
		greeting = new GreetingImpl();
	}
	
	@Test
	public void greetShouldReturnAValidOutput() {
		System.out.println("greetShouldReturnAValidOutput");
		String result = greeting.greet("Junit");
		assertNotNull(result);
		assertEquals("Hello Junit", result);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void greatShouldThrowandExpecption_for_NomeisNull(){
		System.out.println("greatShouldThrowandExpecption_for_NomeisNull");
		greeting.greet(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void greatShouldThrowandExpecption_for_NomeisLenghtEqualsToZero(){
		System.out.println("greatShouldThrowandExpecption_for_NomeisLenghtEqualsToZero");
		greeting.greet("");
	}
	
	@After
	public void teardown(){
		System.out.println("Metodo de CleanUp, Roda Depois de Todo Método de Teste");
		greeting = null;
	}
	
	
	
}