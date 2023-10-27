package testes.livros;

import java.util.List;

import model.entities.Livros;
import model.services.LivroService;

public class LivrosFindByTitle {
	public static void main(String[] args) {
		LivroService livroService = new LivroService();

		try {
			List<Livros> list = livroService.listAllFindByTitle("O simbolo perdido");
			for (Livros livro : list) {
				System.out.println("\n" + livro.toString() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
