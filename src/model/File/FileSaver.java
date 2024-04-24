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

public class FileSaver {

	private static final byte[] AES_KEY = "3js9db5uf0sgud4j".getBytes(StandardCharsets.UTF_8);
	
    public static void saveToFile(ArrayList<AlgorithmComponent> algorithmComponents) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Algorithm Components");
        
        // Imposta il filtro per mostrare solo file con estensione .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CoffeeBuilderAlgorithm", "cbalg");
        fileChooser.setFileFilter(filter);
        
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Controlla se il percorso selezionato è all'interno del volume C
            if (isOnCVolume(selectedFile)) {
                JOptionPane.showMessageDialog(null, "Please select a directory outside of the C volume.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aggiunge l'estensione .txt se non è già presente
            if (!selectedFile.getName().toLowerCase().endsWith(".cbalg")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".cbalg");
            }
            
            // Salva i dati in un file temporaneo
            File tempFile = new File(selectedFile.getParent(), "tempCBALG_not_crypted.txt");
            saveToFile(algorithmComponents, tempFile);

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

    private static void saveToFile(ArrayList<AlgorithmComponent> algorithmComponents, File fileToSave) {
        try (PrintWriter writer = new PrintWriter(fileToSave)) {
            for (AlgorithmComponent component : algorithmComponents) {
            	String finalString = "";
                // Traduzione del tipo di componente
                String componentType = translateComponentType(component);
                // Traduzione dei componenti successivi
                String nextComponent1 = translateComponentType(component.getNextComponent1());
                String nextComponent2 = translateComponentType(component.getNextComponent2());
                // Scrivi la riga nel file utilizzando la virgola come delimitatore
                finalString += componentType + "," + nextComponent1 + "," + nextComponent2; 
                
                if 		  (component instanceof ComponentAssign) {
                	ComponentAssign aus = (ComponentAssign) component;
                	finalString += "," + aus.getVariableName() + "," + aus.getValueString();
                } else if (component instanceof ComponentDeclaration) {
                	ComponentDeclaration aus = (ComponentDeclaration) component;
                	finalString += "," + aus.getVariableType() + "," + aus.getVariableName();
                } else if (component instanceof ComponentOperation) {
                	ComponentOperation aus = (ComponentOperation) component;
                	finalString += "," + aus.getVariableName() + "," + aus.getVariableFirstOperandName() + "," + aus.getVariableSecondOperandName();
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

    private static boolean isOnCVolume(File directory) {
        // Ottieni il percorso del volume C
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Controlla se il percorso della directory inizia con il percorso del volume C
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }
    
    private static void encrypt(InputStream inputStream, OutputStream outputStream) throws IOException {
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

    private static String translateComponentType(AlgorithmComponent component) {
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