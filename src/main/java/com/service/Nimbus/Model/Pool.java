package com.service.Nimbus.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "pools")
@Entity
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "from_location")
    private String fromLocation;
    @Column(name = "to_location")
    private String toLocation;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    private int capacity;
    @Column(name = "seats_available")
    private int seatsAvailable;

    public Pool() {
    }

    public Pool(Long id, String fromLocation, String toLocation, LocalDateTime departureTime, int capacity, int seatsAvailable) {
        this.id = id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.seatsAvailable = seatsAvailable;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seats_available) {
        this.seatsAvailable = seats_available;
    }

}
