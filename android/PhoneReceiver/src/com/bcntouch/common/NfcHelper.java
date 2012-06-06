package com.bcntouch.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.nfc.NfcAdapter;

// TODO - add an appropriate license message to allow others to use this code....

/**
 * This class is used to help usr Nfc in a non-static way, so that we can run the application
 * even when the required packages for nfc are not present. This method should only be instantiated and
 * used if we KNOW that the nfc classes are available in the system -> use NfcManager
 * @author andrew
 *
 */
public class NfcHelper {
	private NfcAdapter			adapter;
	private PendingIntent 		pendingIntent;
	
	public NfcHelper( Context ctx ) throws Exception {
		// Get the default NFC adapter
	    adapter = NfcAdapter.getDefaultAdapter( ctx );
	    
	    // it's possible the hardware is not present
	    if ( adapter == null )
	    	throw new Exception( "Nfc Adapter could not be found" );
	    
		// Create an intent that will start the TagDetected Activity
	    // TODO - let's not mix in detecting tags just yet
//	    pendingIntent = PendingIntent.getActivity( ctx, 0, new Intent( ctx, TagDetectedActivity.class ), Intent.FLAG_ACTIVITY_NEW_TASK );
	}
	
	/**
	 * Only call this method if you have already checked that the Nfc library calss "android.nfc.NfcAdapter" is available
	 */
	public boolean isEnabled() {
		boolean nfcEnabled = false;
		try {
		    // if it exists then check if nfcEnabled
		    if ( adapter != null ) {
		        nfcEnabled = adapter.isEnabled();
		    } 
		}
		catch ( Exception ex ) {
			nfcEnabled = false;
		}
		
		return nfcEnabled;
	}
	
	public void disableForeground( Activity activity ) {
		adapter.disableForegroundDispatch( activity );
	}

	public void enableForeground( Activity activity ) {
		adapter.enableForegroundDispatch( activity, pendingIntent, null /* no filters */, null /* all tag technologies */ );
	}
}
