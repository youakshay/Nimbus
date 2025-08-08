package com.service.Nimbus.Model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Table(name = "trips")
@Entity
public class Trip{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "from_location")
    private String fromLocation;
    @Column(name = "to_location")
    private String toLocation;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "seats_required")
    private int seatsRequired;
    @Column(name = "user_id")
    private Long userId;

    public Trip(Long id, String fromLocation, String toLocation, LocalDateTime departureTime, int seatsRequired, Long userId) {
        this.id = id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureTime = departureTime;
        this.seatsRequired = seatsRequired;
        this.userId = userId;
    }
    public Trip() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFromLocation() {
        return fromLocation;
    }
    public void setFromLocation(String from_location) {
        this.fromLocation = from_location;
    }
    public String getToLocation() {
        return toLocation;
    }
    public void setToLocation(String to_location) {
        this.toLocation = to_location;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departure_time) {
        this.departureTime = departure_time;
    }
    public int getSeatsRequired() {
        return seatsRequired;
    }
    public void setSeatsRequired(int seats_required) {
        this.seatsRequired = seats_required;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long user_id) {
        this.userId = user_id;
    }
}
