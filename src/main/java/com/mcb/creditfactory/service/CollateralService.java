package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.assessment.AirplaneAssessment;
import com.mcb.creditfactory.model.assessment.CarAssessment;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.carassessment.CarAssessmentService;
import com.mcb.creditfactory.service.car.CarService;
import com.mcb.creditfactory.service.planeassessment.AirplaneAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private CarService carService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private CarAssessmentService carAssessmentService;

    @Autowired
    private AirplaneAssessmentService airplaneAssessmentService;

    public Long saveCollateral(Collateral object) {
        if (object instanceof CarDto) {
            return saveCarLikeCollateral(object);
        } else if(object instanceof AirplaneDto) {
            return savePlaneLikeCollateral(object);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Collateral getInfo(Collateral object) {
        if (object instanceof CarDto) {
            return getInfoAboutCar(object);
        } else if(object instanceof AirplaneDto) {
            return getInfoAboutPlane(object);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Long saveCarLikeCollateral(Collateral object) {
        CarDto car = (CarDto) object;
        boolean approved = carService.approve(car);
        if (!approved) {
            return null;
        }

        return Optional.of(car)
                .map(carService::fromDto)
                .map(carService::save)
                .map(carService::getId)
                .orElse(null);
    }

    private Long savePlaneLikeCollateral(Collateral object) {
        AirplaneDto planeDto = (AirplaneDto) object;
        boolean approved = airplaneService.approve(planeDto);
        if (!approved) {
            return null;
        }

        return Optional.of(planeDto)
                .map(airplaneService::fromDto)
                .map(airplaneService::save)
                .map(airplaneService::getId)
                .orElse(null);
    }

    private Collateral getInfoAboutCar(Collateral object) {
        return Optional.of((CarDto) object)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(carService::toDTO)
                .orElse(null);
    }

    private Collateral getInfoAboutPlane(Collateral object) {
        return Optional.of((AirplaneDto) object)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(airplaneService::toDTO)
                .orElse(null);
    }

    public List<Assessment> getAllAssessments(Collateral object) {
        if (object instanceof CarDto) {
            return getCarAssessments(object);
        }
        else if(object instanceof AirplaneDto) {
            return getPlaneAssessments(object);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private List<Assessment> getCarAssessments(Collateral object) {
        List<CarAssessment> carAssessments = Optional.of((CarDto) object)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(Car::getAssessments)
                .orElse(null);
        return carAssessments.stream().map(carAssessmentService::toDto)
                .collect(Collectors.toList());
    }

    private List<Assessment> getPlaneAssessments(Collateral object) {
        List<AirplaneAssessment> carAssessments = Optional.of((AirplaneDto) object)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(Airplane::getAssessments)
                .orElse(null);
        return carAssessments.stream().map(airplaneAssessmentService::toDto)
                .collect(Collectors.toList());
    }
}
