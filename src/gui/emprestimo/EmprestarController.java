package gui.emprestimo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class EmprestarController {
	@FXML
	private Button btnEmprestar;

	@FXML
	private ComboBox<?> cBoxLivros;

	@FXML
	private ComboBox<?> cBoxUsuario;

	@FXML
	private DatePicker dateDevolucao;

	@FXML
	private DatePicker dateEmprestimo;

	@FXML
	void onBtnEmprestarAction(ActionEvent event) {

	}	
}
