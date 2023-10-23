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
		endereco.setNumCasa("142");
		endereco.setRua("Rua Maria F. Castro");
		try {
			Usuario usuario = usuarioService.findById(123456);
			usuario.setEndereco(endereco);
			/**
			 * Primeiro salva o endereço depois atualiza o usuário
			 */
			endereco = service.saveEndereco(endereco);
			usuario = usuarioService.updateUser(usuario);
			System.out.println("\n" + usuario.toString());
			System.out.println("\n" + endereco.toString());
			System.out.println("\n" + usuario.getEndereco().getCidade().toUpperCase());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
