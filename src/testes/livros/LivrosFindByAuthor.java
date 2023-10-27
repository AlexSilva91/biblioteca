package testes.livros;

import java.util.List;

import model.entities.Livros;
import model.services.LivroService;

public class LivrosFindByAuthor {
	public static void main(String[] args) {
		LivroService livroService = new LivroService();

		try {
			List<Livros> list = livroService.listAllFindByAuthor("Dan Brown");
			for (Livros livro : list) {
				System.out.println("\n" + livro.toString() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
