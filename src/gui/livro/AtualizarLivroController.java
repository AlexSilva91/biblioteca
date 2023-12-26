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
import main.livro.AtualizarLivro;
import model.entities.Livros;

public class AtualizarLivroController implements Initializable {
	private LivroController controller = new LivroController();
	private Livros livro = new Livros();

	@FXML
	private Button btnAtualizar;

	@FXML
	private Button btnBusca;

	@FXML
	private Button btnVoltar;

	@FXML
	private CheckBox checkDisponivel;

	@FXML
	private CheckBox checkIndispobivel;

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
	private TextField txtTitulo;

	@FXML
	void onBtnAtualizarAction(ActionEvent event) {
		try {
			if(validCampos()) {
				this.livro.setAno(Integer.valueOf(this.txtAno.getText()));
				this.livro.setAutor(this.txtAutor.getText().toLowerCase());
				this.livro.setTitulo(this.txtTitulo.getText().toLowerCase());
				this.livro.setExemplar(Long.parseLong(this.txtExexmplar.getText()));
				if (this.checkDisponivel.selectedProperty().getValue()) {
					this.livro.setStatus(true);
				}
				if (this.checkIndispobivel.selectedProperty().getValue()) {
					this.livro.setStatus(false);
				}
				if (this.controller.updateBook(this.livro)) {
					Alerts.showAlert("Atualizado!", "Dados atualizados!", null, AlertType.INFORMATION);
				} else {
					Alerts.showAlert("Erro!", "Impossível atualizar dados!", null, AlertType.ERROR);
				}
			}else {
				Alerts.showAlert("Erro!", "Impossível atualizar dados!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		List<Livros> listLivro = new ArrayList<Livros>();
		try {
			listLivro = this.controller.seacherLivros(txtBusca.getText().toLowerCase());
			if (!listLivro.isEmpty()) {
				for (Livros livro : listLivro) {
					if (livro != null) {
						this.txtAno.setText(String.valueOf(livro.getAno()));
						this.txtAutor.setText(livro.getAutor().toLowerCase());
						this.txtExexmplar.setText(String.valueOf(livro.getExemplar()));
						this.txtISBN.setText(String.valueOf(livro.getIsbn()));
						this.txtTitulo.setText(livro.getTitulo().toLowerCase());
						if (livro.getStatus()) {
							this.checkDisponivel.setSelected(true);
							this.checkDisponivel.setDisable(false);
							this.checkIndispobivel.setDisable(false);
						} else {
							this.checkDisponivel.setDisable(false);
							this.checkIndispobivel.setSelected(true);
							this.checkIndispobivel.setDisable(false);
						}
						this.livro = livro;
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
		AtualizarLivro.getStage().close();
	}

	private boolean validarTextFields(List<TextField> listEndereco) {
		boolean preenchido = false;
		int index = 0;
		for (TextField textField : listEndereco) {
			if (!textField.getText().trim().isEmpty()) {
				System.out.println("\n" + textField.getId());
			} else {
				index += 1;
			}
		}
		if (index <= 0) {
			preenchido = true;
		}
		return preenchido;
	}

	@SuppressWarnings("unused")
	private boolean validCampos() {
		boolean camposPreenchidos = false;
		List<TextField> textFildes = new ArrayList<TextField>();
		textFildes.add(this.txtAno);
		textFildes.add(this.txtAutor);
		textFildes.add(this.txtExexmplar);
		textFildes.add(this.txtISBN);
		textFildes.add(this.txtTitulo);
		if (validarTextFields(textFildes)) {
			camposPreenchidos = true;
		}
		return camposPreenchidos;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Adiciona ouvinte para o CheckBox 1
		this.checkDisponivel.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				// Se o CheckBox 1 for selecionado, desativa o CheckBox 2
				this.checkIndispobivel.setDisable(true);
			} else {
				// Se o CheckBox 1 for desselecionado, reativa o CheckBox 2
				this.checkIndispobivel.setDisable(false);
			}
		});

		// Adiciona ouvinte para o CheckBox 2
		this.checkIndispobivel.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				// Se o CheckBox 2 for selecionado, desativa o CheckBox 1
				this.checkDisponivel.setDisable(true);
			} else {
				// Se o CheckBox 2 for desselecionado, reativa o CheckBox 1
				this.checkDisponivel.setDisable(false);
			}
		});
	}

}
