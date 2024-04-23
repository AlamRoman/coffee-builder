package controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.AlgorithmExecuter;
import model.Buffer;
import model.DebuggerConsole;
import model.Exceptions;
import model.Timer;
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
import model.Components.ComponentWhile;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import model.Memory.VariableType;
import view.FlowChartContentPanel;
import view.editComponents.AddComponent;
import view.editComponents.EditAssign;
import view.editComponents.EditAssign.ValuesAssignComponent;
import view.editComponents.EditComment;
import view.editComponents.EditDeclaration;
import view.editComponents.EditDeclaration.ValuesDeclarationComponent;
import view.editComponents.EditIf;
import view.editComponents.EditIf.ValuesIfComponent;
import view.editComponents.EditInput;
import view.editComponents.EditOperation;
import view.editComponents.EditOperation.ValuesOperationComponent;
import view.editComponents.EditOutput;
import view.editComponents.EditWhile;
import view.editComponents.EditWhile.ValuesWhileComponent;
import view.flowChartComponents.FlowChartPanel;
import view.flowChartComponents.OvalPanel;
import view.flowChartComponents.ParallelogramPanel;
import view.flowChartComponents.RectanglePanel;
import view.flowChartComponents.RhombusPanel;

public class FlowChartController extends Controller {
	
	private static final String referenceType = "FC-CONTROLLER";
	private FlowChartContentPanel panel;
	private FlowChartPanel FCPanel;
	
	public FlowChartController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, Buffer BUFFER, FlowChartContentPanel panel, MemoryStorage MEMORY) {
		super(ALGORITHM_EXECUTER, TIMER, BUFFER, MEMORY);
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.panel.setControllerAttribute(this);
		panel.updatePane(super.getMemory().getComponents());
//		System.out.println(this.panel);
//		System.out.println(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton button = (JButton) source;
			FCPanel = (FlowChartPanel) button.getParent();
		}
//MOSTRA IL CMD--------------------------------------------
		System.out.println(e.getActionCommand());
		
//MOSTRA DA DOVE PROVIENE IL CMD---------------------------
//		System.out.println(e.getSource());
		
