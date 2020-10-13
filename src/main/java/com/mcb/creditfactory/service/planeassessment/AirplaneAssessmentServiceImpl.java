package com.mcb.creditfactory.service.planeassessment;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.assessmentdto.AirplaneAssessmentDto;
import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.assessment.AirplaneAssessment;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.assessmentrepository.AirplaneAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneAssessmentServiceImpl implements AirplaneAssessmentService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirplaneAssessmentRepository airplaneAssessmentRepository;

    @Autowired
    private ExternalApproveService approveService;

    @Override
    public AirplaneAssessment fromDto(AirplaneAssessmentDto dto) {
        Optional<Airplane> optionalPlane = airplaneRepository.findById(dto.getPlaneId());
        if (!optionalPlane.isPresent()) {
            throw new IllegalArgumentException("Plane doesn't exist!");
        }
        return new AirplaneAssessment(dto.getId(),
                dto.getValue(),
                dto.getDate(),
                optionalPlane.get()
        );
    }

    @Override
    public AirplaneAssessmentDto toDto(AirplaneAssessment planeAssessment) {
        return new AirplaneAssessmentDto(
                planeAssessment.getId(),
                planeAssessment.getValue(),
                planeAssessment.getDate(),
                planeAssessment.getAirplane().getId()
        );
    }

    @Override
    public AirplaneAssessment save(AirplaneAssessment planeAssessment) {
        return airplaneAssessmentRepository.save(planeAssessment);
    }

    @Override
    public Long getId(AirplaneAssessment planeAssessment) {
        return planeAssessment.getId();
    }

    @Override
    public boolean approvePlane(AirplaneAssessmentDto planeAssessmentDto) {
        return approveService.approveAirplaneAssessment(new AirplaneAssessmentAdapter(planeAssessmentDto)) == 0;
    }
}
