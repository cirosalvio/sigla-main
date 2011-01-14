package it.cnr.contab.gestiva00.bp;
import it.cnr.contab.gestiva00.ejb.*;
import it.cnr.contab.gestiva00.core.bulk.*;
import it.cnr.jada.action.ActionContext;
import it.cnr.jada.action.BusinessProcessException;
import it.cnr.jada.bulk.OggettoBulk;
import it.cnr.jada.util.action.*;
import it.cnr.jada.util.ejb.EJBCommonServices;


public class LiquidazioneIvaBP extends StampaRegistriIvaBP {

	private int status = INSERT;
	private boolean liquidato = false;
	private final CompoundPropertyController dettaglio = new CompoundPropertyController("liquidazione_iva", Liquidazione_ivaBulk.class,"liquidazione_iva",this);

public LiquidazioneIvaBP() {
	super();
}

public LiquidazioneIvaBP(String function) {
	super(function);
}

/**
 * Restituisce il valore della proprietÓ 'dettaglio'
 *
 * @return Il valore della proprietÓ 'dettaglio'
 */
public final CompoundPropertyController getDettaglio() {
	return dettaglio;
}

/**
 * Restituisce il valore della proprietÓ 'liquidato'
 *
 * @return Il valore della proprietÓ 'liquidato'
 */
public boolean isLiquidato() {
	return liquidato;
}

/**
 * Imposta il valore della proprietÓ 'liquidato'
 *
 * @param newLiquidato	Il valore da assegnare a 'liquidato'
 */
public void setLiquidato(boolean newLiquidato) {
	liquidato = newLiquidato;
}
}