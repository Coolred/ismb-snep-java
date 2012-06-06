package com.bcntouch.common;

import android.app.Activity;
import android.content.Context;

//TODO - add an appropriate license message to allow others to use this code....


/**
 * This class is to help check Nfc status, even when we are running on a version of Android prior
 * to the introduction of Nfc and it's classes. It MUST not import any nfc classes, or running it
 * will cause an exception. It should check if the classes are nfcPresent, and if they are invoke
 * methods on other classes that DO import the nfc classes and so will not fail as they are only 
 * ever invoked if we know the nfc classes are available.
 */
public class NfcManager {
	private static Boolean 		nfcPresent = null;
	private static NfcHelper	nfcHelper = null;

	/**
	 * Figure out if the NFC libraries are nfcPresent. No need to do the check every time as this is a
	 * HW and system SW attribute that will not change during the execution of our application
	 * @return
	 */
	public static boolean nfcPresent( Context context ) {
		// If we have never done the check then do it now
		if ( nfcPresent == null )
			try {
				// Check to see if the NFC classes available
				Class.forName( "android.nfc.NfcAdapter" );
				
				// Instantiate a run time object that can call the NFC library 
				// This will also check that the HW is present and the adapter can be found
				nfcHelper = new NfcHelper( context );

				// nfcPresent indicates that the classes AND the hardware are present
				nfcPresent = true;
			}
			catch ( Exception ex ) {
				nfcPresent = false;
			}
			catch( Error er ) {
				// This is thrown when we look for NFC hardware on the siumulator
				nfcPresent = false;
			}
		
		return nfcPresent;
	}
	
	/**
	 * Checks if the system has NFC in a run-time safe way, if it is available, then it checks if enabled
	 */
	public static boolean nfcPresentAndEnabled( Context context ) {
		if ( nfcPresent( context ) )
			return nfcHelper.isEnabled();
		else
			return false;
	}
	
	/**
	 * Checks if the system has NFC in a run-time safe way, if it is available, then it checks if disabled
	 */
	public static boolean nfcPresentAndDisabled( Context context ) {
		if ( nfcPresent( context ) )
			return ! nfcHelper.isEnabled();
		else
			return false;
	}
	
	public static void disableForeground( Activity activity ) {
		if ( nfcPresent( activity ) )
			nfcHelper.disableForeground( activity );
	}
	
	public static void enableForeground( Activity activity ) {
		if ( nfcPresent( activity ) )
			nfcHelper.enableForeground( activity );
	}
}