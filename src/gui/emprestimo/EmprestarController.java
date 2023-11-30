package gui.emprestimo;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.controller.LivroController;
import main.controller.UsuarioController;
import model.entities.Livros;
import model.entities.Usuario;

public class EmprestarController implements Initializable {
	private List<String> listStringLivros = new ArrayList<String>();
	private Usuario usuario = new Usuario();
	private UsuarioController usuarioController = new UsuarioController();
	private LivroController livroController = new LivroController();

	@FXML
	private Button btnBusca;

	@FXML
	private Button btnEmprestar;

	@FXML
	private ComboBox<String> cBoxLivros;

	@FXML
	private TextField txtDevolucao;

	@FXML
	private TextField txtEmprestimo;

	@FXML
	private TextField txtBuscaUsuario;

	@FXML
	void onBtnEmprestarAction(ActionEvent event) {

	}

	@FXML
	void onBuscaUsuarioAction(ActionEvent event) {
		try {
			if (!this.txtBuscaUsuario.getText().toLowerCase().isEmpty()) {
				this.usuario = this.usuarioController.buscaUsuario(this.txtBuscaUsuario.getText());
				if (this.usuario == null) {
					this.txtBuscaUsuario.setStyle("-fx-border-color: red;");
				} else {
					this.txtBuscaUsuario.setStyle("-fx-border-color: lime;");
				}
			} else {
				Alerts.showAlert("Erro!", "Necessário inserir nome ou CPF!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.txtDevolucao.setText(this.validDataDevolucao(LocalDate.now()));
		this.txtEmprestimo.setText(this.ValidData(LocalDate.now()));
		List<Livros> listLivro = new ArrayList<Livros>();
		try {
			listLivro = this.livroController.listAll();
			for (Livros libre : listLivro) {
				this.listStringLivros.add(libre.getTitulo() + " " + libre.getAno());
			}
			ObservableList<String> itens = FXCollections.observableList(this.listStringLivros);
			cBoxLivros.setItems(itens);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String validDataDevolucao(LocalDate date) {
		int dia = date.getDayOfMonth();
		int mes = date.getMonthValue();
		int ano = date.getYear();
		int totalDiasDoMes = date.lengthOfMonth();
		int totalDiasDoAno = date.lengthOfYear();
		int diaDoAno = date.getDayOfYear();
		System.out.println(dia + "-" + mes + "-" + ano);
		if (dia - totalDiasDoMes == 0) {
			date = date.plusDays(7);
			System.out.println("Mês: " + date.getMonthValue() + "\nDia: " + date.getDayOfMonth());
			if (diaDoAno - totalDiasDoAno == 0) {
				date = date.plusYears(1);
				System.out.println("Mês: " + date.getMonthValue() + "\nDia: " + date.getDayOfMonth());
			}
		}
		String formattedDate = this.ValidData(date);
		System.out.println(formattedDate);
		return formattedDate;
	}

	public String ValidData(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(formatter);
		System.out.println(formattedDate);
		return formattedDate;
	}
}
