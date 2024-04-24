package model.File;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Memory.MemoryStorage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class FileDefragger {

    private static final String AES_KEY_STRING = "3js9db5uf0sgud4j";
    private static final String FILE_EXTENSION = ".cbalg";
    private static JFrame frame;
    
    public static void openFile(MemoryStorage memory, JFrame parentFrame) {
    	
    	frame = parentFrame;
    	
        // Mostra il selettore di file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select .cbalg File to Decrypt");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CoffeeBuilderAlgorithm", "cbalg");
        fileChooser.setFileFilter(filter);
        
        int userSelection = fileChooser.showOpenDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            
            if (isOnCVolume(inputFile)) {
                JOptionPane.showMessageDialog(frame, "Please select a directory outside of the C volume.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            File decryptedFile = decryptAndSave(inputFile);
            if(decryptedFile==null) {
            	JOptionPane.showMessageDialog(frame, "There has been an error during the file opening.", "Error", JOptionPane.ERROR_MESSAGE);
            	return;
            }else {
            	
            	ArrayList<String[]> arrayList = new ArrayList<>();

                try (BufferedReader br = new BufferedReader(new FileReader(decryptedFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] elements = line.split(",");
                        arrayList.add(elements);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Stampare gli arraylist per verificare il risultato
                for (String[] array : arrayList) {
                    for (String element : array) {
                        System.out.print(element + " ");
                    }
                    System.out.println();
                }
            }
            
            
        }
    }

    private static boolean isOnCVolume(File directory) {
        // Ottieni il percorso del volume C
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Controlla se il percorso della directory inizia con il percorso del volume C
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }

	private static File decryptAndSave(File inputFile) {
        try {
            // Leggi il contenuto del file cifrato
            byte[] encryptedData = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()));
            
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            
            byte[] encryptedContent = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            
            // Decifra il contenuto
            byte[] decryptedData = decrypt(encryptedContent, AES_KEY_STRING.getBytes(StandardCharsets.UTF_8), iv);
            
            // Crea un file temporaneo con estensione .txt
            File tempFile = new File(inputFile.getParent(), "tempCBALG_decrypted.txt");
            // Salva il contenuto decifrato nel file temporaneo
            Files.write(tempFile.toPath(), decryptedData);
            
            System.out.println("Decrypted content saved to: " + tempFile.getAbsolutePath());
            
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	private static byte[] decrypt(byte[] encryptedData, byte[] key, byte[] iv) {
	    try {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
	        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); // Initialize IV
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec); // Use IV for decryption
	        return cipher.doFinal(encryptedData);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}