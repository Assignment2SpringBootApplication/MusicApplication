package se.experis.com.musicapplication.data_access;

import se.experis.com.musicapplication.models.Customer;
import se.experis.com.musicapplication.models.CustomerCountry;
import se.experis.com.musicapplication.models.CustomerSpender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseAccessHandler {
    // Setup
    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM customer");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("customerId"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("country"),
                                resultSet.getString("postalCode"),
                                resultSet.getString("phone"),
                                resultSet.getString("email")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    public Customer getSpecificCustomerById(int customerId) {
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer WHERE CustomerId = ?");
            preparedStatement.setInt(1, customerId); // Corresponds to 1st '?' (must match type)
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();
            // Process Results
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("country"),
                        resultSet.getString("postalCode"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer;
        }
    }

    public Customer getSpecificCustomerByName(String firstName){
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer WHERE FirstName LIKE ?");
            preparedStatement.setString(1, "%"+firstName+"%"); // Corresponds to 1st '?' (must match type)
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("country"),
                        resultSet.getString("postalCode"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
            }

        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer;
        }
    }

    public ArrayList<Customer> getAllCustomersWithLimitAndOffset(int limit, int offset){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM customer LIMIT ? OFFSET ?");
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("customerId"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("country"),
                                resultSet.getString("postalCode"),
                                resultSet.getString("phone"),
                                resultSet.getString("email")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;


        }
    }

    public Boolean createNewCustomer(Customer customer){
        Boolean success = false;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer(CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());

            // Execute Statement
            int result = preparedStatement.executeUpdate();
            success = (result != 0); // if res = 1; true

            System.out.println("Created a new customer successfully!");

        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return success;
        }
    }

    public Boolean updateCustomer(Customer customer){
        Boolean success = false;
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("UPDATE Customer SET  CustomerId=?, FirstName=?, LastName=?,Country=?, PostalCode=?, Phone=?,Email=? WHERE CustomerId=?");
            prep.setInt(1,customer.getCustomerId());
            prep.setString(2,customer.getFirstName());
            prep.setString(3,customer.getLastName());
            prep.setString(4,customer.getCountry());
            prep.setString(5,customer.getPostalCode());
            prep.setString(6,customer.getPhone());
            prep.setString(7,customer.getEmail());
            prep.setInt(8,customer.getCustomerId());

            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true

            System.out.println("Update went well!");
        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public ArrayList<CustomerCountry> numberOFCustomersInEachCountry(){
        ArrayList<CustomerCountry> customersPerCountry = new ArrayList<CustomerCountry>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.Country AS Country, COUNT(*) AS Quantity FROM Customer GROUP BY Customer.Country ORDER BY COUNT(*) DESC");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customersPerCountry.add(
                        new CustomerCountry(
                                resultSet.getString("country"),
                                resultSet.getInt("quantity")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customersPerCountry;
        }
    }

    public ArrayList<CustomerSpender> customersHighestSpenders(){
        ArrayList<CustomerSpender> customerSpender = new ArrayList<CustomerSpender>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.FirstName, Customer.LastName, SUM(Total) AS TotalAmount FROM Invoice\n" +
                            "INNER JOIN Customer customer ON Invoice.CustomerId = Customer.CustomerId GROUP BY Invoice.CustomerId ORDER BY TotalAmount DESC");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customerSpender.add(
                        new CustomerSpender(
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getInt("totalAmount")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customerSpender;
        }
    }

}

