package testes.usuario;

import java.awt.Desktop;
import java.net.URL;

public class Teste {

	public static void main(String[] args) {
		String url = "https://github.com/AlexSilva91/biblioteca";
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
