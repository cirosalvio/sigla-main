/*
* Created by Generator 1.0
* Date 01/03/2006
*/
package it.cnr.contab.inventario01.bulk;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.persistency.KeyedPersistent;
public class Inventario_beni_apgKey extends OggettoBulk implements KeyedPersistent {
	private java.lang.Long pg_riga;
//  LOCAL_TRANSACTION_ID VARCHAR(200) NOT NULL
	private java.lang.String local_transaction_id;
	public Inventario_beni_apgKey() {
		super();
	}
	
	public Inventario_beni_apgKey(Long riga,String id) {
		
		super();
		this.pg_riga=riga;
		this.local_transaction_id=id;
	}
	public boolean equalsByPrimaryKey(Object o) {
		if (this== o) return true;
		if (!(o instanceof Inventario_beni_apgKey)) return false;
		Inventario_beni_apgKey k = (Inventario_beni_apgKey) o;
		if (!compareKey(getPg_riga(), k.getPg_riga())) return false;
		if (!compareKey(getLocal_transaction_id(), k.getLocal_transaction_id())) return false;
			return true;
			
	}
	public int primaryKeyHashCode() {
		int i = 0;
		i = i + calculateKeyHashCode(getPg_riga());
		return i;
	}
	public void setPg_riga(java.lang.Long pg_riga)  {
		this.pg_riga=pg_riga;
	}
	public java.lang.Long getPg_riga () {
		return pg_riga;
	}

	public java.lang.String getLocal_transaction_id() {
		return local_transaction_id;
	}

	public void setLocal_transaction_id(java.lang.String local_transaction_id) {
		this.local_transaction_id = local_transaction_id;
	}
}