package com.mcb.creditfactory.dto.assessmentdto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("car_assessment")
public class CarAssessmentDto implements Assessment {
    private Long id;
    private BigDecimal value;
    private LocalDate date;
    private Long carId;
}