//MOSTRA IL PANNELLO CHE CONTIENE IL BOTTONE PREMUTO-------
		System.out.println(FCPanel);
		
		if(MemoryStorage.getInstance().isOnGoing()==false) {
			
			switch (e.getActionCommand()) {
			case "ADD_COMPONENT": {
				AlgorithmComponent newComponent = null;
				AlgorithmComponent previousAC = FCPanel.associatedComponent;
				Component s = (Component) e.getSource();
				Point buttonLocation = s.getLocationOnScreen();
				int buttonX = (int) buttonLocation.getX();
				int buttonY = (int) buttonLocation.getY();
//			System.err.println(FCPanel.getParent().getParent().getParent().getParent().getParent().getParent().getParent());
				AddComponent addComp = new AddComponent((JFrame)FCPanel.getParent().getParent().getParent().getParent().getParent().getParent().getParent());
				addComp.setLocation(buttonX + 40, buttonY + 20);
				String compName = addComp.showAddWindow();
				if(compName != null) {
					System.out.println(compName);
					AlgorithmComponent c = FCPanel.associatedComponent;
					int index = MemoryStorage.getInstance().getIndexOf(c);
					
					if(index==-1) {
						DebuggerConsole.getInstance().printDefaultErrorLog(referenceType, "The component (" + c + ") is not in the array of components");
					}else {
						switch(compName) {
						case "Assign":
							newComponent = new ComponentAssign(null, null, super.memory);
							break;
						case "Declaration":
							newComponent = new ComponentDeclaration(null, null, super.memory);
							break;
						case "Operation":
							newComponent = new ComponentOperation(null, null, super.memory);
							break;
						case "Input":
							newComponent = new ComponentInput(null, null, super.memory);
							break;
						case "Output":
							newComponent = new ComponentOutput(null, null, super.memory);
							break;
						case "If":
							newComponent = new ComponentIf(null, null, super.memory);
							break;
						case "While":
							newComponent = new ComponentWhile(null, super.memory);
							break;
						case "Comment":
							newComponent = new ComponentComment(null, null, super.memory);
							break;
						}
						switch (compName) {
						case "If": {
							ComponentElse CE = new ComponentElse(null, null, null);
							ComponentAdd ADD = new ComponentAdd(null, null, null);

							if(previousAC instanceof ComponentWhile) {
								previousAC = (ComponentWhile) previousAC;
								ADD.setNextComponent1(previousAC);
								MemoryStorage.getInstance().addComponent(new AlgorithmComponent[]{
										newComponent, 
										CE, 
										ADD
								}, index+1, true);
							}else {
								MemoryStorage.getInstance().addComponent(new AlgorithmComponent[]{
										newComponent, 
										CE, 
										ADD
								}, index+1, false);								
							}
							break;
						}
						case "While": {
							MemoryStorage.getInstance().addComponent(new AlgorithmComponent[]{
									newComponent,
									new ComponentAdd(null, null, null)
							}, index+1, false);
							break;
						}
						default:
							MemoryStorage.getInstance().addComponent(newComponent, index+1);
						}
						panel.updatePane(MemoryStorage.getInstance().getComponents());
					}
				}
				
				break;
			}
			}
		}
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(MemoryStorage.getInstance().isOnGoing()==false) {
			System.out.println(e);
			Object source = e.getSource();
			if(e.getButton() == MouseEvent.BUTTON1) {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Left mouse button got clicked");
				if(source instanceof OvalPanel || source instanceof ParallelogramPanel || source instanceof RectanglePanel || source instanceof RhombusPanel) {
					FlowChartPanel panel = (FlowChartPanel) source;
					AlgorithmComponent ac = panel.associatedComponent;
					JFrame frame = (JFrame) panel.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
					
					if(ac instanceof ComponentAssign) {
						//Edit assign
						
						ComponentAssign comp = (ComponentAssign) ac;
						ValuesAssignComponent values = new ValuesAssignComponent(comp.getVariableName(), comp.getValueString());
						EditAssign edit = new EditAssign(values, frame);
						
						ValuesAssignComponent result = edit.showEditWindow();

						if (result != null) {
							comp.set(result.getValue(), result.getFinalVarName());
						}
						
					}
					else if(ac instanceof ComponentComment) {
						//edit comment
					}
					else if(ac instanceof ComponentDeclaration) {
						ComponentDeclaration comp = (ComponentDeclaration) ac;
						ValuesDeclarationComponent values = new ValuesDeclarationComponent(comp.getVariableName(), comp.getVariableType());
						
						EditDeclaration edit = new EditDeclaration(values, frame);
						
						ValuesDeclarationComponent result = edit.showEditWindow();
						
						if (result != null) {
							comp.set(result.getVarType(), result.getVarName());
						}
						
					}
					else if(ac instanceof ComponentIf) {
						//Edit if
						
						ComponentIf comp = (ComponentIf) ac;
						ValuesIfComponent values = new ValuesIfComponent(comp.getTerm1(), comp.getTerm1(), comp.getOperator());
						EditIf edit = new EditIf(values, frame);
						
						ValuesIfComponent result = edit.showEditWindow();
						
						if (result != null) {
							comp.set(result.getTerm1(), result.getOperator(), result.getTerm2());
						}
						
					}
					else if(ac instanceof ComponentInput) {
						//edit input
					}
					else if(ac instanceof ComponentOperation) {
						//edit operation
						
						ComponentOperation comp = (ComponentOperation) ac;
						ValuesOperationComponent values = new ValuesOperationComponent(comp.getVariableName(),comp.getVariableFirstOperandName(), comp.getVariableSecondOperandName(), comp.getOperation());
						EditOperation edit = new EditOperation(values, frame);
						
						ValuesOperationComponent result = edit.showEditWindow();
						
						if (result != null) {
							comp.set(result.getFinalVar(), result.getVar1(), result.getVar2(), result.getOperation());
						}
						
					}
					else if(ac instanceof ComponentOutput) {
						//edit comment
						ComponentOutput comp = (ComponentOutput) ac;
						EditOutput edit = new EditOutput(comp.getRawOutputString(), frame);
						
						String newOutputText = edit.showEditWindow();
						
						if (newOutputText != null) {
							comp.setRawOutPutString(newOutputText);
						}
					}
					else if(ac instanceof ComponentWhile) {
						//edit while
						
						ComponentWhile comp = (ComponentWhile) ac;
						ValuesWhileComponent values = new ValuesWhileComponent(comp.getTerm1(), comp.getTerm2(), comp.getOperator());
						EditWhile edit = new EditWhile(values, frame);
						
						ValuesWhileComponent result = edit.showEditWindow();
						
						if (result != null) {
							comp.set(result.getTerm1(), result.getOperator(), result.getTerm2());
						}
						
					}
					
					FlowChartPanel sourcePanel = (FlowChartPanel) source;
					
					sourcePanel.updatePrint();
					//frame.revalidate();
					frame.repaint();
				}
//			System.out.println(panel.getParent().getParent().getParent().getParent().getParent().getParent());
				
				
			}else if(e.getButton() == MouseEvent.BUTTON3) {
				
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Right mouse button got clicked");
				if(source instanceof OvalPanel || source instanceof ParallelogramPanel || source instanceof RectanglePanel || source instanceof RhombusPanel) {
					FlowChartPanel panel = (FlowChartPanel) source;
					FlowChartContentPanel contentPane = (FlowChartContentPanel) panel.getParent();
					AlgorithmComponent ac = panel.associatedComponent;
					try {
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Passing " + ac + " for deletion");
						super.memory.delete(ac);
					} catch (Exceptions e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(panel, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						DebuggerConsole.getInstance().printDefaultErrorLog(referenceType, e1.getMessage());
					}
					contentPane.updatePane(MemoryStorage.getInstance().getComponents());
				}
				
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
