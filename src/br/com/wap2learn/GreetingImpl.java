package br.com.wap2learn;

public class GreetingImpl implements Greetings{

	@Override
	public String greet(String nome) {
		
		if(nome == null || nome.length() == 0){
			throw new IllegalArgumentException();
		}
		
		return "Hello " + nome;
	}
	
	
	
	

}
