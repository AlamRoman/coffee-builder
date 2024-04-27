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

public class SaveFileWithCode {
	
	private static JFrame frame;

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

	
    private static void saveToFile(ArrayList<AlgorithmComponent> components, File selectedFile, String language) {
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

	private static String getIndentationString(int indentation) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indentation; i++) {
			sb.append("    ");
		}
		return sb.toString();
	}


	private static boolean isOnCVolume(File directory) {
        // Ottieni il percorso del volume C
        String cVolumePath = System.getenv("SystemDrive") + "\\";
        // Controlla se il percorso della directory inizia con il percorso del volume C
        return directory.getAbsolutePath().startsWith(cVolumePath);
    }
	
}

