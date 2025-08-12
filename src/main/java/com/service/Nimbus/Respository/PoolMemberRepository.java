package com.service.Nimbus.Respository;

import com.service.Nimbus.DTO.PoolDetailsResponse;
import com.service.Nimbus.Model.PoolMember;
import com.service.Nimbus.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoolMemberRepository extends JpaRepository<PoolMember, Long> {
    @Query(value = """
    select pool_id from pool_members
    where trip_id = :tripId
""", nativeQuery = true)
    Long findPoolIdByTripId(@Param("tripId") Long tripId);

    @Query(value = """
    SELECT u.username, u.full_name, u.email, t.seats_required
    FROM pool_members pm
    JOIN trips t ON t.id = pm.trip_id
    JOIN users u on t.user_id = u.id
    where pm.pool_id = :poolId
""", nativeQuery = true)
    List<PoolDetailsResponse> fetchMembers(@Param("poolId") Long poolId);

    @Query(value = """
    select pm.pool_id
    from users u
    JOIN trips t ON u.id = t.user_id
    JOIN pool_members pm ON pm.trip_id = t.id
    where u.username = :username
""", nativeQuery = true)
    Long getPoolId(@Param("username") String username);

}
