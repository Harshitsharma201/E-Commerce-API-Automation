package com.api.utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviders {

	@DataProvider(name="bookingDataProvider")
	public static Object[][] bookingDataProvider (){
		return new Object[][] {
			
			
            // FirstName, LastName, TotalPrice, DepositPaid, CheckIn, CheckOut, AdditionalNeeds
            { "Amit",      "Sharma",   250,        true,        "2026-07-01", "2026-07-10", "Breakfast" },
            { "Raj",       "Verma",    0,          false,       "2026-08-15", "2026-08-20", "Late Check-in" },
            { "Vikas",     "Singh",    1500,       true,        "2026-12-24", "2027-01-02", "Extra Bed" }
        };
	}
	
	@DataProvider(name = "bookingJsonProvider")
    public static Object[][] getBookingDataFromJson() {
        try {
            // 1. Initialize Jackson's ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            
            // 2. Define the path to our external JSON data file
            File jsonFile = new File("src/test/resources/testdata/booking-inputs.json");
            
            // 3. Read the JSON file directly into a List of Maps
            List<Map<String, Object>> dataList = mapper.readValue(jsonFile, 
                new TypeReference<List<Map<String, Object>>>() {});
            
            // 4. Convert the List into a 2D Object Array matrix for TestNG [rows][columns]
            Object[][] matrix = new Object[dataList.size()][1];
            
            for (int i = 0; i < dataList.size(); i++) {
                // Pass the map of data for each row as a single argument
                matrix[i][0] = dataList.get(i);
            }
            
            return matrix;
            
        }
        catch (IOException e) {
            throw new RuntimeException("💥 Failed to parse external JSON test data file!", e);
        }
    }
}
	

