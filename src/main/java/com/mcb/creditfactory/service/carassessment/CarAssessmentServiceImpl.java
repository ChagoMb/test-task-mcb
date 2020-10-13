package com.mcb.creditfactory.service.carassessment;

import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.dto.assessmentdto.CarAssessmentDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.assessment.CarAssessment;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.repository.assessmentrepository.CarAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarAssessmentServiceImpl implements CarAssessmentService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarAssessmentRepository carAssessmentRepository;

    @Autowired
    private ExternalApproveService approveService;

    @Override
    public CarAssessment save(CarAssessment carAssessment) {
        return carAssessmentRepository.save(carAssessment);
    }

    @Override
    public CarAssessment fromDto(CarAssessmentDto dto) {
        Optional<Car> optionalCar = carRepository.findById(dto.getCarId());
        if (!optionalCar.isPresent()) {
            throw new IllegalArgumentException("Car doesn't exist!");
        }
        return new CarAssessment(dto.getId(),
                dto.getValue(),
                dto.getDate(),
                optionalCar.get()
        );
    }

    @Override
    public CarAssessmentDto toDto(CarAssessment carAssessment) {
        return new CarAssessmentDto(
                carAssessment.getId(),
                carAssessment.getValue(),
                carAssessment.getDate(),
                carAssessment.getCar().getId()
        );
    }

    @Override
    public boolean approveCar(CarAssessmentDto dto) {
        return approveService.approveCarAssessment(new CarAssessmentAdapter(dto)) == 0;
    }

    @Override
    public Long getId(CarAssessment carAssessment) {
        return carAssessment.getId();
    }
}
