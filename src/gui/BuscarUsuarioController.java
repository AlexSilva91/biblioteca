package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class BuscarUsuarioController {

	@FXML
	private CheckBox CheckAtivo;

	@FXML
	private CheckBox CheckInativo;

	@FXML
	private Button btnBuscar;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtComplemento;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtTelefone;

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		CheckAtivo.setSelected(true);
		CheckAtivo.setDisable(true);
		CheckInativo.setDisable(true);
		
	}
}
