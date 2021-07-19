package se.experis.com.musicapplication.controllers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.experis.com.musicapplication.MusicApplication;
import se.experis.com.musicapplication.data_access_repository.CustomerData;
import se.experis.com.musicapplication.models.Customer;

import java.util.ArrayList;

@RestController
public class CustomerController {
    @GetMapping("/") //Hur man når endpoint
    public String index() {
        return "This is the root of our application";
    }

    @GetMapping("/api/Customers") //Hur man når endpoint
    public ArrayList<Customer> getAllCustomers() {
        CustomerData customer = new CustomerData();
        ArrayList<Customer> customers = customer.selectAllCustomers();
        return customers;
    }
}