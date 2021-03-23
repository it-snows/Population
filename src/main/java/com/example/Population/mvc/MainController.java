package com.example.Population.mvc;

import com.example.Population.data.DatabaseManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @GetMapping("")
    public String getIndexPage(Model model) {

        var dm = new DatabaseManager();

        var cities = dm.getCities();

        model.addAttribute("cities", cities);

        return "index";
    }


    @GetMapping("/city/{id}")
    public String getCityDetails(@PathVariable int id, Model model) {

        var dm = new DatabaseManager();
        var city = dm.getCityById(id);

        dm.getPopulationDataForCity(city);
        model.addAttribute("city", city);

        return "details";
    }

    @GetMapping("/region/{id}/cities")
    public String getCitiesInRegion(@PathVariable int id, Model model) {

        var dm = new DatabaseManager();
        var cities = dm.getCitiesByRegionId(id);

        model.addAttribute("cities", cities);

        return "cities_in_region";
    }

    @GetMapping("/chart/{id}")
    public String getGoogleChart(@PathVariable int id, Model model) {
        var dm = new DatabaseManager();
        var city = dm.getCityById(id);
        dm.getPopulationDataForCity(city);
        model.addAttribute("city", city);

        var sb = new StringBuilder();

        sb.append("var graphData = [\n");
        sb.append(" ['Year', 'Population'],\n");
        for(var pop : city.getPopulation()){
            sb.append("['"+ pop.getYear()+"', "+pop.getPopulation()+"],\n");
        }
        sb.append("];");

        sb.append("var cityName='"+city.getName()+"';");

        model.addAttribute("graph", sb.toString());
        return "google_chart";
    }
}
