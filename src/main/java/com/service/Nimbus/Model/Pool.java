package com.service.Nimbus.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "pools")
@Entity
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from_location;
    private String to_location;
    private LocalDateTime departure_time;
    private int capacity;
    private int seats_available;

    public Pool() {
    }

    public Pool(Long id, String from_location, String to_location, LocalDateTime departure_time, int capacity, int seats_available) {
        this.id = id;
        this.from_location = from_location;
        this.to_location = to_location;
        this.departure_time = departure_time;
        this.capacity = capacity;
        this.seats_available = seats_available;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(int seats_available) {
        this.seats_available = seats_available;
    }

}
