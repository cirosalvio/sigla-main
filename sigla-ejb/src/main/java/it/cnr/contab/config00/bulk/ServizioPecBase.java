/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 14/05/2010
 */
package it.cnr.contab.config00.bulk;
import it.cnr.jada.persistency.Keyed;
public class ServizioPecBase extends ServizioPecKey implements Keyed {
//    DS_SERVIZIO VARCHAR(100) NOT NULL
	private java.lang.String dsServizio;
 
//    EMAIL VARCHAR(100) NOT NULL
	private java.lang.String email;
 
//    EMAIL2 VARCHAR(100)
	private java.lang.String email2;
 
//    EMAIL3 VARCHAR(100)
	private java.lang.String email3;
 
//    EMAIL_TEST VARCHAR(100)
	private java.lang.String emailTest;
 
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: SERVIZIO_PEC
	 **/
	public ServizioPecBase() {
		super();
	}
	public ServizioPecBase(java.lang.String cdServizio) {
		super(cdServizio);
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [dsServizio]
	 **/
	public java.lang.String getDsServizio() {
		return dsServizio;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [dsServizio]
	 **/
	public void setDsServizio(java.lang.String dsServizio)  {
		this.dsServizio=dsServizio;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [email]
	 **/
	public java.lang.String getEmail() {
		return email;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [email]
	 **/
	public void setEmail(java.lang.String email)  {
		this.email=email;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [email2]
	 **/
	public java.lang.String getEmail2() {
		return email2;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [email2]
	 **/
	public void setEmail2(java.lang.String email2)  {
		this.email2=email2;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [email3]
	 **/
	public java.lang.String getEmail3() {
		return email3;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [email3]
	 **/
	public void setEmail3(java.lang.String email3)  {
		this.email3=email3;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Restituisce il valore di: [emailTest]
	 **/
	public java.lang.String getEmailTest() {
		return emailTest;
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Setta il valore di: [emailTest]
	 **/
	public void setEmailTest(java.lang.String emailTest)  {
		this.emailTest=emailTest;
	}
}