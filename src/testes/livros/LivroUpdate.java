package testes.livros;

import model.entities.Livros;
import model.services.LivroService;

public class LivroUpdate {

	public static void main(String[] args) {
		LivroService livroService = new LivroService();
		try {
			Livros livro = livroService.findById(1234);
			livro.setStatus(true);
			livro = livroService.updateLivro(livro);
			System.out.println("\n" + livro.toString() + "\n");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
	}

}
