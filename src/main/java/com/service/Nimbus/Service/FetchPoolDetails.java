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


    public ResponseEntity<?> fetchPoolMembers(PoolDetailsRequest poolDetailsRequest, String username) {
        System.out.println(poolDetailsRequest.id());
        System.out.println(username);
        System.out.println(poolMemberRepository.isUserMemberOfPool(poolDetailsRequest.id(), username));
        if(poolMemberRepository.isUserMemberOfPool(poolDetailsRequest.id(), username) > 0) {
            List<PoolDetailsResponse> users = poolMemberRepository.fetchMembers(poolDetailsRequest.id());
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found in pool");
    }
}
