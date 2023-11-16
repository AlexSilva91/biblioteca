package testes.usuario;

import model.entities.Usuario;
import model.services.UsuarioService;

public class SaveUser {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario();
		UsuarioService usuarioService = new UsuarioService();
		usuario.setNome("Alex");
		usuario.setCpf(123456);
		usuario.setContato(1234567891);
		usuario.setStatus(true);
		try {
			usuario = usuarioService.saveUsuario(usuario);
			System.out.println("\n" + usuario.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
