package it.ismb.snep;

import java.nio.ByteBuffer;

public class SnepMessage implements HasBytes {
	private final static byte SNEP_VERSION_1_0 = 0x10;
	
	// Request Field Values
	class REQUEST {
		private final static byte SNEP_CONTINUE = (byte)0x00;
		private final static byte SNEP_GET 		= (byte)0x01;
		private final static byte SNEP_PUT 		= (byte)0x02;
		private final static byte SNEP_REJECT 	= (byte)0x7f;
	};

	// Response Field Values
	class RESPONSE {
		private final static byte SNEP_CONTINUE 			= (byte)0x80;
		private final static byte SNEP_SUCCESS 				= (byte)0x81;
		private final static byte SNEP_NOT_FOUND			= (byte)0xc0;
		private final static byte SNEP_EXCESS_DATA			= (byte)0xc1;
		private final static byte SNEP_BAD_REQUEST			= (byte)0xc2;
		private final static byte SNEP_NOT_IMPLEMENTED		= (byte)0xe0;
		private final static byte SNEP_UNSUPPORTED_VERSION	= (byte)0xe1;
		private final static byte SNEP_REJECT				= (byte)0xff;
	};
	
	private byte 		protocolVersion = SNEP_VERSION_1_0;
	private byte 		method 			= REQUEST.SNEP_PUT;
	private HasBytes 	payload;
	
	public SnepMessage() {	
	}
	
	public SnepMessage( HasBytes payload ) {
		setPayload( payload );
	}
	
	public void setPayload( HasBytes payload ) {
		this.payload = payload;
	}
	
	public HasBytes getPayload() {
		return this.payload;
	}
	
	public void setMethod( byte method ) {
		this.method = method;
	}
	
	public byte getMethod() {
		return this.method;
	}
	
	@Override
	public int getLength() {
		// the length of the snep message will be...
		// protocol version - 1 byte
		// method           - 1 byte
		// payload length   - 4 bytes
		// payload          - depends
		int snepLength = 6 + payload.getLength();
		
		return snepLength;		
	}
	
	@Override
	public byte[] getBytes() {
 	   ByteBuffer bytes = ByteBuffer.allocate( getLength() );
 	   
 	   // Add the standard header
 	   bytes.put( protocolVersion );
 	   
 	   // Add the method
 	   bytes.put( method );
 	   
 	   // Calculate payload length in 4 bytes and add
 	   //b.order(ByteOrder.BIG_ENDIAN); // optional, the initial order of a byte buffer is always BIG_ENDIAN.
 	   if ( ( payload == null ) || ( payload.getLength() == 0 ) )
 		   bytes.putInt( 0 );
 	   else 
 		   bytes.putInt( payload.getLength() );
 	   
 	   // Add payload
 	   if ( ( payload != null ) && ( payload.getLength() > 0 ) )
 		   bytes.put( payload.getBytes(), 0, payload.getLength() );
 	   
 	   return bytes.array();
	}

}
