package com.rosteach.xmlgen;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.crypto.Mac;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.ws.Service;

//import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;






public class Test {

	public static void main(String argv[]) throws Exception{    
	   
		 File f = new File("C:/MLW/XMLCOMDOC.p7s/comdoc_20160816154945_230724693_43917017_007.p7s");
		   byte[] buffer = new byte[(int) f.length()];
		   DataInputStream in = new DataInputStream(new FileInputStream(f));
		   in.readFully(buffer);
		   in.close();

		   //Corresponding class of signed_data is CMSSignedData
		   CMSSignedData signature = new CMSSignedData(buffer);
		   Store cs = signature.getCertificates();
		   SignerInformationStore signers = signature.getSignerInfos();
		   
		   Collection c = signers.getSigners();
		   Iterator it = c.iterator();

		   //the following array will contain the content of xml document
		   byte[] data = null;

		   while (it.hasNext()) {
		        SignerInformation signer = (SignerInformation) it.next();
		        Collection certCollection = cs.getMatches(signer.getSID());
		        Iterator certIt = certCollection.iterator();
		        X509CertificateHolder cert = (X509CertificateHolder) certIt.next();

		        CMSProcessable sc = signature.getSignedContent();
		        data = (byte[]) sc.getContent();
		        
		        
		        String s2 = new String(data);
		       System.out.println(new String(s2));
		        
		       //test2(new String(s2));
		        signer();
		        
		       
		    }
		
		/*EUTaxServiceFile dll = EUTaxServiceFile.INSTANCE;
	    	dll.Initialize("UA1");*/
	    	
	    
	   // System.out.println(dll.getClass().getDeclaredMethod("Initialize")); //new Mapper().getFunctionName(NativeLibrary.getInstance("C:/Program Files/Institute of Informational Technologies/Certificate Authority-1.3/End User/Tax Service File/EUTaxServiceFile.dll"), dasd)
	    
	   // NativeLibrary nlUser = NativeLibrary.getInstance("EUTaxServiceFile");
	   /* Pointer gfwAddress = nlUser.getGlobalVariableAddress("DllGetClassObject");
	    Function DllGetClassObject = Function.getFunction("EUTaxServiceFile", "Initialize");*/
	    //HWND winHandle = (HWND) gfw.invoke(HWND.class, null);
	   /* NativeLibrary td=NativeLibrary.getInstance
                ("C:/Program Files/Institute of Informational Technologies/Certificate Authority-1.3/End User/Tax Service File/EUTaxServiceFile.dll");
 
        Function sum=td.getFunction("SetPrivateKeyFile");*/
        
        
	   /*dll.Initialize("UA1");
	    
	    HashMap<String, Object> nameMapping = new HashMap<String, Object>(); 
	    nameMapping.put(dll.OPTION_FUNCTION_MAPPER, StdCallLibrary.FUNCTION_MAPPER);*/
	   

	    
	   /* dll.INSTANCE.OPTION_FUNCTION_MAPPER;
	    
	    dll.INSTANCE.Initialize("UA1");
	    //dll.Initialize("UA1");
	    dll.SetUIMode(false);
	    dll.ResetPrivateKey(EUKeyType.euKeyTypeAccountant);
	    dll.ResetPrivateKey(EUKeyType.euKeyTypeDirector);
	    dll.ResetPrivateKey(EUKeyType.euKeyTypeDigitalStamp);
	    
	    File[] files  = new File("C:/MLW/XMLCOMDOC.p7s").listFiles();
	    File[] decoded = (File[]) dll.UnprotectFiles(files, false);
	    
	    Function.getFunction("EUTaxServiceFile.dll", "Initialize");
	    
	    System.out.println(dll.DllGetClassObject());
	    
	    System.out.println(decoded.length + " " + decoded[1].length());*/
	    
		   
		  
	    
	}
	
	 static final String KEYSTORE_FILE = "C:\\MLW\\KEYS\\31984286_3092219714_SS160829140904.ZS2";
	    static final String KEYSTORE_INSTANCE = "PKCS12";
	    static final String KEYSTORE_PWD = "20020801";
	    static final String KEYSTORE_ALIAS = "zub";
	    public static void signer() throws Exception {

	        String text = "This is a message";
//Gost34311
	        Security.addProvider(new BouncyCastleProvider());
	       
	        java.security.Provider [] prs = java.security.Security.getProviders();
	        java.util.TreeSet <String>ts = new java.util.TreeSet<String>();

	        for (java.security.Provider provider: prs) {
	        	System.out.println(provider.getInfo());
	        }
	        
	        KeyStore ks = KeyStore.getInstance(KEYSTORE_INSTANCE);
	        
	        ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PWD.toCharArray());
	        Key key = ks.getKey(KEYSTORE_ALIAS, KEYSTORE_PWD.toCharArray());

