package se.experis.com.musicapplication.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.experis.com.musicapplication.data_access.DatabaseAccessHandler;
import se.experis.com.musicapplication.models.SearchTrack;

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

    @RequestMapping(value = "/viewTrack", method = RequestMethod.GET)
    public String getSearchedTrack(Model model, @RequestParam (value = "trackName") String searchString){
        model.addAttribute("track", databaseAccessHandler.searchForTrack(searchString));
        model.addAttribute("resultFor", searchString);
        return "viewTrack";
    }


}
