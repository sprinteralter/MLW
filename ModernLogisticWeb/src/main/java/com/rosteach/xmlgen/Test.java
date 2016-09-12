package com.rosteach.xmlgen;

public class Test {

	public static void main(String argv[]){    
	    EUTaxServiceFile dll = EUTaxServiceFile.INSTANCE;   
	    System.out.println(dll.getClass().getMethods());
	    //System.out.println(dll.Initialize("UA1"));
	}
}