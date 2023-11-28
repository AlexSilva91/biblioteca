package gui.livro;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Collation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import main.controller.LivroController;
import main.livro.CadastrarLivro;
import model.entities.Livros;

public class CadastrarLivroController implements Initializable {
	private LivroController livroController = new LivroController();
	private Livros livro = new Livros();
	private ObservableList<Integer> obsList;

	@FXML
	private ComboBox<Integer> boxAno;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtIsbn;

	@FXML
	private TextField txtTitulo;

	@FXML
	void onBtnCadastrarAction(ActionEvent event) {
		if (this.validaCampos()) {
			this.livro.setIsbn(Long.valueOf(txtIsbn.getText()));
			this.livro.setTitulo(txtTitulo.getText());
			this.livro.setAutor(txtAutor.getText());
			this.livro.setAno(boxAno.getValue());
			if (this.livroController.savarLivro(this.livro)) {
				Alerts.showAlert("Salvo!", "Livro cadastrado!", null, AlertType.INFORMATION);
			}else {
				Alerts.showAlert("Erro!", "ISBN já cadastrado!", null, AlertType.ERROR);
			}
		} else {
			Alerts.showAlert("Erro!", "Necessário preencher todos os campos!", null, AlertType.ERROR);
		}
	}

	@FXML
	void onBtnVoltarAction(ActionEvent event) {
		CadastrarLivro.getStage().close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldInterger(txtIsbn);
		Constraints.setTextFieldMaxLength(txtIsbn, 13);

		List<Integer> anosLaçamento = new ArrayList<Integer>();
		anosLaçamento = livroController.generateNumberList(1900, LocalDate.now().getYear());
		Collections.sort(anosLaçamento, Collections.reverseOrder());
		obsList = FXCollections.observableArrayList(anosLaçamento);
		boxAno.setItems(obsList);

	}

	protected boolean validaCampos() {
		boolean camposPreenchidos = false;
		List<TextField> listTextField = new ArrayList<TextField>();
		listTextField.add(this.txtAutor);
		listTextField.add(this.txtIsbn);
		listTextField.add(this.txtTitulo);
		TextField fieldData = new TextField();
		fieldData.setText(String.valueOf(boxAno.getValue()));
		listTextField.add(fieldData);
		if (this.validarTextFields(listTextField)) {
			camposPreenchidos = true;
		}
		return camposPreenchidos;
	}

	public void data() {

	}

	@SuppressWarnings("unused")
	private boolean validarTextFields(List<TextField> listLivros) {
		boolean preenchido = false;
		int index = 0;
		for (TextField textField : listLivros) {
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

	protected void setDadosLivro() {

	}

}
