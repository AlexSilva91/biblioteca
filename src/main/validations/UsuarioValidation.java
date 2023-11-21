package main.validations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import connectionFactory.ConnectionFactory;
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
				Alerts.showAlert("Usuário já existe", "Usuário existente!", null, AlertType.ERROR);
			} else {
				/**
				 * checa se o endereço não é nulo caso não seja nulo irá salvá-lo linkando com o
				 * usuário
				 */
				if (enderecoArg != null) {
					this.endereco = enderecoService.saveEndereco(enderecoArg);
				}
				usuarioArg.setStatus(true);
				usuarioArg.setEndereco(enderecoArg);
				this.usuario = service.saveUsuario(usuarioArg);
				Alerts.showAlert("Salvo!", "", null, AlertType.INFORMATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario buscaUsuario(String arg) throws NoResultException {
		if (this.validNumber(arg)) {
			this.usuario = service.findById(Long.parseLong(arg));
		} else if (this.validLetras(arg)) {
			this.usuario = service.findByName(arg.toLowerCase());
		} else {
			Alerts.showAlert("ERRO!", "Opção inválida!", null, AlertType.ERROR);
		}
		return this.usuario;
	}

	public boolean atualizarUsuario(Usuario userArg) {
		boolean salvo = false;
		if (usuarioExiste(userArg.getCpf())) {
			this.usuario = this.buscaUsuario(String.valueOf(userArg.getCpf()));
			this.endereco = this.enderecoService.findByidUser(usuario.getCpf());
			System.out.println(endereco.toString());
			if (this.endereco != null) {
				this.endereco.setBairro(userArg.getEndereco().getBairro());
				this.endereco.setCidade(userArg.getEndereco().getCidade());
				this.endereco.setComplemento(userArg.getEndereco().getComplemento());
				this.endereco.setNumero(userArg.getEndereco().getNumero());
				this.endereco.setRua(userArg.getEndereco().getRua());
			}
			this.usuario.setEndereco(endereco);
			//this.enderecoService.updateEndereco(endereco);
			this.service.updateUser(usuario);
			salvo = true;
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
