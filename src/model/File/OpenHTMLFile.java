package model.File;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenHTMLFile {
	
	private String pathToFile;
	
	public OpenHTMLFile(String s) {
		this.pathToFile = s;
		openHTMLFile();
	}

	public void openHTMLFile() {
        // Otteniamo il percorso assoluto del file HTML
        String filePath = System.getProperty("user.dir") + File.separator + pathToFile;
        
        try {
            File htmlFile = new File(filePath);
            if (htmlFile.exists()) {
                // Verifichiamo se Desktop è supportato
                if (Desktop.isDesktopSupported()) {
                    // Otteniamo l'istanza Desktop
                    Desktop desktop = Desktop.getDesktop();
                    // Apriamo il file HTML nel browser predefinito
                    desktop.open(htmlFile);
                } else {
                    System.out.println("Desktop not supported.");
                }
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
