package main.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.entities.Emprestimo;
import model.services.EmprestimoService;

public class EmprestimoControllerMain {
	private EmprestimoService emprestimoService = new EmprestimoService();
	private Emprestimo emprestimo = new Emprestimo();

	public void emprestarLivro(Emprestimo emprestimoArg) {
		try {
			this.emprestimo = emprestimoService.saveEmprestimo(emprestimoArg);
			if (this.emprestimo != null) {
				Alerts.showAlert("Salvo!", "Salvo com sucesso!", null, AlertType.INFORMATION);
			} else {
				Alerts.showAlert("Erro!", "Falha ao salvar!", null, AlertType.INFORMATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String validDataDevolucao(LocalDate date) {
		int dia = date.getDayOfMonth();
		int totalDiasDoMes = date.lengthOfMonth();
		int totalDiasDoAno = date.lengthOfYear();
		int diaDoAno = date.getDayOfYear();
		if (dia - totalDiasDoMes == 0) {
			date = date.plusDays(7);
			if (diaDoAno - totalDiasDoAno == 0) {
				date = date.plusYears(1);
			}
		} else {
			date = date.plusDays(7);
		}
		String formattedDate = EmprestimoControllerMain.ValidData(date);
		return formattedDate;
	}

	public static String ValidData(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(formatter);
		return formattedDate;
	}

	public static LocalDate convertStringEmLocalDate(String date) {
		// Defina um formato para a conversão
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Use o método parse para converter a string em um objeto LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}

	public static int convertStringAno(String arg) {
		String anoEncontrado = null;
		// Defina um padrão para encontrar números na string
		Pattern padrao = Pattern.compile("\\d+");

		// Crie um objeto Matcher para encontrar padrões na string
		Matcher matcher = padrao.matcher(arg);

		// Enquanto houver correspondências, imprima os números encontrados
		while (matcher.find()) {
			anoEncontrado = matcher.group();
		}
		return Integer.valueOf(anoEncontrado);
	}
}
