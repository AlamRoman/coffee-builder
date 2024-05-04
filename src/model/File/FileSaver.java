package model.File;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.FlowChartController;
import model.Components.*;
import model.Memory.MemoryStorage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * <p>
 * This class provides methods for saving algorithm components to a file. It includes functionality for selecting a file location, encrypting the data, and writing it to the file.
 * </p>
 * <p>
 * The main method {@link FileSaver#saveToFile(JFrame)} allows the user to select a file location using a file chooser dialog. It checks if the selected file is on the C volume to prevent potential security risks
 * and to avoid permission issues.
 * If the file location is valid, it saves the algorithm components to a temporary file, encrypts the data, and writes the encrypted content to the selected file.
 * </p>
 * <p>
 * The {@link FileSaver#encrypt(InputStream, OutputStream)} method takes an input stream containing data to be encrypted and an output stream where the encrypted data will be written.
 * It initializes a cipher in encryption mode with a secret key generated using the AES algorithm and an Initialization Vector (IV), and then encrypts the data read from the input stream in chunks.
 * </p>
 * <p>
 * The class also includes helper methods for translating algorithm component types to their corresponding code representations and checking if a file is located on the C volume.
 * </p>
 */
public class FileSaver {

	private static final byte[] AES_KEY = "3js9db5uf0sgud4j".getBytes(StandardCharsets.UTF_8);
	private static JFrame frame;
	
	/**
	 * <p>
	 * Opens a file chooser dialog for the user to select a .cbalg file to save algorithm components.
	 * </p>
	 * 
	 * @param parentFrame The parent JFrame to which the file chooser dialog is attached.
	 */
    public static void saveToFile(JFrame parentFrame) {
    	
    	frame = parentFrame;
    	
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Algorithm Components");
        
        // Imposta il filtro per mostrare solo file con estensione .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CoffeeBuilderAlgorithm", "cbalg");
        fileChooser.setFileFilter(filter);
        
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Controlla se il percorso selezionato è all'interno del volume C
            if (isOnCVolume(selectedFile)) {
                JOptionPane.showMessageDialog(frame, "Please select a directory outside of the C volume.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aggiunge l'estensione .txt se non è già presente
            if (!selectedFile.getName().toLowerCase().endsWith(".cbalg")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".cbalg");
            }
            
            // Salva i dati in un file temporaneo
            File tempFile = new File(selectedFile.getParent(), "tempCBALG_not_crypted.txt");
            saveToFile(MemoryStorage.getInstance().getComponents(), tempFile);

            // Cripta i dati nel file temporaneo e scrivi i dati criptati nel file finale
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(tempFile));
                 OutputStream out = new BufferedOutputStream(new FileOutputStream(selectedFile))) {
                // Cripta e scrivi i dati utilizzando AES
                encrypt(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(tempFile.getAbsolutePath());
            // Elimina il file temporaneo
            tempFile.delete();
        }
    }

    /**
     * <p>
     * Saves the provided list of algorithm components to a file.
     * </p>
     * 
     * @param algorithmComponents The list of algorithm components to save.
     * @param fileToSave The file to which the components will be saved.
     */
    public static void saveToFile(ArrayList<AlgorithmComponent> algorithmComponents, File fileToSave) {
        try (PrintWriter writer = new PrintWriter(fileToSave)) {
            for (AlgorithmComponent component : algorithmComponents) {
            	String finalString = "";
                // Traduzione del tipo di componente
                String componentType = translateComponentType(component);
                // Traduzione dei componenti successivi
                String nextComponent1 = translateComponentType(component.getNextComponent1());
                int nextComponent1idx = MemoryStorage.getInstance().getIndexOf(component.getNextComponent1());
                String nextComponent2 = translateComponentType(component.getNextComponent2());
                int nextComponent2idx = MemoryStorage.getInstance().getIndexOf(component.getNextComponent2());
                // Scrivi la riga nel file utilizzando la virgola come delimitatore
                finalString += componentType + "," + nextComponent1 + "," + nextComponent1idx + "," + nextComponent2 + "," + nextComponent2idx; 
                
                if 		  (component instanceof ComponentAssign) {
                	ComponentAssign aus = (ComponentAssign) component;
                	finalString += "," + aus.getVariableName() + "," + aus.getValueString();
                } else if (component instanceof ComponentDeclaration) {
                	ComponentDeclaration aus = (ComponentDeclaration) component;
                	finalString += "," + aus.getVariableType() + "," + aus.getVariableName();
                } else if (component instanceof ComponentOperation) {
                	ComponentOperation aus = (ComponentOperation) component;
                	finalString += "," + aus.getVariableName() + "," + aus.getVariableFirstOperandName() + "," + aus.getVariableSecondOperandName() + "," + aus.getOperation();
                } else if (component instanceof ComponentIf) {
                	ComponentIf aus = (ComponentIf) component;
                	finalString += "," + aus.getTerm1() + "," + aus.getTerm2() + "," + aus.getOperator();
                } else if (component instanceof ComponentWhile) {
                	ComponentWhile aus = (ComponentWhile) component;
                	finalString += "," + aus.getTerm1() + "," + aus.getTerm2() + "," + aus.getOperator();
                } else if (component instanceof ComponentComment) {
                	ComponentComment aus = (ComponentComment) component;
                	finalString += "," + aus.getCommentString();
                } else if (component instanceof ComponentInput) {
                	ComponentInput aus = (ComponentInput) component;
                	finalString += "," + aus.getNomeVariabile();
                } else if (component instanceof ComponentOutput) {
                	ComponentOutput aus = (ComponentOutput) component;
                	finalString += "," + aus.getRawOutputString();
                }
                writer.println(finalString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Checks if the selected file is located on the C volume (the system directory).
     * </p>
     * 
     * @param directory The selected file.
     * @return {@link Boolean} True if the file is on the C volume, false otherwise.
     */
    public static boolean isOnCVolume(File directory) {
        // Ottieni il percorso del volume C
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Controlla se il percorso della directory inizia con il percorso del volume C
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }
    
    /**
     * <p>
     * Encrypts the input stream data using AES (Advanced Encryption Standard) encryption algorithm and writes the encrypted data to the output stream.
     * </p>
     * <p>
     * This method takes an input stream containing data to be encrypted and an output stream where the encrypted data will be written. It creates a secret key using the AES algorithm,
     * initializes a cipher in encryption mode with the secret key and an Initialization Vector (IV), and then reads the data from the input stream in chunks, encrypts each chunk using the cipher,
 	 * and writes the encrypted data to the output stream.
     * </p>
     * @param inputStream The input stream containing data to be encrypted.
     * @param outputStream The output stream to write the encrypted data.
     * @implNote The <b>IV</b> is an "extra key" used together with the secret key during encryption and it ensures that even equal or similar data produces different encrypted results, enhancing security.
	 * @implNote <b>CBC</b> (Cipher Block Chaining) mode encrypts each block of plaintext data separately and it combines the result with the previous block before encrypting the next one.
     * @throws IOException If an I/O error occurs during encryption.
     */
    public static void encrypt(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            // Crea la chiave segreta
            SecretKeySpec secretKey = new SecretKeySpec(AES_KEY, "AES");

            // Crea un cipher AES
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Genera un vettore di inizializzazione (IV)
            byte[] ivBytes = new byte[cipher.getBlockSize()];
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            // Inizializza il cipher in modalità di crittografia
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            // Scrive l'IV nel file di output
            outputStream.write(iv.getIV());

            // Crea il buffer per la lettura dei dati in input
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Cripta i dati e li scrive nel file di output
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                if (encryptedBytes != null) {
                    outputStream.write(encryptedBytes);
                }
            }

            // Finalizza la crittografia
            byte[] finalBytes = cipher.doFinal();
            if (finalBytes != null) {
                outputStream.write(finalBytes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Translates an algorithm component type to its corresponding code representation.
     * </p>
     * 
     * @param component The algorithm component to translate.
     * @return {@link String} The code representation of the component type.
     */
    public static String translateComponentType(AlgorithmComponent component) {
        // Traduzione dei tipi di componente
        if (component instanceof ComponentStart) {
            return "CS";
        } else if (component instanceof ComponentAssign) {
            return "CA";
        } else if (component instanceof ComponentDeclaration) {
            return "CD";
        } else if (component instanceof ComponentOperation) {
            return "CO";
        } else if (component instanceof ComponentIf) {
            return "CIF";
        } else if (component instanceof ComponentWhile) {
            return "CW";
        } else if (component instanceof ComponentElse) {
            return "CE";
        } else if (component instanceof ComponentAdd) {
            return "CADD";
        } else if (component instanceof ComponentComment) {
            return "CCOM";
        } else if (component instanceof ComponentInput) {
            return "CI";
        } else if (component instanceof ComponentOutput) {
            return "COUT";
        } else if (component instanceof ComponentEnd) {
            return "CEND";
        } else {
            return "null"; // Gestione di altri tipi di componenti se necessario
        }
    }
}