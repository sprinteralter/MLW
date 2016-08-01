//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.28 at 02:43:00 PM EEST 
//


package com.rosteach.xml.tavria;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DOCUMENTNAME" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DATE" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DELIVERYDATE" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DELIVERYTIME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CURRENCY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="INFO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HEAD">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SUPPLIER" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="BUYER" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="DELIVERYPLACE" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="SENDER" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="RECIPIENT" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="EDIINTERCHANGEID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="POSITION" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="POSITIONNUMBER" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="PRODUCT" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="PRODUCTIDBUYER" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ORDEREDQUANTITY" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="ORDERUNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ORDERPRICE" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="PRICEWITHVAT" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="CHARACTERISTIC">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "documentname",
    "number",
    "date",
    "deliverydate",
    "deliverytime",
    "currency",
    "info",
    "head"
})
@XmlRootElement(name = "ORDER")
public class ORDER {

    @XmlElement(name = "DOCUMENTNAME")
    protected short documentname;
    @XmlElement(name = "NUMBER", required = true)
    protected String number;
    @XmlElement(name = "DATE", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "DELIVERYDATE", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deliverydate;
    @XmlElement(name = "DELIVERYTIME", required = true)
    protected String deliverytime;
    @XmlElement(name = "CURRENCY", required = true)
    protected String currency;
    @XmlElement(name = "INFO", required = true)
    protected String info;
    @XmlElement(name = "HEAD", required = true)
    protected ORDER.HEAD head;

    /**
     * Gets the value of the documentname property.
     * 
     */
    public short getDOCUMENTNAME() {
        return documentname;
    }

    /**
     * Sets the value of the documentname property.
     * 
     */
    public void setDOCUMENTNAME(short value) {
        this.documentname = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMBER() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMBER(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDATE() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDATE(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the deliverydate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDELIVERYDATE() {
        return deliverydate;
    }

    /**
     * Sets the value of the deliverydate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDELIVERYDATE(XMLGregorianCalendar value) {
        this.deliverydate = value;
    }

    /**
     * Gets the value of the deliverytime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDELIVERYTIME() {
        return deliverytime;
    }

    /**
     * Sets the value of the deliverytime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDELIVERYTIME(String value) {
        this.deliverytime = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENCY() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENCY(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFO() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFO(String value) {
        this.info = value;
    }

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link ORDER.HEAD }
     *     
     */
    public ORDER.HEAD getHEAD() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link ORDER.HEAD }
     *     
     */
    public void setHEAD(ORDER.HEAD value) {
        this.head = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SUPPLIER" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="BUYER" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="DELIVERYPLACE" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="SENDER" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="RECIPIENT" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="EDIINTERCHANGEID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="POSITION" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="POSITIONNUMBER" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="PRODUCT" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="PRODUCTIDBUYER" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ORDEREDQUANTITY" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="ORDERUNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ORDERPRICE" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="PRICEWITHVAT" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="CHARACTERISTIC">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "supplier",
        "buyer",
        "deliveryplace",
        "sender",
        "recipient",
        "ediinterchangeid",
        "position"
    })
    public static class HEAD {

        @XmlElement(name = "SUPPLIER")
        protected long supplier;
        @XmlElement(name = "BUYER")
        protected long buyer;
        @XmlElement(name = "DELIVERYPLACE")
        protected long deliveryplace;
        @XmlElement(name = "SENDER")
        protected long sender;
        @XmlElement(name = "RECIPIENT")
        protected long recipient;
        @XmlElement(name = "EDIINTERCHANGEID", required = true)
        protected String ediinterchangeid;
        @XmlElement(name = "POSITION")
        protected List<ORDER.HEAD.POSITION> position;

        /**
         * Gets the value of the supplier property.
         * 
         */
        public long getSUPPLIER() {
            return supplier;
        }

        /**
         * Sets the value of the supplier property.
         * 
         */
        public void setSUPPLIER(long value) {
            this.supplier = value;
        }

        /**
         * Gets the value of the buyer property.
         * 
         */
        public long getBUYER() {
            return buyer;
        }

        /**
         * Sets the value of the buyer property.
         * 
         */
        public void setBUYER(long value) {
            this.buyer = value;
        }

        /**
         * Gets the value of the deliveryplace property.
         * 
         */
        public long getDELIVERYPLACE() {
            return deliveryplace;
        }

        /**
         * Sets the value of the deliveryplace property.
         * 
         */
        public void setDELIVERYPLACE(long value) {
            this.deliveryplace = value;
        }

        /**
         * Gets the value of the sender property.
         * 
         */
        public long getSENDER() {
            return sender;
        }

        /**
         * Sets the value of the sender property.
         * 
         */
        public void setSENDER(long value) {
            this.sender = value;
        }

        /**
         * Gets the value of the recipient property.
         * 
         */
        public long getRECIPIENT() {
            return recipient;
        }

        /**
         * Sets the value of the recipient property.
         * 
         */
        public void setRECIPIENT(long value) {
            this.recipient = value;
        }

        /**
         * Gets the value of the ediinterchangeid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEDIINTERCHANGEID() {
            return ediinterchangeid;
        }

        /**
         * Sets the value of the ediinterchangeid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEDIINTERCHANGEID(String value) {
            this.ediinterchangeid = value;
        }

        /**
         * Gets the value of the position property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the position property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPOSITION().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ORDER.HEAD.POSITION }
         * 
         * 
         */
        public List<ORDER.HEAD.POSITION> getPOSITION() {
            if (position == null) {
                position = new ArrayList<ORDER.HEAD.POSITION>();
            }
            return this.position;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="POSITIONNUMBER" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="PRODUCT" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="PRODUCTIDBUYER" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ORDEREDQUANTITY" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="ORDERUNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ORDERPRICE" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="PRICEWITHVAT" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="CHARACTERISTIC">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "positionnumber",
            "product",
            "productidbuyer",
            "orderedquantity",
            "orderunit",
            "orderprice",
            "pricewithvat",
            "characteristic"
        })
        public static class POSITION {

            @XmlElement(name = "POSITIONNUMBER")
            protected int positionnumber;
            @XmlElement(name = "PRODUCT")
            protected long product;
            @XmlElement(name = "PRODUCTIDBUYER")
            protected int productidbuyer;
            @XmlElement(name = "ORDEREDQUANTITY")
            protected float orderedquantity;
            @XmlElement(name = "ORDERUNIT", required = true)
            protected String orderunit;
            @XmlElement(name = "ORDERPRICE")
            protected float orderprice;
            @XmlElement(name = "PRICEWITHVAT")
            protected float pricewithvat;
            @XmlElement(name = "CHARACTERISTIC", required = true)
            protected ORDER.HEAD.POSITION.CHARACTERISTIC characteristic;

            /**
             * Gets the value of the positionnumber property.
             * 
             */
            public int getPOSITIONNUMBER() {
                return positionnumber;
            }

            /**
             * Sets the value of the positionnumber property.
             * 
             */
            public void setPOSITIONNUMBER(int value) {
                this.positionnumber = value;
            }

            /**
             * Gets the value of the product property.
             * 
             */
            public long getPRODUCT() {
                return product;
            }

            /**
             * Sets the value of the product property.
             * 
             */
            public void setPRODUCT(long value) {
                this.product = value;
            }

            /**
             * Gets the value of the productidbuyer property.
             * 
             */
            public int getPRODUCTIDBUYER() {
                return productidbuyer;
            }

            /**
             * Sets the value of the productidbuyer property.
             * 
             */
            public void setPRODUCTIDBUYER(int value) {
                this.productidbuyer = value;
            }

            /**
             * Gets the value of the orderedquantity property.
             * 
             */
            public float getORDEREDQUANTITY() {
                return orderedquantity;
            }

            /**
             * Sets the value of the orderedquantity property.
             * 
             */
            public void setORDEREDQUANTITY(float value) {
                this.orderedquantity = value;
            }

            /**
             * Gets the value of the orderunit property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getORDERUNIT() {
                return orderunit;
            }

            /**
             * Sets the value of the orderunit property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setORDERUNIT(String value) {
                this.orderunit = value;
            }

            /**
             * Gets the value of the orderprice property.
             * 
             */
            public float getORDERPRICE() {
                return orderprice;
            }

            /**
             * Sets the value of the orderprice property.
             * 
             */
            public void setORDERPRICE(float value) {
                this.orderprice = value;
            }

            /**
             * Gets the value of the pricewithvat property.
             * 
             */
            public float getPRICEWITHVAT() {
                return pricewithvat;
            }

            /**
             * Sets the value of the pricewithvat property.
             * 
             */
            public void setPRICEWITHVAT(float value) {
                this.pricewithvat = value;
            }

            /**
             * Gets the value of the characteristic property.
             * 
             * @return
             *     possible object is
             *     {@link ORDER.HEAD.POSITION.CHARACTERISTIC }
             *     
             */
            public ORDER.HEAD.POSITION.CHARACTERISTIC getCHARACTERISTIC() {
                return characteristic;
            }

            /**
             * Sets the value of the characteristic property.
             * 
             * @param value
             *     allowed object is
             *     {@link ORDER.HEAD.POSITION.CHARACTERISTIC }
             *     
             */
            public void setCHARACTERISTIC(ORDER.HEAD.POSITION.CHARACTERISTIC value) {
                this.characteristic = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "description"
            })
            public static class CHARACTERISTIC {

                @XmlElement(name = "DESCRIPTION", required = true)
                protected String description;

                /**
                 * Gets the value of the description property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRIPTION() {
                    return description;
                }

                /**
                 * Sets the value of the description property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRIPTION(String value) {
                    this.description = value;
                }

            }

        }

    }

}
