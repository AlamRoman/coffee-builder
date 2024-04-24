package model.File;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class FileDefragger {

    private static final String AES_KEY_STRING = "3js9db5uf0sgud4j";
    private static final String FILE_EXTENSION = ".cbalg";

    public static void openFile() {
        // Mostra il selettore di file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select .cbalg File to Decrypt");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CoffeeBuilderAlgorithm", "cbalg");
        fileChooser.setFileFilter(filter);
        
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            
            if (isOnCVolume(inputFile)) {
                JOptionPane.showMessageDialog(null, "Please select a directory outside of the C volume.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            decryptAndSave(inputFile);            	
            
            
        }
    }

    private static boolean isOnCVolume(File directory) {
        // Ottieni il percorso del volume C
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Controlla se il percorso della directory inizia con il percorso del volume C
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }

	private static void decryptAndSave(File inputFile) {
        try {
            // Leggi il contenuto del file cifrato
            byte[] encryptedData = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()));
            
            // Decifra il contenuto
            byte[] decryptedData = decrypt(encryptedData, AES_KEY_STRING.getBytes(StandardCharsets.UTF_8));
            
            // Crea un file temporaneo con estensione .txt
            File tempFile = File.createTempFile("decrypted_", ".txt");
            // Salva il contenuto decifrato nel file temporaneo
            Files.write(tempFile.toPath(), decryptedData);
            
            System.out.println("Decrypted content saved to: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] decrypt(byte[] encryptedData, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}