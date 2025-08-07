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
    select u.username, u.full_name, u.email, t.seats_required
    From pool_members pm
    JOIN trips t ON pm.trip_id = t.id
    JOIN users u ON t.user_id = u.id 
    where pm.pool_id = :poolId
""", nativeQuery = true)
    List<PoolDetailsResponse> fetchMembers(@Param("poolId") Long poolId);

    @Query(value = """
    SELECT count(*)
    From pool_members pm
    JOIN trips t ON pm.trip_id = t.id
    JOIN users u ON t.user_id = u.id
    where pm.pool_id = :poolId and u.username = :username
""", nativeQuery = true)
    int isUserMemberOfPool(@Param("poolId") Long poolId, @Param("username") String username);
}
