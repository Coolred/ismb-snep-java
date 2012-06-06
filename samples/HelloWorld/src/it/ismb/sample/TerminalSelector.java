package it.ismb.sample;

import java.util.List;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TerminalSelector extends JLabel {
	private CardTerminal selectedTerminal;
	
	private CardTerminal selectTerminal() {
		CardTerminal terminal = null;
		
		 TerminalFactory factory;
		 List<CardTerminal> terminals;
		 
		 // Find all available terminals	 		 
		 try {
			System.out.println("Get factory");
			 factory = TerminalFactory.getDefault();
			 System.out.println("Get terminals");
			 terminals = factory.terminals().list();          
			 if (terminals.size() == 0) {
				 System.out.println("There are no terminals.");
				 terminals = null;			 
			 }
			 else {
				 // chose always the first available terminal for now
				 // TODO: If more than one terminal, show user list and let them chose one
				 terminal=terminals.get(0);
			 }	
			} 
			catch (CardException c) {
				System.out.print(c.getMessage());
				terminals = null;			
			}
		 
		 return terminal;
	}
	
	public CardTerminal getTerminal() {
		return selectedTerminal;
	}
	
	public boolean terminalSelected() {
		return ( selectedTerminal != null );
	}
	
	public String getName() {
		if ( selectedTerminal == null )
			return null;
		
		return selectedTerminal.getName();
	}
	
	public TerminalSelector() {
		selectedTerminal = selectTerminal();
		
		// TODO make this a list object that fills itself with the list of found terminals
		// and lets the user select one from the list, which becomes the selected one
		if ( selectedTerminal == null )
			setText( "No Terminal found" );
		else
			setText("Terminal name: " + selectedTerminal.getName() );

//			JDialog dialog = new JDialog();
//			dialog.setTitle( "SNEP Error" );
//			JLabel message = new JLabel( "No Terminal found"  );
//			dialog.add( message );
//			dialog.setVisible( true );
	}
}
