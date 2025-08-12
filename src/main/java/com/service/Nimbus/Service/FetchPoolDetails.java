package com.service.Nimbus.Service;

import com.service.Nimbus.DTO.PoolDetailsRequest;
import com.service.Nimbus.DTO.PoolDetailsResponse;
import com.service.Nimbus.Model.User;
import com.service.Nimbus.Respository.PoolMemberRepository;
import com.service.Nimbus.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchPoolDetails {

    private final PoolMemberRepository poolMemberRepository;

    public FetchPoolDetails(PoolMemberRepository poolMemberRepository) {
        this.poolMemberRepository=poolMemberRepository;
    }


<<<<<<< HEAD
    public ResponseEntity<?> fetchPoolMembers(PoolDetailsRequest poolDetailsRequest, String username) {
        if(poolMemberRepository.isUserMemberOfPool(poolDetailsRequest.id(), username) > 0) {
            List<PoolDetailsResponse> users = poolMemberRepository.fetchMembers(poolDetailsRequest.id());
=======
    public ResponseEntity<?> fetchPoolMembers(String username) {
        Long poolId = poolMemberRepository.getPoolId(username);
        System.out.println("User belongs from pool: "+poolId);
        List<PoolDetailsResponse> users = poolMemberRepository.fetchMembers(poolId);
        if(users.size() > 0){
>>>>>>> b8a412b (Fix fetch pool details)
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().body("You have no active pool!!!");
    }
}
