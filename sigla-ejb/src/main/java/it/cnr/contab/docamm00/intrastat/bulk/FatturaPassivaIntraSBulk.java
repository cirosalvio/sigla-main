/*
 * Created by BulkGenerator 2.0 [07/12/2009]
 * Date 14/06/2010
 */
package it.cnr.contab.docamm00.intrastat.bulk;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.util.action.CRUDBP;
public class FatturaPassivaIntraSBulk extends FatturaPassivaIntraSBase {
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: FATTURA_PASSIVA_INTRA_S
	 **/
	public FatturaPassivaIntraSBulk() {
		super();
	}
	/**
	 * Created by BulkGenerator 2.0 [07/12/2009]
	 * Table name: FATTURA_PASSIVA_INTRA_S
	 **/
	public FatturaPassivaIntraSBulk(java.lang.String cdCds, java.lang.String cdUnitaOrganizzativa, java.lang.Integer esercizio, java.lang.Long pgFatturaPassiva, java.lang.Long pgRigaIntra, java.lang.Long pgStorico) {
		super(cdCds, cdUnitaOrganizzativa, esercizio, pgFatturaPassiva, pgRigaIntra, pgStorico);
	}
	
}