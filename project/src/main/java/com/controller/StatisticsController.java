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

    @GetMapping("")
    public String home() {
        // Pritn the instrctions for the user that can be done in this mapping
        return "Welcome to the Statistics API!" +

                "\n\nYou can use the following mappings:" +
                "\n- GET /api/statistics/most-email - Get the most email(s) sent" +
                "\n- GET /api/statistics/most-template - Get the most template(s) used";
    }

    @GetMapping("/most-email")
    public String[] getMostEmail() {
        String[] mostEmails = statisticsService.getMostEmail();
        return mostEmails;
    }

    @GetMapping("/most-template")
    public Template getMostTemplate() {
        Template mostTemplate = statisticsService.getMostTemplate();
        return mostTemplate;
    }
}
