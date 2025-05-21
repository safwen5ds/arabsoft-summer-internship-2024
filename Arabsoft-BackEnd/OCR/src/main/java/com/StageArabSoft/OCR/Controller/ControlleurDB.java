package com.StageArabSoft.OCR.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mongodb.client.MongoCollection;
import com.StageArabSoft.OCR.Model.AttestationImmatriculation;
import com.StageArabSoft.OCR.Repository.AttestationInterface;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/controlleurdb")
@CrossOrigin(origins = "http://localhost:4321")
@Component
public class ControlleurDB {

    private static final Logger logger = Logger.getLogger(ControlleurDB.class.getName());
    private final MongoCollection<Document> collection;
    @Autowired
    AttestationInterface ATRepository;
    
    @Autowired
    public ControlleurDB() {
        logger.info("Etablissement de la connexion avec MongoDB...");
        var client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("Gestion_Des_impots_Arabsoft");
        this.collection = database.getCollection("Data");
        logger.info("La connexion avec MongoDB a été reussi , Nom de base de données : Gestion_Des_impots_Arabsoft");
    }

    @GetMapping("/attestation/{tin}")
    public ResponseEntity<AttestationImmatriculation> getAttestationByTin(@PathVariable("tin") Integer tin) {
        System.out.println("Requête reçue pour récupérer l'attestation pour TIN : " + tin);
        
        Optional<AttestationImmatriculation> attestation = ATRepository.findById(tin);
        if (attestation.isPresent()) {
        	System.out.println("Attestation trouvée !!! | Attestation : " + attestation.get());
        } else {
        	System.out.println("Aucune attestation trouvée avec le TIN : " + tin);
        }
        return attestation.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/attestationall")
    public List<AttestationImmatriculation> getAllAttestations() {
        System.out.println("Récupération de toutes les attestations depuis MongoDB !!!!");
        List<AttestationImmatriculation> attestations = new ArrayList<>();
        for (Document doc : collection.find(new Document("Attestation", new Document("$exists", true)))) {
            Document attestationDoc = (Document) doc.get("Attestation");
            AttestationImmatriculation attestation = new AttestationImmatriculation();
            attestation.setOrg(attestationDoc.getString("org"));
            attestation.setNatureDeLaDemande(attestationDoc.getString("natureDeLaDemande"));
            attestation.setCodeDemande(attestationDoc.getString("codeDemande"));
            attestation.setEtatDemande(attestationDoc.getString("etatDemande"));
            attestation.setDateDebutValidite(attestationDoc.getString("dateDebutValidite"));
            attestation.setDateFinValidite(attestationDoc.getString("dateFinValidite"));
            attestation.setObjetDemande(attestationDoc.getString("objetDemande"));
            attestation.setObservation(attestationDoc.getString("observation"));
            attestation.setTin(attestationDoc.getInteger("tin"));
            attestation.setNomSociete(attestationDoc.getString("nomSociete"));
            attestation.setPrenomBusiness(attestationDoc.getString("prenomBusiness"));
            attestation.setDate(attestationDoc.getString("date"));
            attestation.setEditeur(attestationDoc.getString("editeur"));
            attestations.add(attestation);
        }
        return attestations;
    }
    
    @PutMapping("/attestationupdate/{tin}")
    public ResponseEntity<AttestationImmatriculation> AttestationUpdate(@PathVariable("tin") Integer tin, @RequestBody AttestationImmatriculation attestation) {
        System.out.println("Requête reçue pour mettre à jour l'attestation avec TIN : " + tin);
        System.out.println("Contenu : " + attestation.toString());

        Document filtre = new Document("Attestation.tin", tin);
        
        Document document = collection.find(filtre).first(); 

        if (document != null) {
        	System.out.println("Attestation trouvée pour la mise à jour : " + document.toJson());

            Document at= new Document("org", attestation.getOrg())
                    .append("natureDeLaDemande", attestation.getNatureDeLaDemande())
                    .append("codeDemande", attestation.getCodeDemande())
                    .append("etatDemande", attestation.getEtatDemande())
                    .append("dateDebutValidite", attestation.getDateDebutValidite())
                    .append("dateFinValidite", attestation.getDateFinValidite())
                    .append("objetDemande", attestation.getObjetDemande())
                    .append("observation", attestation.getObservation())
                    .append("tin", attestation.getTin())
                    .append("nomSociete", attestation.getNomSociete())
                    .append("prenomBusiness", attestation.getPrenomBusiness())
                    .append("date", attestation.getDate())
                    .append("editeur", attestation.getEditeur());

            Document document_mise_a_jour = new Document("$set", new Document("Attestation", at));
            collection.updateOne(filtre, document_mise_a_jour);

            System.out.println("Données de l'attestation mises à jour dans MongoDB : " + at.toJson());

            AttestationImmatriculation attestation_reponse = new AttestationImmatriculation();
            attestation_reponse.setOrg(at.getString("org"));
            attestation_reponse.setNatureDeLaDemande(at.getString("natureDeLaDemande"));
            attestation_reponse.setCodeDemande(at.getString("codeDemande"));
            attestation_reponse.setEtatDemande(at.getString("etatDemande"));
            attestation_reponse.setDateDebutValidite(at.getString("dateDebutValidite"));
            attestation_reponse.setDateFinValidite(at.getString("dateFinValidite"));
            attestation_reponse.setObjetDemande(at.getString("objetDemande"));
            attestation_reponse.setObservation(at.getString("observation"));

            attestation_reponse.setTin(attestation.getTin());

            attestation_reponse.setNomSociete(at.getString("nomSociete"));
            attestation_reponse.setPrenomBusiness(at.getString("prenomBusiness"));
            attestation_reponse.setDate(at.getString("date"));
            attestation_reponse.setEditeur(at.getString("editeur"));

            return new ResponseEntity<>(attestation_reponse, HttpStatus.OK);
        } else {
        	System.out.println("Attestation non trouvée pour TIN : " + tin);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/delete/{tin}")
    public ResponseEntity<HttpStatus> deleteAttestation(@PathVariable("tin") Integer tin) {
    	System.out.println("Requete recue pour attestation avec  TIN : " + tin);
        try {
            Document filtre = new Document("Attestation.tin", tin);
            Document document = collection.find(filtre).first(); 
            
            if (document != null) {
            	System.out.println("L'attestation à supprimer a été trouvé : " + document.toJson());

                collection.deleteOne(filtre);
                System.out.println("L'attestation supprimée avec succès avec TIN : " + tin);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
            	System.out.println("Attestation non trouvée avec TIN : " + tin);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        	System.out.println("Erreur lors de la suppression de l'attestation pour le TIN : " + tin + " | Erreur : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
