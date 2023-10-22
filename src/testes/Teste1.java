package testes;

import model.entities.Livros;

public class Teste1 {

	public static void main(String[] args) {
		Livros livros = new Livros();
		livros.setAutor("Jonas");
		livros.setIsbn(123456789);
		livros.setStatus(false);
		livros.setLacamento(2019);
		livros.setTitulo("A volta dos que não foram");
		System.out.println(livros.toString());
	}

}
