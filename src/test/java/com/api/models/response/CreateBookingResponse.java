package com.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBookingResponse {

    // 🌟 Jackson maps the incoming "bookingid" JSON field directly to this variable
	@JsonProperty("bookingid")
    private int bookingid; 

    // Changing the method name to getId() matches your test class call!
    public int getId() {
        return bookingid;
    }

    public void setId(int bookingid) {
        this.bookingid = bookingid;
    }
}