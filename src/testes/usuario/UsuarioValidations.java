package testes.usuario;

import main.controller.UsuarioController;

public class UsuarioValidations {

	public static void main(String[] args) {
		usuarioExiste();
		
	}

	public static void usuarioExiste() {
		boolean existe;
		try {
			UsuarioController validation = new UsuarioController();
			existe = validation.usuarioExiste(123456);
			System.out.println(existe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}