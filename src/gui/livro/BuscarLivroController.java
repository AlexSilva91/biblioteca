package gui.livro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import main.controller.LivroController;
import main.livro.BuscarLivro;
import model.entities.Livros;

public class BuscarLivroController {
	private Livros livro = new Livros();
	private LivroController livroController = new LivroController();

	@FXML
	private Button btnBusca;

	@FXML
	private Button btnVoltar;

	@FXML
	private CheckBox checkDisponivel;

	@FXML
	private CheckBox checkIndispobivel;

	@FXML
	private TextField txtTitle;

	@FXML
	private TextField txtAno;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtBusca;

	@FXML
	private TextField txtExexmplar;

	@FXML
	private TextField txtISBN;

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		List<Livros> listLivro = new ArrayList<Livros>();
		try {
			listLivro = this.livroController.seacherLivros(txtBusca.getText().toLowerCase());
			if (!listLivro.isEmpty()) {
				for (Livros livro : listLivro) {
					if (livro != null) {
						this.txtAno.setText(String.valueOf(livro.getAno()));
						this.txtAutor.setText(livro.getAutor().toLowerCase());
						this.txtExexmplar.setText(String.valueOf(livro.getExemplar()));
						this.txtISBN.setText(String.valueOf(livro.getIsbn()));
						this.txtTitle.setText(livro.getTitulo().toLowerCase());
						if (livro.getStatus()) {
							this.checkDisponivel.setSelected(true);
							this.checkDisponivel.setDisable(true);
							this.checkIndispobivel.setDisable(true);
						} else {
							this.checkDisponivel.setDisable(true);
							this.checkIndispobivel.setSelected(true);
							this.checkIndispobivel.setDisable(true);
						}
					}
				}
			} else {
				Alerts.showAlert("Erro!", "Livro não encontrado!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@FXML
	void onBtnVoltarAction(ActionEvent event) {
		BuscarLivro.getStage().close();
	}

}
