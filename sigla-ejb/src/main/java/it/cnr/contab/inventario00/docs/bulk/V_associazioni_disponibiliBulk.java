/*
* Created by Generator 1.0
* Date 30/08/2005
*/
package it.cnr.contab.inventario00.docs.bulk;
import java.util.Dictionary;

public class V_associazioni_disponibiliBulk extends V_associazioni_disponibiliBase {
  public final static java.util.Dictionary ti_movimento;
  
    public final static String CARICO      = "Carico";
	public final static String SCARICO     = "Scarico";

	static {
			
			ti_movimento = new it.cnr.jada.util.OrderedHashtable();
			ti_movimento.put(it.cnr.contab.inventario01.bulk.Buono_carico_scaricoBulk.CARICO,CARICO);
			ti_movimento.put(it.cnr.contab.inventario01.bulk.Buono_carico_scaricoBulk.SCARICO,SCARICO);
		};
	public V_associazioni_disponibiliBulk() {
		super();
	}
	public Dictionary getTipoMovimentoKey(){
		return ti_movimento;
	}
	
}