package main.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.NoResultException;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.entities.Endereco;
import model.entities.Usuario;
import model.services.EnderecoService;
import model.services.UsuarioService;

public class UsuarioValidation {
	private UsuarioService service = new UsuarioService();
	private List<Usuario> listUsuarios = new ArrayList<>();
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private EnderecoService enderecoService = new EnderecoService();

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
				if (enderecoArg != null) {
					enderecoService.saveEndereco(enderecoArg);
				}
				Alerts.showAlert("Salvo!", "Salvo com sucesso!", null, AlertType.INFORMATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario buscaUsuario(String arg) throws NoResultException {
		/**
		 * valida se o que foi digitado são apenas números ou letras casa não retorna o
		 * erro.
		 */
		if (this.isValidInputLetter(arg) || this.isValidInputNum(arg)) {
			if (this.validNumber(arg)) {
				this.usuario = service.findById(Long.parseLong(arg));
			} else if (this.validLetras(arg)) {
				this.usuario = service.findByName(arg.toLowerCase());
			}
		} else {
			Alerts.showAlert("ERRO!", "Opção inválida!", null, AlertType.ERROR);
		}
		return this.usuario;
	}

	public boolean atualizarUsuario(Usuario userArg) {
		boolean salvo = false;
		try {
			if (usuarioExiste(userArg.getCpf())) {
				this.usuario = this.buscaUsuario(String.valueOf(userArg.getCpf()));
				this.service.updateUser(usuario);
				salvo = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salvo;
	}

	public boolean atualizarUsuarioEndereco(Usuario userArg) {
		boolean salvo = false;
		try {
			if (usuarioExiste(userArg.getCpf())) {
				this.usuario = this.buscaUsuario(String.valueOf(userArg.getCpf()));
				this.endereco = this.enderecoService.findByidUser(usuario.getCpf());
				if (this.endereco != null) {
					this.endereco = userArg.getEndereco();
					this.endereco.setIdUser(userArg.getCpf());
					this.usuario.setEndereco(endereco);
					this.enderecoService.updateEndereco(endereco);
				}
				this.service.updateUser(usuario);
				salvo = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salvo;
	}

	public boolean atualizar(Usuario usuarioArg) {
		boolean salvo = false;
		try {
			if (usuarioArg.getEndereco() != null) {
				this.atualizarUsuarioEndereco(usuarioArg);
			} else {
				this.atualizarUsuario(usuarioArg);
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

	private boolean isValidInputLetter(String text) {
		// Verifica se a entrada contém apenas letras
		return Pattern.matches("[a-zA-Z]*", text);
	}

	private boolean isValidInputNum(String text) {
		// Verifica se a entrada contém apenas números
		return Pattern.matches("\\d*", text);
	}

	/**
	 * Valida o dados inseridos pelo usuário e retorna verdadeira caso sejam válidos
	 */
	public boolean validLogin(String cpf, String senha) {
		boolean log = false;
		if (usuarioExiste(Long.parseLong(cpf))) {
			if (usuario.getCpf() == Long.parseLong(cpf) && senha.equalsIgnoreCase("admin")) {
				log = true;
			}
		}
		return log;
	}

}