	        //Sign
	        PrivateKey privKey = (PrivateKey) key;
	        Signature signature = Signature.getInstance("SHA1WithRSA", "BC");
	        signature.initSign(privKey);
	        signature.update(text.getBytes());

	        //Build CMS
	        X509Certificate cert = (X509Certificate) ks.getCertificate(KEYSTORE_ALIAS);
	        List certList = new ArrayList();
	        CMSTypedData msg = new CMSProcessableByteArray(signature.sign());
	        certList.add(cert);
	        Store certs = new JcaCertStore(certList);
	        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
	        ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(privKey);
	        gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, cert));
	        gen.addCertificates(certs);
	        CMSSignedData sigData = gen.generate(msg, false);

	       // BASE64Encoder encoder = new BASE64Encoder();
	        
	        String signedContent = Base64.getEncoder().encodeToString((byte[]) sigData.getSignedContent().getContent());
	        System.out.println("Signed content: " + signedContent + "\n");

	        String envelopedData = Base64.getEncoder().encodeToString(sigData.getEncoded());
	        System.out.println("Enveloped data: " + envelopedData);
	    
	}
	
	    
	    public static void test2(String message) throws KeyStoreException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException{
	    	
	    	 /////////////////////////////////////////////////// 
	        final Set<String> found = new HashSet<String>();
	        final Set<String> missing = new HashSet<String>();

	        final DefaultSignatureAlgorithmIdentifierFinder finder = new DefaultSignatureAlgorithmIdentifierFinder();
	        for (java.security.Provider.Service service : new BouncyCastleProvider().getServices()) {
	            if ("Signature".equals(service.getType())) {
	                final String algorithm = service.getAlgorithm();
	                try {
	                    finder.find(algorithm);
	                    found.add(algorithm);
	                } catch (IllegalArgumentException ex) {
	                
	                    missing.add(algorithm);
	                }
	            }
	        }

	        System.out.println("Found: " + found);
	        System.out.println("Missing: " + missing);
	        //////////////////////////////////////////////////////
	    	
	    	/*KeyStore ks = KeyStore.getInstance("JKS");
	    	ks.setKeyEntry("keyAlias", "C:\\MLW\\KEYS\\31984286_2849912069_SS160830151104.ZS2", "20020801".toCharArray(), certChain);
	    	OutputStream writeStream = new FileOutputStream(filePathToStore);
	    	ks.store(writeStream, keystorePasswordCharArray);
	    	writeStream.close();*/
	    	
	    /*	final boolean DEBUG = false;

	    	
	    	 System.out.println("");


	    	 Security.addProvider(new BouncyCastleProvider());

	    	 String INFILE   = message;	// Input file to be signed
	    	 String KEYSTORE = "C:\\MLW\\KEYS\\31984286_2849912069_SS160830151104.ZS2";	// Java 2 keystore file
	    	 String ALIAS    = "Key1";	// Java 2 key entry alias
	    	 String PSWD     = "20020801";	// keystore password

	    	 // ---- in real implementation, provide some SECURE way to get keystore
	    	 // ---- password from user! -------

	    	 KeyStore keystore = null;
	    	 PublicKey pub = null;
	    	 PrivateKey priv = null;
	    	 java.security.cert.Certificate storecert = null;
	    	 java.security.cert.Certificate[] certChain = null;
	    	 ArrayList certList = new ArrayList();
	    	 CertStore certs =null;

	    	 
	    	   keystore = KeyStore.getInstance("PKCS12");
	    	   keystore.load(new FileInputStream(KEYSTORE), PSWD.toCharArray());

	    	   certChain = keystore.getCertificateChain(ALIAS);
	    	   for ( int i = 0; i < certChain.length;i++)
	    		certList.add(certChain[i]);      
	    	   certs = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");

	    	   priv = (PrivateKey)(keystore.getKey(ALIAS, PSWD.toCharArray()));
	    	 
	    	   storecert = keystore.getCertificate(ALIAS);
	    	   pub = keystore.getCertificate(ALIAS).getPublicKey();
	    	
	    	 
	    	 
	    	  


	    	 
	    	   System.out.println("Public Key Format: " + pub.getFormat()) ;
	    	   System.out.println("Certificate " + storecert.toString()) ;
	    	  

	    	  FileInputStream freader = null;
	    	  File f = null;

	    	//------  Get the content data from file -------------
	    	  f = new File(INFILE) ;
	    	  int sizecontent = ((int) f.length());
	    	  byte[] contentbytes = new byte[sizecontent];

	    	  try {
	    	    freader = new FileInputStream(f);
	    	    System.out.println("\nContent Bytes: " + freader.read(contentbytes, 0, sizecontent));
	    	    freader.close();
	    	   }
	    	  catch(IOException ioe) {
	    		System.out.println(ioe.toString());
	    		return;
	    		}


	    	// --- Use Bouncy Castle provider to create CSM/PKCS#7 signed message ---
	    	 try{
	    	  CMSSignedDataGenerator signGen = new CMSSignedDataGenerator();
	    	  signGen.addSigner(priv, (X509Certificate)storecert, CMSSignedDataGenerator.DIGEST_SHA1);
	    	  signGen.addCertificatesAndCRLs(certs);
	    	  CMSProcessable content = new CMSProcessableByteArray(contentbytes);

	    	  CMSSignedData signedData = signGen.generate(content,"BC");
	    	  byte[] signeddata = signedData.getEncoded();
	    	  System.out.println("Created signed message: " + signeddata.length + " bytes") ;
	    	  FileOutputStream envfos = new FileOutputStream("BCsigned.p7s");
	    	  envfos.write(signeddata);
	    	  envfos.close();
	    	 }
	    	 catch(Exception ex){
	    	  System.out.println("Couldn't generate CMS signed message\n" + ex.toString()) ;
	    	 }
	    	  System.out.println("Usage:\n java BCSignFile  <contentfile> <keystore> <alias> <keypasswd>") ;

	    	}
*/

	    	
	    	
	    	 
	    	
	    	/* char[] password = "20020801".toCharArray();
	    	    String text = message;

	    	    FileInputStream fis = new FileInputStream("C:\\MLW\\KEYS\\31984286_3092219714_SS160829140904.ZS2");
	    	    KeyStore ks = KeyStore.getInstance("pkcs12");
	    	    ks.load(fis, password);

	    	    String alias = ks.aliases().nextElement();
	    	    PrivateKey pKey = (PrivateKey)ks.getKey(alias, password);
	    	    X509Certificate c = (X509Certificate)ks.getCertificate(alias);

	    	    //Data to sign
	    	    byte[] dataToSign = text.getBytes("UTF-8");
	    	    //compute signature:
	    	    Signature signature = Signature.getInstance("SHA1WithRSA");
	    	    signature.initSign(pKey);
	    	    signature.update(dataToSign);
	    	    byte[] signedData = signature.sign();

	    	    //load X500Name
	    	    X500Name xName      = X500Name.asX500Name(c.getSubjectX500Principal().getName());
	    	    //load serial number
	    	    BigInteger serial   = c.getSerialNumber();
	    	    //laod digest algorithm
	    	    AlgorithmId digestAlgorithmId = new AlgorithmId(AlgorithmId.SHA_oid);
	    	    //load signing algorithm
	    	    AlgorithmId signAlgorithmId = new AlgorithmId(AlgorithmId.RSAEncryption_oid);

	    	    //Create SignerInfo:
	    	    SignerInfo sInfo = new SignerInfo(xName, serial, digestAlgorithmId, signAlgorithmId, signedData);

	    	    //Create ContentInfo:
	    	    ContentInfo cInfo = new ContentInfo(ContentInfo.DIGESTED_DATA_OID, new DerValue(DerValue.tag_OctetString, dataToSign));

	    	    //Create PKCS7 Signed data
	    	    PKCS7 p7 = new PKCS7(new AlgorithmId[] { digestAlgorithmId }, cInfo,
	    	            new java.security.cert.X509Certificate[] { cert, },
	    	            new SignerInfo[] { sInfo });

	    	    //Write PKCS7 to bYteArray
	    	    ByteArrayOutputStream bOut = new DerOutputStream();
	    	    p7.encodeSignedData(bOut);
	    	    byte[] encoded = bOut.toByteArray();

	    	    print(encoded);*/
	    }
	
}