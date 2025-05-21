package com.StageArabSoft.OCR.Model;


public class DeclarationFiscale {
	
	private int num;
    private String tin;
    private String nomSociete;
    private String dateEntreeExploitation;
    private String activite;
    private String registreCommerce;
    private String dated;
    private String formeJuridique;
    private String divisionFiscale;
    private String code;
    private String dateDeclarationFiscale;
    private String periode;
    private String typeImpot;
    private String montantPrincipalUSD;
    private String penalites;
    private int declarationNulle;
    private int credit;
    private String montantCredit;
    private String total;
    private String groupe;
    private String ligne;
    private int typePropriete;
    private String valeurDeclaree;
    private String ancienneValeurEstimee;
    private String montantImpot;
    private int tauxImposition;
    private String adresse;
    private String montantAPayer;
    private String modePaiement;
	public DeclarationFiscale() {
		
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	public String getDateEntreeExploitation() {
		return dateEntreeExploitation;
	}
	public void setDateEntreeExploitation(String dateEntreeExploitation) {
		this.dateEntreeExploitation = dateEntreeExploitation;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
	public String getRegistreCommerce() {
		return registreCommerce;
	}
	public void setRegistreCommerce(String registreCommerce) {
		this.registreCommerce = registreCommerce;
	}
	public String getDated() {
		return dated;
	}
	public void setDated(String dated) {
		this.dated = dated;
	}
	public String getFormeJuridique() {
		return formeJuridique;
	}
	public void setFormeJuridique(String formeJuridique) {
		this.formeJuridique = formeJuridique;
	}
	public String getDivisionFiscale() {
		return divisionFiscale;
	}
	public void setDivisionFiscale(String divisionFiscale) {
		this.divisionFiscale = divisionFiscale;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDateDeclarationFiscale() {
		return dateDeclarationFiscale;
	}
	public void setDateDeclarationFiscale(String dateDeclarationFiscale) {
		this.dateDeclarationFiscale = dateDeclarationFiscale;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public String getTypeImpot() {
		return typeImpot;
	}
	public void setTypeImpot(String typeImpot) {
		this.typeImpot = typeImpot;
	}
	public String getMontantPrincipalUSD() {
		return montantPrincipalUSD;
	}
	public void setMontantPrincipalUSD(String montantPrincipalUSD) {
		this.montantPrincipalUSD = montantPrincipalUSD;
	}
	public String getPenalites() {
		return penalites;
	}
	public void setPenalites(String penalites) {
		this.penalites = penalites;
	}
	public int getDeclarationNulle() {
		return declarationNulle;
	}
	public void setDeclarationNulle(int declarationNulle) {
		this.declarationNulle = declarationNulle;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getMontantCredit() {
		return montantCredit;
	}
	public void setMontantCredit(String montantCredit) {
		this.montantCredit = montantCredit;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	public String getLigne() {
		return ligne;
	}
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}
	public int getTypePropriete() {
		return typePropriete;
	}
	public void setTypePropriete(int typePropriete) {
		this.typePropriete = typePropriete;
	}
	public String getValeurDeclaree() {
		return valeurDeclaree;
	}
	public void setValeurDeclaree(String valeurDeclaree) {
		this.valeurDeclaree = valeurDeclaree;
	}
	public String getAncienneValeurEstimee() {
		return ancienneValeurEstimee;
	}
	public void setAncienneValeurEstimee(String ancienneValeurEstimee) {
		this.ancienneValeurEstimee = ancienneValeurEstimee;
	}
	public String getMontantImpot() {
		return montantImpot;
	}
	public void setMontantImpot(String montantImpot) {
		this.montantImpot = montantImpot;
	}
	public int getTauxImposition() {
		return tauxImposition;
	}
	public void setTauxImposition(int tauxImposition) {
		this.tauxImposition = tauxImposition;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getMontantAPayer() {
		return montantAPayer;
	}
	public void setMontantAPayer(String montantAPayer) {
		this.montantAPayer = montantAPayer;
	}
	public String getModePaiement() {
		return modePaiement;
	}
	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}
	@Override
	public String toString() {
		return "DeclarationFiscale [num=" + num + ", tin=" + tin + ", nomSociete=" + nomSociete
				+ ", dateEntreeExploitation=" + dateEntreeExploitation + ", activite=" + activite
				+ ", registreCommerce=" + registreCommerce + ", dated=" + dated + ", formeJuridique=" + formeJuridique
				+ ", divisionFiscale=" + divisionFiscale + ", code=" + code + ", dateDeclarationFiscale="
				+ dateDeclarationFiscale + ", periode=" + periode + ", typeImpot=" + typeImpot
				+ ", montantPrincipalUSD=" + montantPrincipalUSD + ", penalites=" + penalites + ", declarationNulle="
				+ declarationNulle + ", credit=" + credit + ", montantCredit=" + montantCredit + ", total=" + total
				+ ", groupe=" + groupe + ", ligne=" + ligne + ", typePropriete=" + typePropriete + ", valeurDeclaree="
				+ valeurDeclaree + ", ancienneValeurEstimee=" + ancienneValeurEstimee + ", montantImpot=" + montantImpot
				+ ", tauxImposition=" + tauxImposition + ", adresse=" + adresse + ", montantAPayer=" + montantAPayer
				+ ", modePaiement=" + modePaiement + "]";
	}
	
	
	
	
	
	
	

}
