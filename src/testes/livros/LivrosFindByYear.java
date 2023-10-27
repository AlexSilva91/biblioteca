package testes.livros;

import java.util.List;

import model.entities.Livros;
import model.services.LivroService;

public class LivrosFindByYear {
	public static void main(String[] args) {
		LivroService livroService = new LivroService();

		try {
			List<Livros> list = livroService.listAllFindByYear(2000);
			for (Livros livro : list) {
				System.out.println("\n" + livro.toString() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
