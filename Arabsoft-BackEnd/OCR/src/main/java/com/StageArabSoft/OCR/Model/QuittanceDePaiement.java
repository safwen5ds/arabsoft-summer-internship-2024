package com.StageArabSoft.OCR.Model;



public class QuittanceDePaiement {
	
	private String numeroRecuPaiement;
    private String numeroFacture;
    private String identifiant;
    private String codeActe;
    private String referenceActe;
    private String dateActe;
    private String natureActe;
    private String montantRegle;
    private String montantEnLettres;
    private String datePaiement;
    private String recuPar;
    private String operationEffectueePar;
    private String date;
    private String editeur;
    private String adresse;
    private String email;
    private String siteWeb;

    public QuittanceDePaiement() {
        // Empty constructor
    }

	public String getNumeroRecuPaiement() {
		return numeroRecuPaiement;
	}

	public void setNumeroRecuPaiement(String numeroRecuPaiement) {
		this.numeroRecuPaiement = numeroRecuPaiement;
	}

	public String getNumeroFacture() {
		return numeroFacture;
	}

	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getCodeActe() {
		return codeActe;
	}

	public void setCodeActe(String codeActe) {
		this.codeActe = codeActe;
	}

	public String getReferenceActe() {
		return referenceActe;
	}

	public void setReferenceActe(String referenceActe) {
		this.referenceActe = referenceActe;
	}

	public String getDateActe() {
		return dateActe;
	}

	public void setDateActe(String dateActe) {
		this.dateActe = dateActe;
	}

	public String getNatureActe() {
		return natureActe;
	}

	public void setNatureActe(String natureActe) {
		this.natureActe = natureActe;
	}

	public String getMontantRegle() {
		return montantRegle;
	}

	public void setMontantRegle(String montantRegle) {
		this.montantRegle = montantRegle;
	}

	public String getMontantEnLettres() {
		return montantEnLettres;
	}

	public void setMontantEnLettres(String montantEnLettres) {
		this.montantEnLettres = montantEnLettres;
	}

	public String getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(String datePaiement) {
		this.datePaiement = datePaiement;
	}

	public String getRecuPar() {
		return recuPar;
	}

	public void setRecuPar(String recuPar) {
		this.recuPar = recuPar;
	}

	public String getOperationEffectueePar() {
		return operationEffectueePar;
	}

	public void setOperationEffectueePar(String operationEffectueePar) {
		this.operationEffectueePar = operationEffectueePar;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	@Override
	public String toString() {
		return "QuittanceDePaiement [numeroRecuPaiement=" + numeroRecuPaiement + ", numeroFacture=" + numeroFacture
				+ ", identifiant=" + identifiant + ", codeActe=" + codeActe + ", referenceActe=" + referenceActe
				+ ", dateActe=" + dateActe + ", natureActe=" + natureActe + ", montantRegle=" + montantRegle
				+ ", montantEnLettres=" + montantEnLettres + ", datePaiement=" + datePaiement + ", recuPar=" + recuPar
				+ ", operationEffectueePar=" + operationEffectueePar + ", date=" + date + ", editeur=" + editeur
				+ ", adresse=" + adresse + ", email=" + email + ", siteWeb=" + siteWeb + "]";
	}
	
    
	
	
	
	


}
