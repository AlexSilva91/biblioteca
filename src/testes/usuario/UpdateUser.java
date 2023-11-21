package testes.usuario;

import model.entities.Usuario;
import model.services.UsuarioService;

public class UpdateUser {

	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		Usuario user = new Usuario();
		try {
			Usuario usuario = service.findById(13166456490L);
			usuario.setStatus(false);
			user = service.updateUser(usuario);
			System.out.println("\n" + user.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
