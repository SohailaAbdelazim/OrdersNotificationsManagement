package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.ArabicTemplate;
import com.model.Template;
import com.service.IDatabaseService;
import com.service.IStatisticsService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private IStatisticsService statisticsService;

    @GetMapping("/most-email")
    public String getMostEmail() {
        String mostEmail = statisticsService.getMostEmail();
        return "The most email is: " + mostEmail + ".";
    }

    @GetMapping("/most-template")
    public Template getMostTemplate() {
        Template mostTemplate = statisticsService.getMostTemplate();
        return mostTemplate;
    }
}
