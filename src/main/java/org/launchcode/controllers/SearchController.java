package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    @GetMapping(value = "results")
    public String searchSubmit(Model model, @RequestParam String searchType, @RequestParam String searchTerm ) {
        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        String title = "Searching: " + searchType + " for: " + searchTerm;
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("title", title);
        model.addAttribute("searchType", searchType);

        return "search";
    }
}
