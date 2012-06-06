/*
 * IsmbNppConnection
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


package it.ismb.snep;

import java.nio.ByteBuffer;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

@SuppressWarnings("restriction")
public class IsmbSnepConnection {
	private final static byte[] targetConnect = { (byte)0x01, (byte) 0x11, (byte) 0x20 };
	
	private final static byte[] initiatorPayload = { (byte) 0x01, (byte) 0x02,
			(byte) 0x04,
			(byte) 0x46, 
			(byte) 0x66, (byte) 0x6D, (byte) 0x01, (byte) 0x01, (byte) 0x10,        				      				        				  
			(byte) 0x03, (byte) 0x02, (byte) 0x00,
			(byte) 0x01, (byte) 0x04, (byte) 0x01, (byte) 0x96        		
	};
	
	private final static byte[] targetPayload = {
			(byte) 0x01, //TARGET
			(byte) 0x13, (byte) 0x20, //INFO LLCP		 
			(byte) 0x00, //SEQUENCE
			
			// ------------- This below is the SNEP request
			(byte) 0x10, // Protocol Version - VERSION			        		
			(byte) 0x02, // Request Method - PUT
			
			// SNEP LENGTH 0x58 = 88 = first byte + type length byte + payload length byte + type bytes (36) + content length  (86)
			// content length (86) = 36 (target) + 49 (actual payload) ?? = 86
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x58, //SNEP LENGTH
			
			// ------------- This below is the NDEF Message request
			(byte)0xD2, //NDEF FIRST BYTE
			
			(byte)0x24, //TYPE LENGTH     - 36 bytes			
			
			(byte)0x31, //PAYLOAD LENGTH - 49 bytes
			
			// TYPE
			// Use appendToByteArray and keep track of payload length
			// "application/com.example.android.beam"
			(byte)0x61, (byte)0x70, (byte)0x70, (byte)0x6C, (byte)0x69, 
			(byte)0x63, (byte)0x61,	(byte)0x74, (byte)0x69, (byte)0x6F, 
			(byte)0x6E, (byte)0x2F, (byte)0x63, (byte)0x6F, (byte)0x6D, 
			(byte)0x2E, (byte)0x65, (byte)0x78, (byte)0x61, (byte)0x6D, 
			(byte)0x70, (byte)0x6C,	(byte)0x65, (byte)0x2E, (byte)0x61, 
			(byte)0x6E, (byte)0x64, (byte)0x72, (byte)0x6F, (byte)0x69,
			(byte)0x64, (byte)0x2E, (byte)0x62, (byte)0x65, (byte)0x61, 
			(byte)0x6D, // 36 bytes
			//end of "application/com.example.android.beam"

			// Use appendToByteArray and keep track of payload length
			(byte)0x42, (byte)0x65, (byte)0x61, (byte)0x6D, (byte)0x20, // "Beam " 5 bytes
			(byte)0x72, (byte)0x65, (byte)0x63, (byte)0x65, (byte)0x69, 
			(byte)0x76, (byte)0x65, (byte)0x64, (byte)0x20, // "received " 9 bytes
			(byte)0x66, (byte)0x72, (byte)0x6F, (byte)0x6D, (byte)0x20, // "from " 5 bytes
			(byte)0x49, (byte)0x53, (byte)0x4D, (byte)0x42, (byte)0x20, // "ISMB " 5 bytes
			(byte)0x53, (byte)0x4E, (byte)0x45, (byte)0x50, (byte)0x20, // "SNEP " 5 bytes
			(byte)0x4C, (byte)0x49, (byte)0x42,
			(byte)0x52, (byte)0x41, (byte)0x52,
			(byte)0x59, (byte)0x3A, (byte)0x0A, // "LIBRARY:/n" 9 bytes
			(byte)0x0A, // "/n" 1 byte
			(byte)0x67, (byte)0x6F, (byte)0x6F, (byte)0x64, (byte)0x20, // "good " 5 bytes
			(byte)0x6C, (byte)0x75, (byte)0x63, (byte)0x6B, (byte)0x21 // "luck!" 5 bytes
			// 49 bytes
	};	
			
//        private final static int BLOCK_SIZE = 250;     
//        public final static int INITIATOR = 0;
//        public final static int TARGET = 1;
//
//        private final static int RECEIVE = 0;
//        private final static int SEND = 1;

    //USEFUL APDU COMMANDS 
    private final static byte TG_GET_DATA = (byte) 0x86;
    private final static byte TG_INIT_AS_TARGET = (byte) 0x8c;
    private final static byte TG_SET_DATA = (byte) 0x8e;
    private final static byte IN_RELEASE = (byte) 0x52;
    private final static byte IN_JUMP_FOR_DEP = (byte) 0x56;
    private final static byte TG_GET_TARGET_STATUS = (byte)0x8A;
    
    //Mode of LLCP connection (always target)
    //private int mode=TARGET;
    //private int transmissionMode = RECEIVE;
    
    //private byte[] receiveBuffer;
    private CardTerminal terminal;
    private CardChannel ch;

    //  Enable debugMode to print info about the communication
    private boolean debugMode = false;

    //Set debug mode
    public void setDebugMode() {
            debugMode = true;
    }
    
	//Unset debug mode
    public void unsetDebugMode() {
            debugMode = false;
    } 
        
    /**
     * Initialize SNEP Connection
     *
     * @param t
     *            a valid card terminal
     *
     * @throws IsmbSnepException
     *             if the terminal is incorrect
     */
    public IsmbSnepConnection(CardTerminal t) throws IsmbSnepException {
        if (t == null) {
                throw new IsmbSnepException("invalid card terminal");
        }
        terminal = t;
        Card card;
        try {
             if (terminal.isCardPresent()) {
            	 card = terminal.connect("*");
            	 System.out.println("card: "+card);
                 ch = card.getBasicChannel();
                 System.out.println("Protocol:"+card.getProtocol());   
              	 } 
             else {
            	 throw new IsmbSnepException("Device not supported, only ACS ACR122 is supported now");
             }
        } catch (CardException e) { throw new IsmbSnepException("problem with connecting to reader");}       
    }

    /**
     * Close the connection (release target)
     *
     * @throws IsmbSnepException
     */
    public void close() throws IsmbSnepException {
        //if (mode == INITIATOR) {
                transceive(IN_RELEASE, new byte[] { 0x01 });
        //}
    }
        
    /**
     * Sends and receives APDUs to and from the controller
     *
     * @param instr
     *            Instruction
     * @param param
     *            Payload to send
     *
     * @return The response payload 
     */
    private byte[] transceive( byte instr, byte[] payload ) throws IsmbSnepException {
        if (ch == null) {
                throw new IsmbSnepException("channel not open");
        }
        int payloadLength = (payload != null) ? payload.length : 0;
        byte[] instruction = { (byte) 0xd4, instr };

        //ACR122 header
        byte[] header = { (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                        (byte) (instruction.length + payloadLength) };

        /* construct the command */
        byte[] cmd = Util.appendToByteArray(header, instruction, 0,
                        instruction.length);
        cmd = Util.appendToByteArray(cmd, payload);

        if (debugMode)
            Util.debugAPDUs(cmd, null);

        try {
        	// Send the command and payload to the reader
            CommandAPDU c = new CommandAPDU(cmd);
            // and read the response
            ResponseAPDU r = ch.transmit(c);
            
            byte[] ra = r.getBytes();

            if (debugMode)
                Util.debugAPDUs(null, ra);

            /* check whether APDU command was accepted by the Controller */
            if (r.getSW1() == 0x63 && r.getSW2() == 0x27) {
                throw new CardException("wrong checksum from contactless response");
            } else if (r.getSW1() == 0x63 && r.getSW2() == 0x7f) {
                throw new CardException("wrong PN53x command");
            } else if (r.getSW1() != 0x90 && r.getSW2() != 0x00) {
                throw new CardException("unknown error");
            }
            
            // Is this to skip the first two bytes (SW1 and SW2) in the response?
            // Can't remember the structure, and what the other two bytes (to make 4) maybe..
            return Util.subByteArray(ra, 2, ra.length - 4);
        } catch (CardException e) {
            throw new IsmbSnepException("problem with transmitting data");
        }
    }
    
    /**
     * 
     * @param target  - The String name of the target - application/com.example.android.beam
     * @param payload - The byte array to send
     */
    public void send( String type,String message ) throws IsmbSnepException { 
    	System.out.println("Called Procedure to Send data .. INITIATOR MODE");
    	        		
		transceive(IN_JUMP_FOR_DEP, initiatorPayload);        		
		System.out.println("IN JUMP FOR DEP done..");
    	
    	while ( true ){
        	byte[] reply = transceive((byte)0x40, targetConnect);
        
        	System.out.println( "Reply Length:" + reply.length + ", Reply: " + Util.byteArrayToString(reply) );
        	// TODO check for something more conclusive than the length?
        	if ( reply.length == 7 ) {
        		System.out.println("CC RECEIVED");
        		break;
        	}
        	
        	try {
        		Thread.sleep(300);
        	} catch (InterruptedException e1) {e1.printStackTrace();}        	
	    }

	   try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	   // Send the payload
	   NDEFMessage ndef = new NDEFMessage( type, message );
	   SnepMessage snep = new SnepMessage( ndef );
	   LlcpMessage llcp = new LlcpMessage( snep );
	   byte[] testPayload = llcp.getBytes();
//	   transceive( (byte) 0x40, targetPayload );			
	   transceive( (byte) 0x40, testPayload );			
    }
}