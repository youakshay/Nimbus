package com.service.Nimbus.Model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Table(name = "trips")
@Entity
public class Trip{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from_location;
    private String to_location;
    private LocalDateTime departure_time;
    private int seats_required;
    private Long user_id;

    public Trip(Long id, String from_location, String to_location, LocalDateTime departure_time, int seats_required, Long user_id) {
        this.id = id;
        this.from_location = from_location;
        this.to_location = to_location;
        this.departure_time = departure_time;
        this.seats_required = seats_required;
        this.user_id = user_id;
    }
    public Trip() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFrom_location() {
        return from_location;
    }
    public void setFrom_location(String from_location) {
        this.from_location = from_location;
    }
    public String getTo_location() {
        return to_location;
    }
    public void setTo_location(String to_location) {
        this.to_location = to_location;
    }
    public LocalDateTime getDeparture_time() {
        return departure_time;
    }
    public void setDeparture_time(LocalDateTime departure_time) {
        this.departure_time = departure_time;
    }
    public int getSeats_required() {
        return seats_required;
    }
    public void setSeats_required(int seats_required) {
        this.seats_required = seats_required;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
