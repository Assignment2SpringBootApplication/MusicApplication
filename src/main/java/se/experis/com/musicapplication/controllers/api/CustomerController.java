package se.experis.com.musicapplication.controllers.api;

import org.springframework.web.bind.annotation.*;
import se.experis.com.musicapplication.data_access.DatabaseAccessHandler;
import se.experis.com.musicapplication.models.Customer;

import java.util.ArrayList;

@RestController
public class CustomerController {

    DatabaseAccessHandler customer = new DatabaseAccessHandler();

    @GetMapping("/") //Hur man når endpoint
    public String index() {
        return "This is the root of our application";
    }

    @GetMapping("/api/Customers") //Get all customers
    public ArrayList<Customer> getCustomers() {
        return customer.getAllCustomers();
    }

    @GetMapping("/api/Customers/customerId/{CustomerId}") //Get a specific customer
    public Customer getCustomer(@PathVariable int CustomerId)  {
        return customer.getSpecificCustomerById(CustomerId);
    }

    @GetMapping("/api/Customers/firstName/{FirstName}") //Get a specific customer by name
    public Customer getCustomer(@PathVariable String FirstName)  {
        return customer.getSpecificCustomerByName(FirstName);
    }

    @GetMapping("/api/Customers/{Limit}/{Offset}") //Get all customers with Limit and Offset
    public ArrayList<Customer> getCustomersWithLimitAndOffset(@PathVariable int Limit, @PathVariable int Offset)  {
        return customer.getAllCustomersWithLimitAndOffset(Limit, Offset);
    }

   @RequestMapping(value = "/api/Customers/addNewCustomer", method = RequestMethod.POST) //Create new customer
    public Boolean createCustomer(@RequestBody Customer newCustomer) {
        return customer.createNewCustomer(newCustomer);
    }

    @RequestMapping(value = "/api/Customers/updateCustomer", method = RequestMethod.PUT) //Create new customer
    public Boolean updateCustomer(@RequestBody Customer updatedCustomer) {
        return customer.updateCustomer(updatedCustomer);
    }


}
