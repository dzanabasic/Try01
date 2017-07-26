package bih.travel.smart.smarttravel.Helpers;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Džana on 24.7.2017.
 */

public class Flight {
    private String company_name;
    private String departure_place;
    private String destination_place;
    private Date departure_date;
    private Date arrival_date;
    private Time departure_time;
    private Time arrival_time;
    private Time flight_total_time;
    private Double price;
    private String currency;

    public Flight(String company_name, String departure_place, String destination_place, Date departure_date, Date arrival_date, Time departure_time, Time arrival_time, Time flight_total_time, Double price, String currency) {
        this.company_name = company_name;
        this.departure_place = departure_place;
        this.destination_place = destination_place;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.flight_total_time = flight_total_time;
        this.price = price;
        this.currency = currency;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setDeparture_place(String departure_place) {
        this.departure_place = departure_place;
    }

    public void setDestination_place(String destination_place) {
        this.destination_place = destination_place;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public void setFlight_total_time(Time flight_total_time) {
        this.flight_total_time = flight_total_time;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getDeparture_place() {
        return departure_place;
    }

    public String getDestination_place() {
        return destination_place;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public Time getFlight_total_time() {
        return flight_total_time;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
