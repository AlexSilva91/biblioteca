package main.controller;

import java.util.ArrayList;
import java.util.List;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.entities.Livros;
import model.services.LivroService;

public class LivroController {

	private LivroService livroService = new LivroService();
	private Livros livro = new Livros();
	private List<Livros> listAllLivros = new ArrayList<Livros>();

	public boolean savarLivro(Livros livro) {
		boolean salvo = false;
		try {
			this.livro = this.getLivroFindById(livro.getIsbn());
			if (this.livro == null) {
				salvo = this.salveLivro(livro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salvo;
	}

	public boolean salveLivro(Livros book) {
		boolean salvo = false;
		book.setStatus(true);
		Long exemplar = book.getExemplar();
		book.setExemplar(exemplar + 1);
		if (this.livroService.saveLivro(book) != null) {
			salvo = true;
		}
		return salvo;
	}

	public List<Livros> searchFindTitle(String title) {
		List<Livros> listBooks = new ArrayList<Livros>();
		try {
			listBooks = livroService.listAllFindByTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBooks;
	}

	public Livros getLivroFindById(Long id) {
		try {
			this.livro = livroService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.livro;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Livros> seacherLivros(String arg) {
		List<Livros> listLivros = new ArrayList();
		try {
			if (this.validNumber(arg)) {
				listLivros.add(this.livroService.findById(Long.valueOf(arg)));
			}
			if (this.validLetras(arg)) {
				this.listAllLivros = this.listAll();
				if (this.listAllLivros != null) {
					for (Livros livro : this.listAllLivros) {
						if (arg.equals(livro.getAutor()) || arg.equals(livro.getTitulo())) {
							listLivros.add(livro);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listLivros;
	}

	public List<Livros> listAll() {
		try {
			this.listAllLivros = this.livroService.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listAllLivros;
	}

	public boolean updateBook(Livros livro) {
		boolean updateBook = false;
		try {
			if (this.livroService.updateLivro(livro) != null) {
				updateBook = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateBook;
	}

	@SuppressWarnings("unused")
	public List<Integer> generateNumberList(int start, int end) {
		List<Integer> numberList = new ArrayList<>();

		for (int i = start; i <= end; i++) {
			numberList.add(i);
		}
		return numberList;
	}

	public boolean validNumber(String arg) {
		for (char caracteres : arg.toCharArray()) {
			if (Character.isDigit(caracteres)) {
				return true;
			}
		}
		return false;
	}

	public boolean validLetras(String arg) {
		for (char caracteres : arg.toCharArray()) {
			if (Character.isLetter(caracteres)) {
				return true;
			}
		}
		return false;
	}
}
