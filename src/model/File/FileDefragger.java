package model.File;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Components.AlgorithmComponent;
import model.Components.ComponentAdd;
import model.Components.ComponentAssign;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentElse;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;
import model.Memory.MemoryStorage;
import model.Memory.OperationType;
import model.Memory.RelationalOperators;
import model.Memory.VariableType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/**
 * <p>
 * This class provides methods for decrypting and reading algorithm components from a .cbalg file. It includes functionality for decrypting the file content, parsing the decrypted data,
 * and reconstructing the algorithm components for further processing.
 * </p>
 * <p>
 * The main method {@link FileDefragger#openFile(JFrame)} presents a file chooser dialog for the user to select a .cbalg file to decrypt. It checks if the selected file is on the C volume to prevent potential security risks.
 * If the file location is valid, it decrypts the file content, parses the decrypted data, and reconstructs the algorithm components from the parsed information.
 * </p>
 * <p>
 * The {@link FileDefragger#decryptAndSave(File)} method takes an encrypted .cbalg file as input, decrypts its content using the AES encryption algorithm, and saves the decrypted content to a temporary file.
 * It reads the encrypted data from the input file, separates the Initialization Vector (IV) and the encrypted content, decrypts the content using the provided secret key and IV,
 * and then saves the decrypted content to a temporary file. This method returns the temporary file containing the decrypted content.
 * </p>
 * <p>
 * The class also includes helper methods for processing and reconstructing the algorithm components from the decrypted data, as well as checking if a file is located on the C volume.
 * </p>
 */
public class FileDefragger {

    private static final String AES_KEY_STRING = "3js9db5uf0sgud4j";
    private static final String FILE_EXTENSION = ".cbalg";
    private static JFrame frame;
    
