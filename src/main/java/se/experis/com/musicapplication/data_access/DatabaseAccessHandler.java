package se.experis.com.musicapplication.data_access;

import se.experis.com.musicapplication.models.*;

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

    public ArrayList<CustomerGenre> customersMostPopularGenre(int customerId){
        ArrayList<CustomerGenre> customerGenre = new ArrayList<CustomerGenre>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("WITH Top AS" +
                            "(" +
                            " SELECT Genre.Name AS Genre, COUNT(Track.GenreId) AS Occurrences" +
                            "      FROM Customer" +
                            "      INNER JOIN Invoice ON Invoice.CustomerId = Customer.CustomerId" +
                            "      INNER JOIN InvoiceLine ON InvoiceLine.InvoiceId = Invoice.InvoiceId" +
                            "      INNER JOIN Track ON InvoiceLine.TrackId = Track.TrackId" +
                            "      Inner JOIN Genre ON Track.GenreId = Genre.GenreId" +
                            "      WHERE Customer.CustomerId = ? GROUP BY Track.GenreId" +
                            ")" +
                            "SELECT * FROM Top WHERE Occurrences = (SELECT MAX(Occurrences) FROM Top)");
            preparedStatement.setInt(1, customerId);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customerGenre.add(
                        new CustomerGenre(
                                resultSet.getString("Genre"),
                                resultSet.getInt("Occurrences")
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
            return customerGenre;
        }
    }

    public ArrayList<Artist> randomArtists(){
        ArrayList<Artist> artists = new ArrayList<Artist>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT ArtistId, Name FROM Artist ORDER BY random() LIMIT 5");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                artists.add(
                        new Artist(
                                resultSet.getInt("artistId"),
                                resultSet.getString("name")
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
            return artists;
        }
    }

    public ArrayList<Track> randomTracks(){
        ArrayList<Track> tracks = new ArrayList<Track>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT TrackId, Name FROM Track ORDER BY random() LIMIT 5");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                tracks.add(
                        new Track(
                                resultSet.getInt("trackId"),
                                resultSet.getString("name")
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
            return tracks;
        }
    }

    public ArrayList<Genre> randomGenres(){
        ArrayList<Genre> genres = new ArrayList<Genre>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId, Name FROM Genre ORDER BY random() LIMIT 5");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                genres.add(
                        new Genre(
                                resultSet.getInt("genreId"),
                                resultSet.getString("name")
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
            return genres;
        }
    }

    public ArrayList<Search> searchFor(String searchString){
        ArrayList<Search> result = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Track.Name AS TrackName, Artist.Name AS ArtistName, Album.Title AS AlbumTitle, Genre.Name AS GenreName FROM Track " +
                            "INNER JOIN Album ON Track.AlbumId = Album.AlbumId \n" +
                            "INNER JOIN Artist ON Album.ArtistId = Artist.Artistid \n" +
                            "INNER JOIN Genre ON Track.GenreId = Genre.GenreId WHERE Track.Name LIKE ? OR Artist.Name LIKE ? OR Album.Title LIKE ? OR Genre.Name LIKE ?");
            preparedStatement.setString(1, "%"+searchString+"%");
            preparedStatement.setString(2, "%"+searchString+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                result.add(
                        new Search(
                                resultSet.getString("trackName"),
                                resultSet.getString("artistName"),
                                resultSet.getString("albumTitle"),
                                resultSet.getString("genreName")
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
            return result;
        }
    }
}

