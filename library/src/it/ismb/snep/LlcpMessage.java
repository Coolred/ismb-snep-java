package it.ismb.snep;

import java.nio.ByteBuffer;

public class LlcpMessage implements HasBytes {
	private final static byte TARGET = (byte) 0x01;
	private final static byte[] INFO = { (byte) 0x13, (byte) 0x20 };
	
	private HasBytes contents;
	private byte sequence = 0;
	
	public LlcpMessage() {
	}

	public LlcpMessage(HasBytes contents) {
		this.contents = contents;
	}

	public HasBytes getContents() {
		return contents;
	}

	public void setContents(HasBytes contents) {
		this.contents = contents;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBytes() {
		ByteBuffer bytes = ByteBuffer.allocate( 4 + contents.getLength() );
		
		bytes.put( TARGET );
		bytes.put( INFO, 0, INFO.length );
		bytes.put( sequence );
		
		bytes.put( contents.getBytes(), 0, contents.getLength() );
		
		return bytes.array();
	}	
}
