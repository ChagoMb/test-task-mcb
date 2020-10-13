package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {

    private AirplaneDto airplaneDto;

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }

    @Override
    public Short getYear() {
        return airplaneDto.getYear();
    }
}
