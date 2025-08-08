package com.service.Nimbus.Model;

import jakarta.persistence.*;


@Table(name = "pool_members")
@Entity
public class PoolMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pool_id")
    private Long poolId;
    @Column(name = "trip_id")
    private Long tripId;

    public PoolMember(Long id, Long poolId, Long tripId) {
        this.id = id;
        this.poolId = poolId;
        this.tripId = tripId;
    }

    public PoolMember() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long pool_id) {
        this.poolId = pool_id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long trip_id) {
        this.tripId = trip_id;
    }
}
