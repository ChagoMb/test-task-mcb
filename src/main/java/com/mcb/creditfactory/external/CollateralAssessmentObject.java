package com.mcb.creditfactory.external;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CollateralAssessmentObject {
    BigDecimal getValue();
    LocalDate getDate();
}
