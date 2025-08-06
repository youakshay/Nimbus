package com.service.Nimbus.Model;

import jakarta.persistence.*;


@Table(name = "pool_members")
@Entity
public class PoolMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pool_id;
    private Long trip_id;

    public PoolMember(Long id, Long pool_id, Long trip_id) {
        this.id = id;
        this.pool_id = pool_id;
        this.trip_id = trip_id;
    }

    public PoolMember() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPool_id() {
        return pool_id;
    }

    public void setPool_id(Long pool_id) {
        this.pool_id = pool_id;
    }

    public Long getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Long trip_id) {
        this.trip_id = trip_id;
    }
}
