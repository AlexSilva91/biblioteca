package testes.livros;

import model.entities.Livros;
import model.services.LivroService;

public class LivrosSave {

	public static void main(String[] args) {
		LivroService livroService = new LivroService();
		Livros livro = new Livros();
		
		try {
			livro.setAutor("Dan Brown");
			livro.setIsbn(12);
			livro.setAno(2000);
			livro.setExemplar(1);
			livro.setStatus(true);
			livro.setTitulo("Anjos e demônios");
			
			livro = livroService.saveLivro(livro);
			System.out.println("\n"+livro.toString()+"\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