    /**
     * <p>
     * Opens a file chooser dialog for the user to select a .cbalg file to decrypt.
     * </p>
     * @param parentFrame The parent JFrame to which the file chooser dialog is attached to.
     */
    public static void openFile(JFrame parentFrame) {
    	
    	frame = parentFrame;
    	
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
                
                ArrayList<AlgorithmComponent> newArrayComponents = new ArrayList<AlgorithmComponent>(arrayList.size());
                for (int i = 0; i < arrayList.size(); i++) {
                    newArrayComponents.add(null);
                }
                
                for (int i = arrayList.size() - 1; i >= 0; i--) {
                	elaborateComponentInfoAndAdd(arrayList, i, newArrayComponents, MemoryStorage.getInstance());
                }
                
                System.out.println(newArrayComponents.toString());
                
                MemoryStorage.getInstance().setComponents(newArrayComponents);
            
            }
        }
    }

    // C, N1, NIDX1, N2, NIDX2, ATTR1, ATTR2, ATTR3, ATTR4
    /**
     * <p>
     * Elaborates the information of each component and adds it to the new array.
     * </p>
     * 
     * @param arrayList The array list containing all the component informations.
     * @param i The index of the current component being processed.
     * @param newArrayComponents The new array to store elaborated components.
     * @param instance The MemoryStorage instance shared in the program.
     */
    public static void elaborateComponentInfoAndAdd(ArrayList<String[]> arrayList, int i, ArrayList<AlgorithmComponent> newArrayComponents, MemoryStorage instance) {
		// TODO Auto-generated method stub
    	Integer N1_idx = null;
    	Integer N2_idx = 0;
    	
    	AlgorithmComponent aus = null;
    	
    	try {
    		N1_idx = Integer.parseInt(arrayList.get(i)[2]);
    		if(N1_idx==-1) N1_idx = 0;
    	} catch (Exception e) {
    		// TODO: handle exception
    		
    	}
    	
    	try {
    		N2_idx = Integer.parseInt(arrayList.get(i)[4]);
    		if(N2_idx==-1) N2_idx = 0;
    	} catch (Exception e) {
    		// TODO: handle exception
    	}
    	
    	switch (arrayList.get(i)[0]) {
			case "CS":
				ComponentStart temp_start = (ComponentStart) aus;
				temp_start = new ComponentStart(newArrayComponents.get(N1_idx));
				aus = temp_start;
				break;
			case "CA":
				ComponentAssign temp_assign = (ComponentAssign) aus;
				temp_assign = new ComponentAssign(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_assign.set(arrayList.get(i)[6], arrayList.get(i)[5]);
				aus = temp_assign;
				break;
			case "CD":
				ComponentDeclaration temp_declaration = (ComponentDeclaration) aus;
				temp_declaration = new ComponentDeclaration(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_declaration.set(getVariableType(arrayList.get(i)[5]), arrayList.get(i)[6]);
				aus = temp_declaration;
				break;
			case "CO":
				ComponentOperation temp_operation = (ComponentOperation) aus;
				temp_operation = new ComponentOperation(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_operation.set(arrayList.get(i)[5], arrayList.get(i)[6], arrayList.get(i)[7], getOperationType(arrayList.get(i)[8]));
				aus = temp_operation;
				break;
			case "CIF":
				ComponentIf temp_if = (ComponentIf) aus;
				temp_if = new ComponentIf(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_if.set(arrayList.get(i)[5], getRelationalOperator(arrayList.get(i)[6]), arrayList.get(i)[7]);
				aus = temp_if;
				break;
			case "CW":
				ComponentWhile temp_while = null;
				if(newArrayComponents.get(i) == null){
					temp_while = (ComponentWhile) aus;
					temp_while = new ComponentWhile(newArrayComponents.get(N2_idx), instance);
				}else {
					temp_while = (ComponentWhile) newArrayComponents.get(i);
					temp_while.set(arrayList.get(i)[5], getRelationalOperator(arrayList.get(i)[7]), arrayList.get(i)[6]);
//					temp_while.setNextComponent1(newArrayComponents.get(N1_idx));
					temp_while.setNextComponent2(newArrayComponents.get(N2_idx));
				}
				
				if(newArrayComponents.get(i+1) instanceof ComponentAdd) {
					temp_while.setNextComponent1(temp_while);
				}else {
					temp_while.setNextComponent1(newArrayComponents.get(i+1));
				}
				
				aus = temp_while;
//				aus = new ComponentWhile(newArrayComponents.get(N1_idx), instance);
				break;
			case "CE":
				ComponentElse temp_else = (ComponentElse) aus;
				temp_else = new ComponentElse(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				aus = temp_else;
				break;
			case "CADD":
				ComponentAdd temp_add = (ComponentAdd) aus;
				temp_add = new ComponentAdd(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				aus = temp_add;
				break;
			case "CCOM":
				ComponentComment temp_comment = (ComponentComment) aus;
				temp_comment = new ComponentComment(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_comment.set(arrayList.get(i)[5]);
				aus = temp_comment;
				break;
			case "CI":
				ComponentInput temp_input = (ComponentInput) aus;
				temp_input = new ComponentInput(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_input.set(arrayList.get(i)[5]);
				aus = temp_input;
				break;
			case "COUT":
				ComponentOutput temp_output = (ComponentOutput) aus;
				temp_output = new ComponentOutput(newArrayComponents.get(N1_idx), newArrayComponents.get(N2_idx), instance);
				temp_output.set(arrayList.get(i)[5]);
				aus = temp_output;
				break;
			case "CEND":
				ComponentEnd temp_end = (ComponentEnd) aus;
				temp_end = new ComponentEnd();
				aus = temp_end;
				break;
    	}
    	if(!arrayList.get(i)[0].equals("CEND") && aus != null) {
    		if(aus.getNextComponent1() == null  && i != N1_idx) {
    			ComponentWhile temp = new ComponentWhile(null, null);
    			newArrayComponents.set(N1_idx, temp);
    			aus.setNextComponent1(newArrayComponents.get(N1_idx));
    		}else if(aus.getNextComponent1() == null && i == N1_idx) {
    			aus.setNextComponent1(aus);
    		}    		
    	}
    	
    	newArrayComponents.set(i, aus);
		
	}

    /**
     * <p>
     * Gets the operation type from the string representation.
     * </p>
     * 
     * @param string The string representation of the operation type.
     * @return {@link OperationType} The corresponding OperationType enum value.
     */
    public static OperationType getOperationType(String string) {
		switch (string) {
		case "ADD": 
			return OperationType.ADD;
		case "SUB": 
			return OperationType.SUB;
		case "MUL": 
			return OperationType.MUL;
		case "DIV": 
			return OperationType.DIV;
		case "MOD": 
			return OperationType.MOD;
		}
		return null;
	}
	
	/**
	 * <p>
	 * Gets the variable type from the string representation.
	 * </p>
	 * 
	 * @param string The string representation of the variable type.
	 * @return {@link VariableType} The corresponding VariableType enum value.
	 */
    public static VariableType getVariableType(String string) {
		switch (string) {
		case "String": 
			return VariableType.String;
		case "Double": 
			return VariableType.Double;
		case "Integer": 
			return VariableType.Integer;
		default:
			return VariableType.String;
		}
	}
	/**
	 * <p>
	 * Gets the relational operator from the string representation.
	 * </p>
	 * 
	 * @param string The string representation of the relational operator.
	 * @return {@link RelationalOperators} The corresponding RelationalOperators enum value.
	 */
    public static RelationalOperators getRelationalOperator(String string) {
		// TODO Auto-generated method stub
		switch (string) {
			case "EQUAL_TO": {
				return RelationalOperators.EQUAL_TO;
			}
			case "GREATER_THAN": {
				return RelationalOperators.GREATER_THAN;
			}
			case "LESS_THAN": {
				return RelationalOperators.LESS_THAN;
			}
			case "NOT_EQUAL_TO": {
				return RelationalOperators.NOT_EQUAL_TO;
			}
			case "GREATER_THAN_EQUAL_TO": {
				return RelationalOperators.GREATER_THAN_EQUAL_TO;
			}
			case "NOT": {
				return RelationalOperators.NOT;
			}
			case "LESS_THAN_EQUAL_TO": {
				return RelationalOperators.LESS_THAN_EQUAL_TO;
			}
			default:
				return RelationalOperators.EQUAL_TO;
		}
	}

	/**
	 * <p>
	 * Checks if the selected file is located on the C volume (the system volume).
	 * </p>
	 * 
	 * @param directory The selected file.
	 * @return {@link Boolean} True if the file is on the C volume, false otherwise.
	 */
    public static boolean isOnCVolume(File directory) {
        // Gets the path of System volume
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Returns true if the selected file is in the system volume, false if its not
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }

	/**
	 * <p>
	 * Decrypts the content of a .cbalg file and saves it to a temporary file.
	 * </p>
	 * <p>
	 * This method takes an encrypted .cbalg file as input, decrypts its content using AES (Advanced Encryption Standard) encryption algorithm, and saves the decrypted content to a temporary file 
	 * It reads the encrypted data from the input file, separates the initialization vector (IV) and the encrypted content, decrypts the content using the provided secret key and IV, 
	 * and then saves the decrypted content to a temporary file. This method returns the temporary file containing the decrypted content.
	 * </p>
	 * @implNote The <b>IV</b> is an "extra key" used together with the secret key during encryption and it ensures that even equal or similar data produces different encrypted results, enhancing security.
	 * @param inputFile The encrypted .cbalg file to decrypt.
	 * @return {@link File} The decrypted temporary file.
	 */
    public static File decryptAndSave(File inputFile) {
        try {
        	
            // Read the encrypted data from file
            byte[] encryptedData = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()));
            
            // Save the iv from file
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            
            // Save only the content of the file
            byte[] encryptedContent = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            
            // Decrypt the content with provided iv and AES Key
            byte[] decryptedData = decrypt(encryptedContent, AES_KEY_STRING.getBytes(StandardCharsets.UTF_8), iv);
            
            // Create a temp file .txt
            File tempFile = new File(inputFile.getParent(), "tempCBALG_decrypted.txt");
            // Save the decrypted content in the temp file
            Files.write(tempFile.toPath(), decryptedData);
            
            System.out.println("Decrypted content saved to: " + tempFile.getAbsolutePath());
            
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * <p>
	 * Decrypts the provided encrypted data using AES (Advanced Encryption Standard) encryption algorithm.
	 * </p>
	 * <p>
	 * This method takes encrypted data, a secret key, and an initialization vector (IV) as input parameters. 
	 * It initializes a Cipher object with the AES encryption algorithm and mode of operation (CBC) using the provided key and IV. 
	 * Then, it decrypts the encrypted data using the initialized Cipher object and returns the decrypted data.
	 * </p>
	 * @param encryptedData The encrypted data to decrypt.
	 * @param key The secret key used for decryption.
	 * @param iv The initialization vector used for decryption.
	 * @implNote The <b>IV</b> is an "extra key" used together with the secret key during encryption and it ensures that even equal or similar data produces different encrypted results, enhancing security.
	 * @implNote <b>CBC</b> (Cipher Block Chaining) mode encrypts each block of plaintext data separately and it combines the result with the previous block before encrypting the next one.
	 * @return {@link Byte}[] The decrypted data.
	 */
    public static byte[] decrypt(byte[] encryptedData, byte[] key, byte[] iv) {
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