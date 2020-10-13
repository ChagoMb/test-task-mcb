package com.mcb.creditfactory.dto.assessmentdto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarAssessmentDto.class),
        @JsonSubTypes.Type(value = AirplaneAssessmentDto.class)
})
public interface Assessment {
}
