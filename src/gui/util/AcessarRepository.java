package gui.util;

import java.awt.Desktop;
import java.net.URL;

public class AcessarRepository implements Runnable{

	@Override
	public void run() {
		try {
			Desktop.getDesktop().browse(new URL("https://github.com/AlexSilva91/biblioteca").toURI());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
