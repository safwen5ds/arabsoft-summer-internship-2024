package com.StageArabSoft.OCR.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Data")
@Component
public class AttestationImmatriculation {
	
	private String org;
	private String natureDeLaDemande;
	private String codeDemande;
	private String etatDemande;
	private String dateDebutValidite;
	private String dateFinValidite;
	private String objetDemande;
	private String observation;
	@Id
	private int tin;
	private String nomSociete;
	private String prenomBusiness;
	private String date;
	private String editeur;
	public AttestationImmatriculation() {
	
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getNatureDeLaDemande() {
		return natureDeLaDemande;
	}
	public void setNatureDeLaDemande(String natureDeLaDemande) {
		this.natureDeLaDemande = natureDeLaDemande;
	}
	public String getCodeDemande() {
		return codeDemande;
	}
	public void setCodeDemande(String codeDemande) {
		this.codeDemande = codeDemande;
	}
	public String getEtatDemande() {
		return etatDemande;
	}
	public void setEtatDemande(String etatDemande) {
		this.etatDemande = etatDemande;
	}
	public String getDateDebutValidite() {
		return dateDebutValidite;
	}
	public void setDateDebutValidite(String dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}
	public String getDateFinValidite() {
		return dateFinValidite;
	}
	public void setDateFinValidite(String dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}
	public String getObjetDemande() {
		return objetDemande;
	}
	public void setObjetDemande(String objetDemande) {
		this.objetDemande = objetDemande;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public int getTin() {
		return tin;
	}
	public void setTin(int tin) {
		this.tin = tin;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	public String getPrenomBusiness() {
		return prenomBusiness;
	}
	public void setPrenomBusiness(String prenomBusiness) {
		this.prenomBusiness = prenomBusiness;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEditeur() {
		return editeur;
	}
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}
	@Override
	public String toString() {
		return "AttestationImmatriculation [org=" + org + ", natureDeLaDemande=" + natureDeLaDemande + ", codeDemande="
				+ codeDemande + ", etatDemande=" + etatDemande + ", dateDebutValidite=" + dateDebutValidite
				+ ", dateFinValidite=" + dateFinValidite + ", objetDemande=" + objetDemande + ", observation="
				+ observation + ", tin=" + tin + ", nomSociete=" + nomSociete + ", prenomBusiness=" + prenomBusiness
				+ ", date=" + date + ", editeur=" + editeur + "]";
	}
	
	
	
	
	
	
	
	

}