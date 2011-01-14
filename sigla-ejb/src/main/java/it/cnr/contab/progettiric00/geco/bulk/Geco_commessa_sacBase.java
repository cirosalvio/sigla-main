/*
 * Created by Aurelio's BulkGenerator 1.0
 * Date 27/11/2006
 */
package it.cnr.contab.progettiric00.geco.bulk;
import it.cnr.jada.persistency.Keyed;
public class Geco_commessa_sacBase extends Geco_commessa_sacKey implements Keyed {
//    cod_comm VARCHAR(20)
	private java.lang.String cod_comm;
 
//    id_prog DECIMAL(10,0) NOT NULL
	private java.lang.Long id_prog;
 
//    tipo_comm DECIMAL(10,0)
	private java.lang.Long tipo_comm;
 
//    descr_comm VARCHAR(255) NOT NULL
	private java.lang.String descr_comm;
 
//    cds CHAR(3)
	private java.lang.String cds;
 
//    sede_svol_uo CHAR(3)
	private java.lang.String sede_svol_uo;
 
//    cod_tip DECIMAL(10,0)
	private java.lang.Long cod_tip;
 
//    cod_3rzo_resp VARCHAR(255)
	private java.lang.String cod_3rzo_resp;
 
//    matricola_resp DECIMAL(10,0)
	private java.lang.Long matricola_resp;
 
//    cognome_resp VARCHAR(255)
	private java.lang.String cognome_resp;
 
//    nome_resp VARCHAR(255)
	private java.lang.String nome_resp;
 
//    telefono_resp VARCHAR(50)
	private java.lang.String telefono_resp;
 
//    email_resp VARCHAR(255)
	private java.lang.String email_resp;
 
//    parola_chiave_1 VARCHAR(255)
	private java.lang.String parola_chiave_1;
 
//    parola_chiave_2 VARCHAR(255)
	private java.lang.String parola_chiave_2;
 
//    parola_chiave_3 VARCHAR(255)
	private java.lang.String parola_chiave_3;
 
//    descrittori_1 DECIMAL(10,0)
	private java.lang.Long descrittori_1;
 
//    descrittori_2 DECIMAL(10,0)
	private java.lang.Long descrittori_2;
 
//    descrittori_3 DECIMAL(10,0)
	private java.lang.Long descrittori_3;
 
//    esercizio_proposta DECIMAL(10,0)
	private java.lang.Long esercizio_proposta;
 
//    esercizio_inizio_attivita DECIMAL(10,0)
	private java.lang.Long esercizio_inizio_attivita;
 
//    data_inizio_attivita TIMESTAMP(7)
	private java.sql.Timestamp data_inizio_attivita;
 
//    stato DECIMAL(10,0)
	private java.lang.Long stato;
 
//    stato_att_scie DECIMAL(10,0)
	private java.lang.Long stato_att_scie;
 
//    stato_att_contab DECIMAL(10,0)
	private java.lang.Long stato_att_contab;
 
//    data_fine_attivita_scie TIMESTAMP(7)
	private java.sql.Timestamp data_fine_attivita_scie;
 
//    data_fine_attivita_contab TIMESTAMP(7)
	private java.sql.Timestamp data_fine_attivita_contab;
 
//    esercizio_chiusura DECIMAL(10,0)
	private java.lang.Long esercizio_chiusura;
 
//    codicefiscale_resp CHAR(16)
	private java.lang.String codicefiscale_resp;
 
