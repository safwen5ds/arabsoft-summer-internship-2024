package com.StageArabSoft.OCR.Controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.StageArabSoft.OCR.Service.ServiceStockage;
import com.StageArabSoft.OCR.Service.ServiceTraitementTexte;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "http://localhost:4321") 
@Component
public class ControlleurUpload {

    @Value("http://localhost:8081/OCR")
    private String urlServiceOCR;
    private final ServiceStockage serviceStockage;
    private final RestTemplate restTemplate;
    private final ServiceTraitementTexte st;

    public ControlleurUpload(ServiceStockage serviceStockage, RestTemplate restTemplate) {
        this.serviceStockage = serviceStockage;
        this.restTemplate = restTemplate;
		this.st = new ServiceTraitementTexte();
    }

    @PostMapping
    public ResponseEntity<String> traiterUpload(@RequestParam("pdf") MultipartFile file) throws IOException {
        System.out.println("We are here !!!!");
        serviceStockage.ViderDossier();
        
        String nomFichier = serviceStockage.storeFile(file);
        
        System.out.println("Fichier stocké avec le nom: " + nomFichier);

        byte[] fichierBytes = serviceStockage.getFichierBytes(nomFichier);
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.set("Content-Type", "application/pdf");

        HttpEntity<ByteArrayResource> requestEntity = new HttpEntity<>(new ByteArrayResource(fichierBytes), headers);

        System.out.println("Envoi de la requête au service OCR à l'URL: " + urlServiceOCR);
        
        ResponseEntity<String> response = restTemplate.postForEntity(urlServiceOCR, requestEntity, String.class);
        
        serviceStockage.ViderDossier();
        
        if (response.getStatusCode() == HttpStatus.OK) {
            String ocrTexte = response.getBody();
            
            System.out.println("Texte OCR reçu: " + ocrTexte);
            
            st.setTexte(ocrTexte);
            st.traiterTexte();
            
            return new ResponseEntity<>("Texte traité avec succès: " + ocrTexte, HttpStatus.OK);
        } else {
            System.err.println("Erreur du service OCR: " + response.getStatusCode() + " - " + response.getBody());
            
            return new ResponseEntity<>("Erreur de traitement de texte", response.getStatusCode());
        }
       
    }
}
