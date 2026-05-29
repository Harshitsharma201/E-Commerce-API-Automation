package com.api.models.response;

import com.api.models.request.UpdateBookingRequest.BookingDates;

public class UpdateBookingResponse {

	private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates; 
    private String additionalneeds;

    public UpdateBookingResponse() {
    	
    }
    // 🌟 FIXED: The constructor now assigns your incoming parameters to your fields!
    public UpdateBookingResponse(String firstname, String lastname, int totalprice, 
                                boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "UpdateBookingResponse [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
                + ", depositpaid=" + depositpaid + ", bookingdates=" + bookingdates + ", additionalneeds="
                + additionalneeds + "]";
    }

    // ==========================================
    // Getters and Setters
    // ==========================================
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public int getTotalprice() { return totalprice; }
    public void setTotalprice(int totalprice) { this.totalprice = totalprice; }

    public boolean isDepositpaid() { return depositpaid; }
    public void setDepositpaid(boolean depositpaid) { this.depositpaid = depositpaid; }

    public BookingDates getBookingdates() { return bookingdates; }
    public void setBookingdates(BookingDates bookingdates) { this.bookingdates = bookingdates; }

    public String getAdditionalneeds() { return additionalneeds; }
    public void setAdditionalneeds(String additionalneeds) { this.additionalneeds = additionalneeds; }

    // ==========================================
    // Static Inner Class
    // ==========================================
    public static class BookingDates {
        private String checkin;
        private String checkout;

        public BookingDates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }
        public BookingDates() {
        	
        }
        public String getCheckin() { return checkin; }
        public void setCheckin(String checkin) { this.checkin = checkin; }
        public String getCheckout() { return checkout; }
        public void setCheckout(String checkout) { this.checkout = checkout; }
        
        // 🌟 Added a toString for the inner class so dates print beautifully!
        @Override
        public String toString() {
            return "[checkin=" + checkin + ", checkout=" + checkout + "]";
        }
    }
}
