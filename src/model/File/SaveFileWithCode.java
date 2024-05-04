package model.File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Exceptions;
import model.Line;
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

/**
 * <p>
 * This class provides functionality to translate the algorithm components into code in a specified programming language
 * and save the code to a .txt file. It allows users to select a file path where the code will be saved, along with the choice of programming language.
 * </p>
 */
public class SaveFileWithCode {
	
	private static JFrame frame;

	/**
     * <p>
     * Translates the algorithm components into code in the specified programming language and saves the code to a text file.
     * It displays a file chooser dialog to let the user select the file path for saving the code. If the selected file path is within the C volume,
     * it displays an error message.
     * </p>
     *
     * @param parent The parent JFrame to attach the file chooser dialog to.
     * @param language The programming language for which the code will be generated ("java", "python", or "PseudoCode").
     */
	public static void translateAndSaveCode(JFrame parent, String language) {
		
		frame = parent;
    	
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save with code");
        
        // Imposta il filtro per mostrare solo file con estensione .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File (.txt)", "txt");
        fileChooser.setFileFilter(filter);
        
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Controlla se il percorso selezionato è all'interno del volume C
            if (isOnCVolume(selectedFile)) {
                JOptionPane.showMessageDialog(frame, "Please select a directory outside of the C volume.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }
            
            saveToFile(MemoryStorage.getInstance().getComponents(), selectedFile, language);
            
        }
	}

	/**
     * <p>
     * Saves the translated code generated from the algorithm components to the specified file.
     * It iterates through each algorithm component, executing declarations if present, adjusting indentation levels based on component types,
     * printing code lines with appropriate indentation, and updating indentation levels for conditional components.
     * </p>
     *
     * @param components The list of algorithm components to be translated into code.
     * @param selectedFile The file where the translated code will be saved.
     * @param language The programming language for which the code will be generated ("java", "python" or "psudocode").
     */
	public static void saveToFile(ArrayList<AlgorithmComponent> components, File selectedFile, String language) {
		// TODO Auto-generated method stub
    	int indentation = 0;
    	try (PrintWriter writer = new PrintWriter(selectedFile)) {
            for (AlgorithmComponent component : components) {
            	if(component instanceof ComponentDeclaration) {
            		ComponentDeclaration aus = (ComponentDeclaration) component;
					try {
						aus.execute();
					} catch (Exceptions e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	if(component instanceof ComponentAdd || component instanceof ComponentElse) indentation--;
            	if(component instanceof ComponentEnd) indentation=0;
            	ArrayList<Line> lines = component.printCode(language);
            	for (Line line : lines) {
					writer.println(getIndentationString(indentation) + line.text);
				}
                if(component instanceof ComponentWhile || component instanceof ComponentIf || component instanceof ComponentElse) indentation++;
                if(component instanceof ComponentStart) {
                	switch(language) {
                		case "pseudocode":
                			indentation = 0;
                			break;
                		case "java":
                			indentation = 2;
                			break;
                		case "python":
                			indentation = 0;
                			break;
                		case "c":
                			indentation = 0;
                			break;
                	}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	MemoryStorage.getInstance().destroyVariables();
	}

    /**
     * <p>
     * Returns a string representing the indentation based on the specified level.
     * </p>
     *
     * @param indentation The level of indentation.
     * @return {@link String} A string representing the indentation.
     */
	public static String getIndentationString(int indentation) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indentation; i++) {
			sb.append("    ");
		}
		return sb.toString();
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
        // Ottieni il percorso del volume C
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Controlla se il percorso della directory inizia con il percorso del volume C
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }
	
}

