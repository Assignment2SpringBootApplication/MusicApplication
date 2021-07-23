package se.experis.com.musicapplication.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.experis.com.musicapplication.data_access.DatabaseAccessHandler;

@Controller
public class ViewController {
    DatabaseAccessHandler databaseAccessHandler = new DatabaseAccessHandler();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("artists", databaseAccessHandler.randomArtists());
        model.addAttribute("tracks", databaseAccessHandler.randomTracks());
        model.addAttribute("genres", databaseAccessHandler.randomGenres());
        return "home";
    }

    @RequestMapping(value = "/searchResult", method = RequestMethod.GET)
    public String getSearchedTrack(Model model, @RequestParam (value = "searchString") String searchString){
        model.addAttribute("result", databaseAccessHandler.searchFor(searchString));
        model.addAttribute("resultFor", searchString);
        return "searchResult";
    }


}
