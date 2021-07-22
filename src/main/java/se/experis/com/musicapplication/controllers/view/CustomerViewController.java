package se.experis.com.musicapplication.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.experis.com.musicapplication.data_access.DatabaseAccessHandler;

@Controller
public class CustomerViewController {
    DatabaseAccessHandler databaseAccessHandler = new DatabaseAccessHandler();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("artists", databaseAccessHandler.randomArtists());
        model.addAttribute("tracks", databaseAccessHandler.randomTracks());
        model.addAttribute("genres", databaseAccessHandler.randomGenres());
        return "home";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getAllCustomers(Model model){
        model.addAttribute("customers", databaseAccessHandler.getAllCustomers());
        return "view-customers";
    }


}
