package com.api.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
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
            // 1. Read JSON file safely as a stream (Works on both Windows & GitHub Linux)
            InputStream inputStream = DataProviders.class.getClassLoader()
                    .getResourceAsStream("testdata/booking-inputs.json");

            if (inputStream == null) {
                inputStream = DataProviders.class.getResourceAsStream("src/test/resources/testdata/booking-inputs.json");
            }

            if (inputStream == null) {
                throw new RuntimeException("💥 Could not find booking-inputs.json in target resources classpath!");
            }

            // 2. Initialize Jackson's ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            
            // 3. Read the JSON stream data directly into a List of Maps (Exactly like your original)
            List<Map<String, Object>> dataList = mapper.readValue(inputStream, 
                new TypeReference<List<Map<String, Object>>>() {});
            
            // 4. Initialize and size the 2D Object Array matrix for TestNG
            Object[][] matrix = new Object[dataList.size()][1];
            
            // 5. Convert the List elements into the TestNG matrix data blocks
            for (int i = 0; i < dataList.size(); i++) {
                matrix[i][0] = dataList.get(i);
            }
            
            return matrix;
            
        } catch (IOException e) {
            throw new RuntimeException("💥 Failed to parse external JSON test data file!", e);
        }
    }
}