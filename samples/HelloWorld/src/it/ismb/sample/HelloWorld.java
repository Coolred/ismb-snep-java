/*
 * SendetToPhone- Simple test of IsmbSNEPConnection
 * 
* Copyright (C) 2012  Antonio Lotito <lotito@ismb.it>
 * 
 * This file is part of ISMB-SNEP-JAVA LIBRARY.
 * 
 * ISMB-SNEP-JAVA LIBRARY is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 * 
 * ISMB-SNEP-JAVA LIBRARY is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with ISMB-SNEP-JAVA-LIBRARY. If not, see http://www.gnu.org/licenses/.
 */

package it.ismb.sample;

import it.ismb.snep.IsmbSnepConnection;
import it.ismb.snep.IsmbSnepException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.smartcardio.CardTerminal;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HelloWorld extends JFrame {
	private final static String TYPE = "application/com.example.android.beam";
	private final static String MESSAGE = "Hello World";
				
	public HelloWorld() {
		initUI();
	}
	
	private void sendMessage( CardTerminal terminal, String type, String message ) throws IsmbSnepException {
		IsmbSnepConnection snepConnection = new IsmbSnepConnection( terminal );
		snepConnection.setDebugMode();
		snepConnection.send( type, message );				  
		snepConnection.close();
		System.out.println( "Message Sent" );
	}
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void initUI() {
        //Create and set up the window.
        setTitle("SNEP Hello World");
        
        // Terminate this app when user selects Close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final TerminalSelector terminalSelector = new TerminalSelector();
        
        final JTextField message = new JTextField( MESSAGE, 20 );
        
        final JTextField type = new JTextField( TYPE, 20 );

        // Add a Button
        JButton button = new JButton( "Send message by SNEP" );
        button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sendMessage( terminalSelector.getTerminal(), type.getText(), message.getText() );
				}
				catch ( IsmbSnepException ex ) {
					JDialog dialog = new JDialog();
					dialog.setTitle( "SNEP Error" );
					JLabel message = new JLabel( ex.getMessage() );
					dialog.add( message );
					dialog.setVisible( true );
				}
			}        	
        } );
		button.setEnabled( terminalSelector.terminalSelected() );	
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add( terminalSelector );
        panel.add( type );
        panel.add( message );
        panel.add(button);
        getContentPane().add( panel );

        //Display the window.
        pack();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
        	public void run() {
	        	HelloWorld hw = new HelloWorld();
	        	hw.setVisible(true);
        	}
        } );
    }
}
