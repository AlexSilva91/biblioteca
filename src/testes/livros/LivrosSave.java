package testes.livros;

import model.entities.Livros;
import model.services.LivroService;

public class LivrosSave {

	public static void main(String[] args) {
		LivroService livroService = new LivroService();
		Livros livro = new Livros();
		
		try {
			livro.setAutor("Dan Brown");
			livro.setIsbn(1234);
			livro.setLacamento(2010);
			livro.setNumExemplares(1);
			livro.setStatus(false);
			livro.setTitulo("O simbolo perdido");
			
			livro = livroService.saveLivro(livro);
			System.out.println("\n"+livro.toString()+"\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
