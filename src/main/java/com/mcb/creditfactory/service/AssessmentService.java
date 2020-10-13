package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.assessmentdto.AirplaneAssessmentDto;
import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.dto.assessmentdto.CarAssessmentDto;
import com.mcb.creditfactory.model.assessment.AirplaneAssessment;
import com.mcb.creditfactory.service.carassessment.CarAssessmentService;
import com.mcb.creditfactory.service.planeassessment.AirplaneAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentService {

    @Autowired
    private CarAssessmentService carAssessmentService;

    @Autowired
    private AirplaneAssessmentService airplaneAssessmentService;

    public Long saveAssessment(Assessment object) {
        if (object instanceof CarAssessmentDto) {
            return saveCarAssessmentLikeAssessment(object);
        } else if(object instanceof AirplaneAssessmentDto) {
            return savePlaneAssessment(object);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Long saveCarAssessmentLikeAssessment(Assessment object) {
        CarAssessmentDto carAssessmentDto = (CarAssessmentDto) object;
        boolean approved = carAssessmentService.approveCar(carAssessmentDto);
        if (!approved) {
            return null;
        }

        return Optional.of(carAssessmentDto)
                .map(carAssessmentService::fromDto)
                .map(carAssessmentService::save)
                .map(carAssessmentService::getId)
                .orElse(null);
    }

    public Long savePlaneAssessment(Assessment object) {
        if (object instanceof AirplaneAssessmentDto) {
            return savePlaneAssessmentLikeAssessment(object);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Long savePlaneAssessmentLikeAssessment(Assessment object) {
        AirplaneAssessmentDto airplaneAssessmentDto = (AirplaneAssessmentDto) object;
        boolean approved = airplaneAssessmentService.approvePlane(airplaneAssessmentDto);
        if (!approved) {
            return null;
        }

        return Optional.of(airplaneAssessmentDto)
                .map(airplaneAssessmentService::fromDto)
                .map(airplaneAssessmentService::save)
                .map(airplaneAssessmentService::getId)
                .orElse(null);
    }
}
