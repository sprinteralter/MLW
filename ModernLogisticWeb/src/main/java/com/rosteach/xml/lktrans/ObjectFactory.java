//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.29 at 12:33:18 PM EEST 
//


package com.rosteach.xml.lktrans;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rosteach.xml.lktrans package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rosteach.xml.lktrans
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ORDER }
     * 
     */
    public ORDER createORDER() {
        return new ORDER();
    }

    /**
     * Create an instance of {@link ORDER.HEAD }
     * 
     */
    public ORDER.HEAD createORDERHEAD() {
        return new ORDER.HEAD();
    }

    /**
     * Create an instance of {@link ORDER.HEAD.POSITION }
     * 
     */
    public ORDER.HEAD.POSITION createORDERHEADPOSITION() {
        return new ORDER.HEAD.POSITION();
    }

    /**
     * Create an instance of {@link ORDER.HEAD.POSITION.CHARACTERISTIC }
     * 
     */
    public ORDER.HEAD.POSITION.CHARACTERISTIC createORDERHEADPOSITIONCHARACTERISTIC() {
        return new ORDER.HEAD.POSITION.CHARACTERISTIC();
    }

}
