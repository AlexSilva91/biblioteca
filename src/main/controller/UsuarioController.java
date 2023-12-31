package main.controller;

import javax.persistence.NoResultException;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.entities.Endereco;
import model.entities.Usuario;
import model.services.UsuarioService;

public class UsuarioController {
	private UsuarioService service = new UsuarioService();
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private EnderecoController enderecoValidation = new EnderecoController();

	public Boolean usuarioExiste(long id) {
		boolean existe = false;
		try {
			usuario = service.findById(id);
			/**
			 * Se id for diferente de nulo significa que o usuário já existe logo irá
			 * retornar true
			 */
			if (usuario != null) {
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existe;
	}

	public Usuario saveUser(Usuario usuarioArg, Endereco enderecoArg) {
		try {
			if (usuarioExiste(usuarioArg.getCpf())) {
				Alerts.showAlert("Erro!", "Usuário existente!", null, AlertType.ERROR);
			} else {
				usuarioArg.setStatus(true);
				/**
				 * checa se o endereço não é nulo caso não seja nulo irá salvá-lo linkando com o
				 * usuário
				 */
				service.saveUsuario(usuarioArg);
				enderecoValidation.saveEndereco(enderecoArg);
				Alerts.showAlert("Salvo!", "Salvo com sucesso!", null, AlertType.INFORMATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario buscaUsuario(String arg) throws NoResultException, NumberFormatException {
		/**
		 * valida se o que foi digitado são apenas números ou letras casa não retorna o
		 * erro.
		 */
		// if (this.isValidInputLetter(arg) || this.isValidInputNum(arg)) {
		try {
			if (this.validNumber(arg)) {
				this.usuario = service.findById(Long.parseLong(arg));
			}
			if (this.validLetras(arg)) {
				this.usuario = service.findByName(arg.toLowerCase());
			}
			// } else {
			// Alerts.showAlert("ERRO!", "Opção inválida!", null, AlertType.ERROR);
			// }
		} catch (Exception e) {
			Alerts.showAlert("ERRO!", "Usuário não encontrado!", null, AlertType.ERROR);
			e.printStackTrace();
		}
		return this.usuario;
	}

	public boolean atualizarUsuario(Usuario userArg, Endereco enderecoArg) {
		boolean salvo = false;
		try {
			if (usuarioExiste(userArg.getCpf())) {
				this.usuario = this.buscaUsuario(String.valueOf(userArg.getCpf()));
				if (this.usuario != null) {
					enderecoArg.setIdUser(this.usuario.getCpf());
					this.enderecoValidation.updateEndereco(enderecoArg);
				}
				this.usuario = userArg;
				this.service.updateUser(this.usuario);

				salvo = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salvo;
	}

	/**
	 * Valida se o que foi digitado foram numeros
	 */
	public boolean validNumber(String arg) {
		for (char caracteres : arg.toCharArray()) {
			if (Character.isDigit(caracteres)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Valida se o que foi digitado foram letras
	 */
	public boolean validLetras(String arg) {
		for (char caracteres : arg.toCharArray()) {
			if (Character.isLetter(caracteres)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Valida o dados inseridos pelo usuário e retorna verdadeira caso sejam válidos
	 */
	public boolean validLogin(String login, String senha) {
		boolean log = false;
		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
			log = true;
		}
		return log;
	}

}
