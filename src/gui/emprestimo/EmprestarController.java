package gui.emprestimo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EmprestarController {
    @FXML
    private Button btnBusca;

    @FXML
    private Button btnEmprestar;

    @FXML
    private ComboBox<?> cBoxLivros;

    @FXML
    private DatePicker dateDevolucao;

    @FXML
    private DatePicker dateEmprestimo;

    @FXML
    private TextField txtBuscaUsuario;

    @FXML
    void onBtnEmprestarAction(ActionEvent event) {

    }

    @FXML
    void onBuscaUsuarioAction(ActionEvent event) {

    }
}
