package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private ExternalApproveService approveService;

    @Override
    public boolean approve(AirplaneDto planeDto) {
        return approveService.approve(new AirplaneAdapter(planeDto)) == 0;
    }

    @Override
    public Airplane save(Airplane plane) {
        return airplaneRepository.save(plane);
    }

    @Override
    public Optional<Airplane> load(Long planeId) {
        return airplaneRepository.findById(planeId);
    }

    @Override
    public Airplane fromDto(AirplaneDto planeDto) {
        return new Airplane(
                planeDto.getId(),
                planeDto.getBrand(),
                planeDto.getModel(),
                planeDto.getPower(),
                planeDto.getYear()
                );
    }

    @Override
    public AirplaneDto toDTO(Airplane plane) {
        return new AirplaneDto(
                plane.getId(),
                plane.getBrand(),
                plane.getModel(),
                plane.getPower(),
                plane.getYear()
        );
    }

    @Override
    public Long getId(Airplane plane) {
        return plane.getId();
    }
}
