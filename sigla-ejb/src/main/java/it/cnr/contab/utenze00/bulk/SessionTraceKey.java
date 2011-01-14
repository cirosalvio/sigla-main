/*
 * Created by BulkGenerator 1.5 [30/07/2008]
 * Date 31/07/2008
 */
package it.cnr.contab.utenze00.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.bulk.annotation.FieldPropertyAnnotation;
import it.cnr.jada.bulk.annotation.InputType;
import it.cnr.jada.persistency.KeyedPersistent;
public class SessionTraceKey extends OggettoBulk implements KeyedPersistent {
	@FieldPropertyAnnotation(name="idSessione",
					inputType=InputType.TEXTAREA,
					cols=60,
					rows=5, 
					maxLength=100,
					enabledOnSearch=false,
					nullable=false,
					label="Session Id")
	private java.lang.String id_sessione;
	/**
	 * Created by BulkGenerator 1.5 [30/07/2008]
	 * Table name: SESSION_TRACE
	 **/
	public SessionTraceKey() {
		super();
	}
	public SessionTraceKey(java.lang.String id_sessione) {
		super();
		this.id_sessione=id_sessione;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof SessionTraceKey)) return false;
		SessionTraceKey k = (SessionTraceKey) o;
		if (!compareKey(getId_sessione(), k.getId_sessione())) return false;
		return true;
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getId_sessione());
		return i;
	}
	/**
	 * Created by BulkGenerator 1.5 [30/07/2008]
	 * Restituisce il valore di: [id_sessione]
	 **/
	public void setId_sessione(java.lang.String id_sessione)  {
		this.id_sessione=id_sessione;
	}
	/**
	 * Created by BulkGenerator 1.5 [30/07/2008]
	 * Setta il valore di: [id_sessione]
	 **/
	public java.lang.String getId_sessione() {
		return id_sessione;
	}
}