	public Geco_commessa_sacBase() {
		super();
	}
	public Geco_commessa_sacBase(java.lang.Long id_comm, java.lang.Long esercizio, java.lang.String fase) {
		super(id_comm, esercizio, fase);
	}
	public java.lang.String getCod_comm() {
		return cod_comm;
	}
	public void setCod_comm(java.lang.String cod_comm)  {
		this.cod_comm=cod_comm;
	}
	public java.lang.Long getId_prog() {
		return id_prog;
	}
	public void setId_prog(java.lang.Long id_prog)  {
		this.id_prog=id_prog;
	}
	public java.lang.Long getTipo_comm() {
		return tipo_comm;
	}
	public void setTipo_comm(java.lang.Long tipo_comm)  {
		this.tipo_comm=tipo_comm;
	}
	public java.lang.String getDescr_comm() {
		return descr_comm;
	}
	public void setDescr_comm(java.lang.String descr_comm)  {
		this.descr_comm=descr_comm;
	}
	public java.lang.String getCds() {
		return cds;
	}
	public void setCds(java.lang.String cds)  {
		this.cds=cds;
	}
	public java.lang.String getSede_svol_uo() {
		return sede_svol_uo;
	}
	public void setSede_svol_uo(java.lang.String sede_svol_uo)  {
		this.sede_svol_uo=sede_svol_uo;
	}
	public java.lang.Long getCod_tip() {
		return cod_tip;
	}
	public void setCod_tip(java.lang.Long cod_tip)  {
		this.cod_tip=cod_tip;
	}
	public java.lang.String getCod_3rzo_resp() {
		return cod_3rzo_resp;
	}
	public void setCod_3rzo_resp(java.lang.String cod_3rzo_resp)  {
		this.cod_3rzo_resp=cod_3rzo_resp;
	}
	public java.lang.Long getMatricola_resp() {
		return matricola_resp;
	}
	public void setMatricola_resp(java.lang.Long matricola_resp)  {
		this.matricola_resp=matricola_resp;
	}
	public java.lang.String getCognome_resp() {
		return cognome_resp;
	}
	public void setCognome_resp(java.lang.String cognome_resp)  {
		this.cognome_resp=cognome_resp;
	}
	public java.lang.String getNome_resp() {
		return nome_resp;
	}
	public void setNome_resp(java.lang.String nome_resp)  {
		this.nome_resp=nome_resp;
	}
	public java.lang.String getTelefono_resp() {
		return telefono_resp;
	}
	public void setTelefono_resp(java.lang.String telefono_resp)  {
		this.telefono_resp=telefono_resp;
	}
	public java.lang.String getEmail_resp() {
		return email_resp;
	}
	public void setEmail_resp(java.lang.String email_resp)  {
		this.email_resp=email_resp;
	}
	public java.lang.String getParola_chiave_1() {
		return parola_chiave_1;
	}
	public void setParola_chiave_1(java.lang.String parola_chiave_1)  {
		this.parola_chiave_1=parola_chiave_1;
	}
	public java.lang.String getParola_chiave_2() {
		return parola_chiave_2;
	}
	public void setParola_chiave_2(java.lang.String parola_chiave_2)  {
		this.parola_chiave_2=parola_chiave_2;
	}
	public java.lang.String getParola_chiave_3() {
		return parola_chiave_3;
	}
	public void setParola_chiave_3(java.lang.String parola_chiave_3)  {
		this.parola_chiave_3=parola_chiave_3;
	}
	public java.lang.Long getDescrittori_1() {
		return descrittori_1;
	}
	public void setDescrittori_1(java.lang.Long descrittori_1)  {
		this.descrittori_1=descrittori_1;
	}
	public java.lang.Long getDescrittori_2() {
		return descrittori_2;
	}
	public void setDescrittori_2(java.lang.Long descrittori_2)  {
		this.descrittori_2=descrittori_2;
	}
	public java.lang.Long getDescrittori_3() {
		return descrittori_3;
	}
	public void setDescrittori_3(java.lang.Long descrittori_3)  {
		this.descrittori_3=descrittori_3;
	}
	public java.lang.Long getEsercizio_proposta() {
		return esercizio_proposta;
	}
	public void setEsercizio_proposta(java.lang.Long esercizio_proposta)  {
		this.esercizio_proposta=esercizio_proposta;
	}
	public java.lang.Long getEsercizio_inizio_attivita() {
		return esercizio_inizio_attivita;
	}
	public void setEsercizio_inizio_attivita(java.lang.Long esercizio_inizio_attivita)  {
		this.esercizio_inizio_attivita=esercizio_inizio_attivita;
	}
	public java.sql.Timestamp getData_inizio_attivita() {
		return data_inizio_attivita;
	}
	public void setData_inizio_attivita(java.sql.Timestamp data_inizio_attivita)  {
		this.data_inizio_attivita=data_inizio_attivita;
	}
	public java.lang.Long getStato() {
		return stato;
	}
	public void setStato(java.lang.Long stato)  {
		this.stato=stato;
	}
	public java.lang.Long getStato_att_scie() {
		return stato_att_scie;
	}
	public void setStato_att_scie(java.lang.Long stato_att_scie)  {
		this.stato_att_scie=stato_att_scie;
	}
	public java.lang.Long getStato_att_contab() {
		return stato_att_contab;
	}
	public void setStato_att_contab(java.lang.Long stato_att_contab)  {
		this.stato_att_contab=stato_att_contab;
	}
	public java.sql.Timestamp getData_fine_attivita_scie() {
		return data_fine_attivita_scie;
	}
	public void setData_fine_attivita_scie(java.sql.Timestamp data_fine_attivita_scie)  {
		this.data_fine_attivita_scie=data_fine_attivita_scie;
	}
	public java.sql.Timestamp getData_fine_attivita_contab() {
		return data_fine_attivita_contab;
	}
	public void setData_fine_attivita_contab(java.sql.Timestamp data_fine_attivita_contab)  {
		this.data_fine_attivita_contab=data_fine_attivita_contab;
	}
	public java.lang.Long getEsercizio_chiusura() {
		return esercizio_chiusura;
	}
	public void setEsercizio_chiusura(java.lang.Long esercizio_chiusura)  {
		this.esercizio_chiusura=esercizio_chiusura;
	}
	public java.lang.String getCodicefiscale_resp() {
		return codicefiscale_resp;
	}
	public void setCodicefiscale_resp(java.lang.String codicefiscale_resp)  {
		this.codicefiscale_resp=codicefiscale_resp;
	}
}