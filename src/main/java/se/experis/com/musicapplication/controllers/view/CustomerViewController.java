package se.experis.com.musicapplication.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.experis.com.musicapplication.data_access.DatabaseAccessHandler;

@Controller
public class CustomerViewController {
    DatabaseAccessHandler customer = new DatabaseAccessHandler();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getAllCustomers(Model model){
        model.addAttribute("customers", customer.getAllCustomers());
        return "view-customers";
    }


}
