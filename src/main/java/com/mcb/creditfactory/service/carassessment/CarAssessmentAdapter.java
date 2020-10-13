package com.mcb.creditfactory.service.carassessment;

import com.mcb.creditfactory.dto.assessmentdto.CarAssessmentDto;
import com.mcb.creditfactory.external.CollateralAssessmentObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class CarAssessmentAdapter implements CollateralAssessmentObject {

    private CarAssessmentDto dto;

    @Override
    public BigDecimal getValue() {
        return dto.getValue();
    }

    @Override
    public LocalDate getDate() {
        return dto.getDate();
    }
}
