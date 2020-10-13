package com.mcb.creditfactory.repository.assessmentrepository;

import com.mcb.creditfactory.model.assessment.AirplaneAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneAssessmentRepository extends JpaRepository<AirplaneAssessment, Long> {
}
