package com.StageArabSoft.OCR.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Component
public class ServiceStockage {
	
	private final Path emplacementStockage;
	
	@Autowired
	public ServiceStockage(Environment env) {
		 this.emplacementStockage = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files"))
			        .toAbsolutePath().normalize();		
		 try {
			 Files.createDirectories(this.emplacementStockage);
			 
		 } catch (Exception ex) {
			 throw new RuntimeException("impossible de creer le dossier ou le fichier télecharge va etre stocké");
		 }
	}

	public String storeFile(MultipartFile fichier) {
		String fichierName = 
				new Date().getTime() + "-file.pdf" ;
		
		try {
			
			Path emplacmentCible = this.emplacementStockage.resolve(fichierName);
			 Files.copy(fichier.getInputStream(), emplacmentCible, StandardCopyOption.REPLACE_EXISTING);
			 return fichierName;
		}catch (IOException ex) {
		      throw new RuntimeException("Impossible de Stocker " + fichierName, ex);
	    }
	}
	
	@Value("./uploads/files")
    private String uploadDir;

    public byte[] getFichierBytes(String filename) throws IOException {
        Path path = Paths.get(uploadDir).resolve(filename);
        return Files.readAllBytes(path);
    }
    
    public boolean ViderDossier() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(this.emplacementStockage)) {
            for (Path path : directoryStream) {
                Files.deleteIfExists(path);
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Impossible de supprimer les fichiers dans le dossier : " + this.emplacementStockage, e);
        }
    }

}
