package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> locationStatsList = coronaVirusDataService.getAllStats();
        model.addAttribute("locationStats", locationStatsList);
        int totalNumberOfCases = 0;

        for (int i = 0; i < locationStatsList.size(); i++) {
            totalNumberOfCases = totalNumberOfCases + locationStatsList.get(i).getLatestTotal();
        }
        model.addAttribute("totalReportedCases", totalNumberOfCases);

        return "home";
    }
}
