package main.controller;

import java.util.ArrayList;
import java.util.List;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.entities.Livros;
import model.services.LivroService;

public class LivroController {

	private LivroService livroService = new LivroService();

	public boolean saveLivro(Livros livro) {
		boolean salvo = false;
		List<Livros> listBooks = new ArrayList<Livros>();
		try {
			listBooks = this.searchFindTitle(livro.getTitulo());
			if (listBooks != null) {
				for (Livros book : listBooks) {
					if (book.getTitulo().equals(livro.getTitulo()) && book.getAno() == livro.getAno()) {
						Long exemplar = book.getExemplar();
						book = livro;
						book.setStatus(true);
						book.setExemplar(exemplar + 1);
						if (this.updateBook(book)) {
							salvo = true;
						}
					} else {
						salvo = this.salveLivro(livro);
					}
				}
			} else {
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
			Alerts.showAlert("ERRO!", "Livros não encontrados!", null, AlertType.ERROR);
			e.printStackTrace();
		}
		return listBooks;
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
}
