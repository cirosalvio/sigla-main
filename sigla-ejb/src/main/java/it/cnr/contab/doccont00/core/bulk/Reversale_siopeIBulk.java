/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 07/05/2007
 */
package it.cnr.contab.doccont00.core.bulk;

public class Reversale_siopeIBulk extends Reversale_siopeBulk {

	protected Reversale_rigaIBulk reversale_rigaI;
	
	public Reversale_siopeIBulk() {
		super();
	}

	public Reversale_siopeIBulk(java.lang.String cd_cds, java.lang.Integer esercizio, java.lang.Long pg_mandato, java.lang.Integer esercizio_obbligazione, java.lang.Integer esercizio_ori_obbligazione, java.lang.Long pg_obbligazione, java.lang.Long pg_obbligazione_scadenzario, java.lang.String cd_cds_doc_amm, java.lang.String cd_uo_doc_amm, java.lang.Integer esercizio_doc_amm, java.lang.String cd_tipo_documento_amm, java.lang.Long pg_doc_amm, java.lang.Integer esercizio_siope, java.lang.String ti_gestione, java.lang.String cd_siope) {
		super(cd_cds, esercizio, pg_mandato, esercizio_obbligazione, esercizio_ori_obbligazione, pg_obbligazione, pg_obbligazione_scadenzario, cd_cds_doc_amm, cd_uo_doc_amm, esercizio_doc_amm, cd_tipo_documento_amm, pg_doc_amm, esercizio_siope, ti_gestione, cd_siope);
	}

	public Reversale_rigaBulk getReversale_riga() {
		return getReversale_rigaI();
	}	
	
	public Reversale_rigaIBulk getReversale_rigaI() {
		return reversale_rigaI;
	}	
	
	public void setReversale_riga(Reversale_rigaBulk newReversale_riga) {
		setReversale_rigaI((Reversale_rigaIBulk)newReversale_riga);
	}

	public void setReversale_rigaI(Reversale_rigaIBulk newReversale_rigaI) {
		reversale_rigaI = newReversale_rigaI;
	}
}