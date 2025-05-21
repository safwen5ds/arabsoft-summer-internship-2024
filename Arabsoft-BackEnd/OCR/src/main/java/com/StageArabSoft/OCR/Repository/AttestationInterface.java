package com.StageArabSoft.OCR.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.StageArabSoft.OCR.Model.AttestationImmatriculation;
@Component

public interface AttestationInterface extends MongoRepository<AttestationImmatriculation, Integer>{

}
