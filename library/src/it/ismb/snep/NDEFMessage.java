package it.ismb.snep;

import java.nio.ByteBuffer;

public class NDEFMessage implements HasBytes {
	private final static byte NDEF_FIRST_BYTE = (byte)0xD2;
	
	private String	type;
	private String	contents;
	
	public NDEFMessage() {
	}

	public NDEFMessage( String type, String contents ) {
		setType( type );
		setContents( contents );
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public int getLength() {
		// NDEF first byte  - 1 byte
		// type length byte - 1 byte
		// contents length byte - 1 byte
		// + type + contents
		
		if ( ( type == null ) || ( contents == null ) )
			return 0;
		
		return 3 + this.type.getBytes().length + this.contents.getBytes().length;
	}

	@Override
	public byte[] getBytes() {
		if ( ( type == null ) || ( contents == null ) )
			return null;

		ByteBuffer bytes = ByteBuffer.allocate( getLength() );
	 	   
 	   // Add the standard header
 	   bytes.put( NDEF_FIRST_BYTE );
 	   
 	   // Add the type length byte
 	   bytes.put( (byte)type.getBytes().length );
 	   
 	   // Add the contents length byte
 	   bytes.put( (byte)contents.getBytes().length );
   
 	   // Add type bytes
 	   if ( ( type != null ) && ( type.length() > 0 ) )
 		   bytes.put( type.getBytes(), 0, type.length() );

 	   // Add content bytes
 	   if ( ( contents != null ) && ( contents.length() > 0 ) )
 		   bytes.put( contents.getBytes(), 0, contents.length() );

 	   return bytes.array();	
	}
}
