package testes.usuario;

import main.validations.UsuarioValidation;
import model.entities.Endereco;
import model.entities.Usuario;
import model.services.EnderecoService;
import model.services.UsuarioService;

public class UpdateUser2 {

	public static void main(String[] args) {
		UsuarioValidation usuarioService = new UsuarioValidation();
		Usuario user = new Usuario();
		EnderecoService service = new EnderecoService();
		Endereco endereco = new Endereco();
		UsuarioService service2 = new UsuarioService();

		try {
			/* pesquisa o endereço por meio do id contido no usuário */
			user = usuarioService.buscaUsuario("alex");
			endereco = service.findByidUser(user.getCpf());
			System.out.println(user.toString());
			
			
			/* seta as alteções e salva no banco*/
			endereco.setCidade("maracanaú");
			user.setStatus(false);
			usuarioService.atualizarUsuario(user, endereco);

			
			user = usuarioService.buscaUsuario("alex");
			endereco = service.findByidUser(user.getCpf());
			System.out.println(user.toString());
			System.out.println("\nAcessando pelo endereço: " + endereco.getCidade());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
