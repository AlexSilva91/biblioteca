package testes.usuario;

import main.controller.UsuarioController;
import model.entities.Usuario;
import model.services.UsuarioService;

public class UpdateUser {

	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		Usuario user = new Usuario();
		UsuarioController usuarioValidation = new UsuarioController();
		try {
			Usuario usuario = service.findById(13166456490L);
			usuario.setStatus(false);
			usuario.setNome("alex");
			user = service.updateUser(usuario);
			user = usuarioValidation.buscaUsuario("ana maria");
			System.out.println("\n" + user.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
