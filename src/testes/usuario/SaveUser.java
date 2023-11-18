package testes.usuario;

import model.entities.Usuario;
import model.services.UsuarioService;

public class SaveUser {

	public static void main(String[] args) {

		Usuario usuario = new Usuario();
		UsuarioService usuarioService = new UsuarioService();
		usuario.setNome("Alex");
		usuario.setCpf(13166456490L);
		usuario.setContato(87981469865L);
		usuario.setStatus(true);
		try {
			usuario = usuarioService.saveUsuario(usuario);
			System.out.println("\n" + usuario.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
