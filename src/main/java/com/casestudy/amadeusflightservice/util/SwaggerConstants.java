package com.casestudy.amadeusflightservice.util;

public class SwaggerConstants {
    public static final String GET_AIRPORTS_SUMMARY = "Get all airports in the database";
    public static final String GET_AIRPORTS_DESCRIPTION = "Get all airports. Only authenticated users can access this endpoint";
    public static final String ADD_AIRPORT_SUMMARY = "Add an new airport";
    public static final String ADD_AIRPORT_DESCRIPTION = "Add an airport to the database. If the provided airport name already exists, error code 106 is returned with a message \"Airport already exists\"";
    public static final String UPDATE_AIRPORT_SUMMARY = "Update an airport";
    public static final String UPDATE_AIRPORT_DESCRIPTION = "Update an airport that exists in the database. If the airport is not found with the given id, error code 107 is returned with a message \"Airport not found\"";
    public static final String DELETE_AIRPORT_SUMMARY = "Delete an airport";
    public static final String DELETE_AIRPORT_DESCRIPTION = "Delete an airport that exists in the database. If the airport is not found with the given id, error code 107 is returned with a message \"Airport not found\"";
    public static final String SEARCH_FLIGHTS_SUMMARY = "Search flights by departure and arrival airports";
    public static final String SEARCH_FLIGHTS_DESCRIPTION = """
            Search flights by departure and arrival airports. Only authenticated users can search for flights.
            If the any of the airports are not found, error code 107 is returned with a message "Airport not found".
            Return date is optional. If it is not provided, only one way flights are returned.
            
            
            Step by step logic:
            1. Get the departure and arrival airports by the given airport names.
            2. Get all flights that have the provided departure and arrival airport.
            3. Filter the flights by departure date to get flights that are only in the provided date. (In the same day, month and year. Not the exact time.)
            4. Sort the flights by departure date.
            5. Return the flights.
            """;
    public static final String SEARCH_FLIGHTS_BY_CITY_SUMMARY = "Search flights by cities";
    public static final String SEARCH_FLIGHTS_BY_CITY_DESCRIPTION = """
            Search flights by departure and arrival cities. Only authenticated users can search for flights.
            Return date is optional. If it is not provided, only one way flights are returned.
            
            Step by step logic:
            1. Get the departure and arrival airports by the given cities.
            2. Find flights by checking all departure airports that have flights to the arrival airport.
            3. Filter the flights by departure date to get flights that are only in the provided date. (In the same day, month and year. Not the exact time.)
            4. Sort the flights by departure date.
            5. Return the flights.
            """;
    public static final String LOGIN_SUMMARY = "Login";
    public static final String LOGIN_DESCRIPTION = """
            Login to the system. If the provided email and password are correct, a JWT token is returned with the expiration date.
            The token is used to authenticate the user in the system.
            
            Step by step logic:
            1. Check if the user exists in the database by the given email.
            2. If the user exists, check if the password is correct.
            3. If the password is correct, generate a JWT token and return it with the expiration date.
            
            If the user is not found, return error code 101 with a message "User not found".
            If the password is incorrect, return error code 104 with a message "Invalid credentials".
            """;
    public static final String REGISTER_SUMMARY = "Register";
    public static final String REGISTER_DESCRIPTION = """
            Register to the system. If the provided email is not used before, the user is registered to the system.
            Password has to be at least 6 characters long.
            
            Step by step logic:
            1. Check if the user exists in the database by the given email.
            2. If the user does not exist, register the user to the system.
            3. Before saving the user, encode the password.
            
            If the user exists, return error code 105 with a message "Email already exists".
            """;
    public static final String GET_FLIGHTS_SUMMARY = "Get all flights";
    public static final String GET_FLIGHTS_DESCRIPTION = "Get all the flights in the database. Only authenticated users can access this endpoint";
    public static final String ADD_FLIGHT_SUMMARY = "Add a flight";
    public static final String ADD_FLIGHT_DESCRIPTION = """
            Add a flight to the database.
            
            Step by step logic:
            1. Check if the flight already exists in the database by the given flight number with the same departure time.
            2. Check provided departure and arrival airports.
            3. If the flight does not exist, add the flight to the database.
            
            If the flight already exists, return error code 111 with a message "Flight already exists".
            If the departure or arrival airport is not found, return error code 107 with a message "Airport not found".
            If the departure and arrival airports are the same, return error code 108 with a message "Departure and arrival airports cannot be the same".
            """;
    public static final String UPDATE_FLIGHT_SUMMARY = "Update a flight";
    public static final String UPDATE_FLIGHT_DESCRIPTION = """
            Update a flight that exists in the database.
            
            Step by step logic:
            1. Check if the flight exists in the database by the given id.
            2. Check provided departure and arrival airports.
            3. If the flight exists, update the flight in the database.
            
            If the flight does not exist, return error code 109 with a message "Flight not found".
            If the departure or arrival airport is not found, return error code 107 with a message "Airport not found".
            If the departure and arrival airports are the same, return error code 108 with a message "Departure and arrival airports cannot be the same".
            """;
    public static final String DELETE_FLIGHT_SUMMARY = "Delete a flight";
    public static final String DELETE_FLIGHT_DESCRIPTION = "Delete a flight by the given id. If the flight is not found, error code 109 is returned with a message \"Flight not found\"";

    private SwaggerConstants() {}
}
