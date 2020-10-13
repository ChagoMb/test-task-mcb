package com.mcb.creditfactory.service.carassessment;

import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.dto.assessmentdto.CarAssessmentDto;
import com.mcb.creditfactory.model.assessment.CarAssessment;

public interface CarAssessmentService {
    CarAssessment fromDto(CarAssessmentDto dto);
    CarAssessmentDto toDto(CarAssessment carAssessment);
    CarAssessment save(CarAssessment carAssessment);
    Long getId(CarAssessment carAssessment);
    boolean approveCar(CarAssessmentDto carAssessmentDto);
}
