package com.mcb.creditfactory.repository.assessmentrepository;

import com.mcb.creditfactory.model.assessment.CarAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarAssessmentRepository extends JpaRepository<CarAssessment, Long> {

    @Query("SELECT new com.mcb.creditfactory.model.assessment.CarAssessment(carAssessment.id," +
            " carAssessment.value, MAX(carAssessment.date), carAssessment.car)"
            + " FROM CarAssessment carAssessment WHERE carAssessment.car.id = :carId")
    CarAssessment getLatestCarAssessmentById(Long carId);
}
