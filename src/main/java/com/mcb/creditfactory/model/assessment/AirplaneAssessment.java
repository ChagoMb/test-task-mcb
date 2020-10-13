package com.mcb.creditfactory.model.assessment;

import com.mcb.creditfactory.model.Airplane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "assessment_of_airplanes")
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assessed_value")
    private BigDecimal value;

    @Column(name = "date_of_assessment")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Airplane airplane;

    public AirplaneAssessment(BigDecimal value, LocalDate localDate) {
        this.value = value;
        this.date = localDate;
    }
}
