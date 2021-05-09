package javanoob.io.coronavirustracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javanoob.io.coronavirustracker.services.CoronavirusDataService;

@Controller
public class HomeController {

  @Autowired
  private CoronavirusDataService coronavirusDataService;

  @GetMapping("/")
  public String home(Model model) {

    model.addAttribute("locationStats", coronavirusDataService.getAllStats());
    model.addAttribute("totalReportedCases", coronavirusDataService.getTotalReportedCases());
    return "home";
  }

}
