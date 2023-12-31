package testes.endereco;

import model.entities.Endereco;
import model.entities.Usuario;
import model.services.EnderecoService;
import model.services.UsuarioService;

public class SaveEndereco {

	public static void main(String[] args) {
		EnderecoService service = new EnderecoService();
		UsuarioService usuarioService = new UsuarioService();

		Endereco endereco = new Endereco();
		endereco.setBairro("Centro");
		endereco.setCidade("Exu");
		endereco.setComplemento("Casa");
		endereco.setNumero("142");
		endereco.setRua("Rua Maria F. Castro");
		try {
			Usuario usuario = usuarioService.findById(13166456490L);
			/**
			 * Primeiro salva o endereço depois atualiza o usuário
			 */
			endereco = service.saveEndereco(endereco);
			usuario = usuarioService.updateUser(usuario);
			System.out.println("\n" + usuario.toString());
			System.out.println("\n" + endereco.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
