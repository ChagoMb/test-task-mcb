package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.dto.assessmentdto.Assessment;
import com.mcb.creditfactory.service.AssessmentService;
import com.mcb.creditfactory.service.CollateralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollateralObjectController {
    @Autowired
    private CollateralService service;

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping("/collateral/save")
    public HttpEntity<Long> save(@RequestBody Collateral object) {
        Long id = service.saveCollateral(object);
        return id != null ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/collateral/info")
    public HttpEntity<Collateral> getInfo(@RequestBody Collateral object) {
        Collateral info = service.getInfo(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @PostMapping("/assessment/save")
    public HttpEntity<Long> saveAssessment(@RequestBody Assessment object) {
        Long id = assessmentService.saveAssessment(object);
        return id != null ? ResponseEntity.ok(id) : ResponseEntity.notFound().build();
    }

    @PostMapping("/assessment/info")
    public HttpEntity<List<Assessment>> getAssessment(@RequestBody Collateral object) {
        List<Assessment> info  = service.getAllAssessments(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }
}
