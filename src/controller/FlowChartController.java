package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;

import model.AlgorithmExecuter;
import model.DebuggerConsole;
import model.Timer;
import model.Components.AlgorithmComponent;
import model.Components.ComponentAssign;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentWhile;
import model.Memory.MemoryStorage;
import view.FlowChartContentPanel;
import view.editComponents.AddComponent;
import view.editComponents.EditAssign;
import view.editComponents.EditAssign.ValuesAssignComponent;
import view.editComponents.EditComment;
import view.editComponents.EditDeclaration;
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
	
	public FlowChartController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, FlowChartContentPanel panel) {
		super(ALGORITHM_EXECUTER, TIMER, MemoryStorage.getInstance());
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
		
		switch (e.getActionCommand()) {
		case "ADD_COMPONENT": {
			AlgorithmComponent newComponent = null;
//			System.err.println(FCPanel.getParent().getParent().getParent().getParent().getParent().getParent().getParent());
			AddComponent addComp = new AddComponent((JFrame)FCPanel.getParent().getParent().getParent().getParent().getParent().getParent().getParent());
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
						newComponent = new ComponentAssign(null, null, null);
						break;
					case "Declaration":
						newComponent = new ComponentDeclaration(null, null, null);
						break;
					case "Operation":
						newComponent = new ComponentOperation(null, null, null);
						break;
					case "Input":
						newComponent = new ComponentInput(null, null, null);
						break;
					case "Output":
						newComponent = new ComponentOutput(null, null, null);
						break;
					case "If":
						newComponent = new ComponentIf(null, null, null);
						break;
					case "While":
						newComponent = new ComponentWhile(null, null);
						break;
					case "Comment":
						newComponent = new ComponentComment(null, null, null);
						break;
					}
					MemoryStorage.getInstance().addComponent(newComponent, index+1);
					panel.updatePane(MemoryStorage.getInstance().getComponents());
				}
			}
			
		break;
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
		System.out.println(e);
		Object source = e.getSource();
		if(e.getButton() == MouseEvent.BUTTON1) {
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
					//edit declaration
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
				frame.revalidate();
				frame.repaint();
			}
//			System.out.println(panel.getParent().getParent().getParent().getParent().getParent().getParent());
			
			
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
