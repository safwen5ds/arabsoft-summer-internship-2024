package com.StageArabSoft.OCR.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.StageArabSoft.OCR.Model.AttestationImmatriculation;
import com.StageArabSoft.OCR.Model.DeclarationFiscale;
import com.StageArabSoft.OCR.Model.QuittanceDePaiement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;

@Service
@Component
public class ServiceTraitementTexte {
    private String texte;
    private MongoClient mongoClient;
    private MongoDatabase db;

    public ServiceTraitementTexte() {
        this.texte = "";
        mongoClient = MongoClients.create("mongodb://localhost:27017");
		System.out.println("Created Mongo Connection successfully");
		
		db = mongoClient.getDatabase("Gestion_Des_impots_Arabsoft");
		System.out.println("Get database is successful");


    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
    
    public void traiterAttestation() throws JsonProcessingException
    {
    	 AttestationImmatriculation at = new AttestationImmatriculation();

         String[][] patterns = {
             {"org", "Org\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"natureDeLaDemande", "Nature of request\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"codeDemande", "Request Code\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"etatDemande", "Status of application\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"dateDebutValidite", "Effective Start Date\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"dateFinValidite", "Effective End Date\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"objetDemande", "Subject of request\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"observation", "Observation\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"tin", "TIN\\s*:\\s*(\\d+?)\\s*(?=\\n|$)"},
             {"nomSociete", "Name or Company\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"prenomBusiness", "First name / Business\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"date", "Date\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
             {"editeur", "Editor\\s*:\\s*(\\w+)"}
         };

         for (String[] pattern : patterns) {
             String key = pattern[0];
             String regex = pattern[1];
             Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
             Matcher m = p.matcher(texte);

             String value = m.find() ? m.group(1).trim() : "None";

             switch (key) {
                 case "org": at.setOrg(value); break;
                 case "natureDeLaDemande": at.setNatureDeLaDemande(value); break;
                 case "codeDemande": at.setCodeDemande(value); break;
                 case "etatDemande": at.setEtatDemande(value); break;
                 case "dateDebutValidite": at.setDateDebutValidite(value); break;
                 case "dateFinValidite": at.setDateFinValidite(value); break;
                 case "objetDemande": at.setObjetDemande(value); break;
                 case "observation": at.setObservation(value); break;
                 case "tin": at.setTin(Integer.parseInt(value)); break;
                 case "nomSociete": at.setNomSociete(value); break;
                 case "prenomBusiness": at.setPrenomBusiness(value); break;
                 case "date": at.setDate(value); break;
                 case "editeur": at.setEditeur(value); break;
             }
         }

         System.out.println(at.toString());
     




         ObjectMapper Obj = new ObjectMapper();  
         Map<String, Object> jsonMap = Obj.convertValue(at, new TypeReference<Map<String, Object>>(){});

         Document document = new Document("Attestation", jsonMap);

         MongoCollection<Document> collection = db.getCollection("Data");
         collection.insertOne(document);

         System.out.println("########### Insert OK  ###############");



    }
    
    public void traiterFiscale() throws JsonProcessingException
    {
    	 DeclarationFiscale df = new DeclarationFiscale();

    	 String[][] patterns = {
                 {"num", "No:\\s*(\\d+)"},
                 {"tin", "TIN\\s*:\\s*(\\S+)"},
                 {"nomSociete", "Name / Company name\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"dateEntreeExploitation", "Date of entry into operation\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"activite", "Activity\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"registreCommerce", "Business register\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"dated", "Dated\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"formeJuridique", "Legal form\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"divisionFiscale", "Tax Division\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"code", "Code\\s*:\\s*(\\d+)"},
                 {"dateDeclarationFiscale", "Date of Tax return\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"periode", "Period\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"typeImpot", "Kind of tax\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"montantPrincipalUSD", "Principal Amount USD\\s*:\\s*\\$([\\d,]+\\.\\d{2}) USD"},
                 {"penalites", "Penalties\\s*:\\s*\\$([\\d,]+\\.\\d{2}) USD"},
                 {"declarationNulle", "Nil Return\\s*:\\s*(YES|NO)"},
                 {"credit", "Credit\\s*:\\s*(YES|NO)"},
                 {"montantCredit", "Amount of credit\\s*:\\s*\\$([\\d,]+\\.\\d{2}) USD"},
                 {"total", "Total\\s*:\\s*\\$([\\d,]+\\.\\d{2}) USD"},
                 {"groupe", "Group\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"ligne", "Line\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"typePropriete", "A. Property Type\\s*:\\s*(\\d+)"},
                 {"valeurDeclaree", "B. Declared Value\\s*:\\s*(\\d+)"},
                 {"ancienneValeurEstimee", "Old Estimated Value\\s*:\\s*(\\d+)"},
                 {"montantImpot", "Tax Amount\\s*:\\s*(\\d+)"},
                 {"tauxImposition", "Tax Rate\\s*:\\s*(\\d+)"},
                 {"adresse", "Address\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                 {"montantAPayer", "Amount on hand to pay\\s*:\\s*\\$([\\d,]+\\.\\d{2}) USD"},
                 {"modePaiement", "Payment Method\\s*:\\s*(.+?)\\s*(?=\\n|$)"}
         };

         for (String[] pattern : patterns) {
             String key = pattern[0];
             String regex = pattern[1];
             Pattern p = Pattern.compile(regex);
             Matcher m = p.matcher(texte);

             if (m.find()) {
                 String value = m.group(1).trim();
                 switch (key) {
                     case "num":
                         df.setNum(Integer.parseInt(value));
                         break;
                     case "tin":
                         df.setTin(value);
                         break;
                     case "nomSociete":
                         df.setNomSociete(value);
                         break;
                     case "dateEntreeExploitation":
                         df.setDateEntreeExploitation(value);
                         break;
                     case "activite":
                         df.setActivite(value);
                         break;
                     case "registreCommerce":
                         df.setRegistreCommerce(value);
                         break;
                     case "dated":
                         df.setDated(value);
                         break;
                     case "formeJuridique":
                         df.setFormeJuridique(value);
                         break;
                     case "divisionFiscale":
                         df.setDivisionFiscale(value);
                         break;
                     case "code":
                         df.setCode(value);
                         break;
                     case "dateDeclarationFiscale":
                         df.setDateDeclarationFiscale(value);
                         break;
                     case "periode":
                         df.setPeriode(value);
                         break;
                     case "typeImpot":
                         df.setTypeImpot(value);
                         break;
                     case "montantPrincipalUSD":
                         df.setMontantPrincipalUSD(value);
                         break;
                     case "penalites":
                         df.setPenalites(value);
                         break;
                     case "declarationNulle":
                         df.setDeclarationNulle("YES".equalsIgnoreCase(value) ? 1 : 0);
                         break;
                     case "credit":
                         df.setCredit("YES".equalsIgnoreCase(value) ? 1 : 0);
                         break;
                     case "montantCredit":
                         df.setMontantCredit(value);
                         break;
                     case "total":
                         df.setTotal(value);
                         break;
                     case "groupe":
                         df.setGroupe(value);
                         break;
                     case "ligne":
                         df.setLigne(value);
                         break;
                     case "typePropriete":
                         df.setTypePropriete(Integer.parseInt(value));
                         break;
                     case "valeurDeclaree":
                         df.setValeurDeclaree(value);
                         break;
                     case "ancienneValeurEstimee":
                         df.setAncienneValeurEstimee(value);
                         break;
                     case "montantImpot":
                         df.setMontantImpot(value);
                         break;
                     case "tauxImposition":
                         df.setTauxImposition(Integer.parseInt(value));
                         break;
                     case "adresse":
                         df.setAdresse(value);
                         break;
                     case "montantAPayer":
                         df.setMontantAPayer(value);
                         break;
                     case "modePaiement":
                         df.setModePaiement(value);
                         break;
                 }
             }
         }


         System.out.println(df.toString());




         ObjectMapper Obj = new ObjectMapper();  
         Map<String, Object> jsonMap = Obj.convertValue(df, new TypeReference<Map<String, Object>>(){});

         Document document = new Document("Fiscale", jsonMap);

         MongoCollection<Document> collection = db.getCollection("Data");
         collection.insertOne(document);

         System.out.println("########### Insert OK  ###############");
    }
    
    public void traiterQuittance() throws JsonProcessingException
    {
        QuittanceDePaiement qp = new QuittanceDePaiement ();

        String[][] patterns = {
                {"numeroRecuPaiement", "Payment Receipt\\s*:\\s*(\\d+/\\d+)"},
                {"numeroFacture", "Bill number\\s*:\\s*(\\d+)"},
                {"identifiant", "Receipt Identifier\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"codeActe", "Act Code\\s*:\\s*(\\d+)"},
                {"referenceActe", "Act reference\\s*:\\s*(\\d+)"},
                {"dateActe", "Act date\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"natureActe", "Kind of Act\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"montantRegle", "Amount settled\\s*:\\s*\\$(\\d+\\.\\d{2}) USD"},
                {"montantEnLettres", "Amount in words\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"datePaiement", "Date of payment\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"recuPar", "Received by\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"operationEffectueePar", "Operation performed by\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"date", "Date\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"editeur", "Editor\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"adresse", "Address\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"email", "Email\\s*:\\s*(.+?)\\s*(?=\\n|$)"},
                {"siteWeb", "website\\s*:\\s*(http[^\n]+)"}
        };

        for (String[] pattern : patterns) {
            String key = pattern[0];
            String regex = pattern[1];
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(texte);

            if (m.find()) {
                String value = m.group(1).trim();
                switch (key) {
                    case "numeroRecuPaiement":
                        qp.setNumeroRecuPaiement(value);
                        break;
                    case "numeroFacture":
                        qp.setNumeroFacture(value);
                        break;
                    case "identifiant":
                        qp.setIdentifiant(value);
                        break;
                    case "codeActe":
                        qp.setCodeActe(value);
                        break;
                    case "referenceActe":
                        qp.setReferenceActe(value);
                        break;
                    case "dateActe":
                        qp.setDateActe(value);
                        break;
                    case "natureActe":
                        qp.setNatureActe(value);
                        break;
                    case "montantRegle":
                        qp.setMontantRegle(value);
                        break;
                    case "montantEnLettres":
                        qp.setMontantEnLettres(value);
                        break;
                    case "datePaiement":
                        qp.setDatePaiement(value);
                        break;
                    case "recuPar":
                        qp.setRecuPar(value);
                        break;
                    case "operationEffectueePar":
                        qp.setOperationEffectueePar(value);
                        break;
                    case "date":
                        qp.setDate(value);
                        break;
                    case "editeur":
                        qp.setEditeur(value);
                        break;
                    case "adresse":
                        qp.setAdresse(value);
                        break;
                    case "email":
                        qp.setEmail(value);
                        break;
                    case "siteWeb":
                        qp.setSiteWeb(value);
                        break;
                }
            }
        }


        System.out.println(qp.toString());




        ObjectMapper Obj = new ObjectMapper();  
        Map<String, Object> jsonMap = Obj.convertValue(qp, new TypeReference<Map<String, Object>>(){});

        Document document = new Document("Quittance", jsonMap);

        MongoCollection<Document> collection = db.getCollection("Data");
        collection.insertOne(document);

        System.out.println("########### Insert OK  ###############");

    }
    
    public void traiterFactures() throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();  
        String Texte = this.texte.replaceFirst("^```json\\s*", "").replaceFirst("\\s*```$", "");

        Map<String, Object> jsonMap = Obj.readValue(Texte, new TypeReference<Map<String, Object>>(){});

        Document document = new Document("Facture", jsonMap);

        MongoCollection<Document> collection = db.getCollection("Data");
        collection.insertOne(document);

        System.out.println("########### Insert OK  ###############");
    }

    
    public void traiterTexte() throws JsonProcessingException{
    	if (this.texte.toLowerCase().contains("Acknowledgement Receipt".toLowerCase())) {
    		traiterAttestation();
    	
    	}else if  (this.texte.toLowerCase().contains("Receipt of tax return".toLowerCase())) {
    		traiterFiscale();
    		
    	}else if  (this.texte.toLowerCase().contains("Payment Receipt".toLowerCase())) {
    		traiterQuittance();
    		
    	}else {
    		traiterFactures();
    	}
    }
    
  



    
    
    
    
}
