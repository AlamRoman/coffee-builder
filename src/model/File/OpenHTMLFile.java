package model.File;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * This class provides functionality to open an HTML file in the default web browser.
 * It allows users to specify the path to the HTML file and during the initialization it attempts to open the HTML file in the default web browser.
 * </p>
 */
public class OpenHTMLFile {
	
	private String pathToFile;
	
	/**
     * <p>
     * The constructor or {@link OpenHTMLFile}, it creates an instance with the specified path to the HTML file and attempts to open it in the default web browser.
     * </p>
     *
     * @param s The path to the HTML file.
     */
	public OpenHTMLFile(String s) {
		this.pathToFile = s;
		openHTMLFile();
	}

	/**
     * <p>
     * Opens the HTML file specified by the path in the default web browser.
     * If the file exists, it retrieves the absolute path of the HTML file, and opens the file.
     * </p>
     */
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
