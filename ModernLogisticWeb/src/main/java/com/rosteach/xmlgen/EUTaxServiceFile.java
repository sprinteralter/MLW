package com.rosteach.xmlgen;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface EUTaxServiceFile extends Library{
	EUTaxServiceFile INSTANCE = (EUTaxServiceFile) Native.loadLibrary("C:/Program Files/Institute of Informational Technologies/Certificate Authority-1.3/End User/Tax Service File/EUTaxServiceFile.dll", EUTaxServiceFile.class);
	
	boolean Initialize(String caType);
	boolean SetUIMode(boolean uiMode);
	boolean ResetPrivateKey(EUKeyType keyType);
	void SetFilesOptions(boolean useHeaders);
	boolean SetPrivateKey(EUKeyType keyType,Object privKey, 
						  String privKeyPassword,boolean useDirectorAsDigitalStamp);
	
	Object SignFilesByAccountant(Object fileNames);
	
	Object SignFilesByDirector(Object fileNames);
	
	Object SignFilesByDigitalStamp(Object fileNames);

	public static interface EUKeyType{
		public static final int euKeyTypeAccountant = 1;
		public static final int euKeyTypeDirector = 2;
		public static final int euKeyTypeDigitalStamp = 3;
	}
	
	
}
