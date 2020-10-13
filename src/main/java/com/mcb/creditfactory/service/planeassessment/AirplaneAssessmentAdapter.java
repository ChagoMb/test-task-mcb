package com.mcb.creditfactory.service.planeassessment;

import com.mcb.creditfactory.dto.assessmentdto.AirplaneAssessmentDto;
import com.mcb.creditfactory.external.CollateralAssessmentObject;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class AirplaneAssessmentAdapter implements CollateralAssessmentObject {

    private AirplaneAssessmentDto dto;

    @Override
    public BigDecimal getValue() {
        return dto.getValue();
    }

    @Override
    public LocalDate getDate() {
        return dto.getDate();
    }
}
