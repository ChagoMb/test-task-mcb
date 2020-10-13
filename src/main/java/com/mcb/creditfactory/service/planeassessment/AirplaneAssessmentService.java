package com.mcb.creditfactory.service.planeassessment;

import com.mcb.creditfactory.dto.assessmentdto.AirplaneAssessmentDto;
import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.model.assessment.AirplaneAssessment;

public interface AirplaneAssessmentService {
    AirplaneAssessment fromDto(AirplaneAssessmentDto dto);
    AirplaneAssessmentDto toDto(AirplaneAssessment planeAssessment);
    AirplaneAssessment save(AirplaneAssessment planeAssessment);
    Long getId(AirplaneAssessment planeAssessment);
    boolean approvePlane(AirplaneAssessmentDto planeAssessmentDto);
}
