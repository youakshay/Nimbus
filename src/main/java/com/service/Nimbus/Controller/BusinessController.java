package com.service.Nimbus.Controller;

import com.service.Nimbus.DTO.PoolDetailsRequest;
import com.service.Nimbus.Model.Trip;
import com.service.Nimbus.Respository.PoolMemberRepository;
import com.service.Nimbus.Respository.PoolRepository;
import com.service.Nimbus.Respository.TripRepository;
import com.service.Nimbus.Service.FetchPoolDetails;
import com.service.Nimbus.Service.RidePoolService;
import com.service.Nimbus.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {

    private final RidePoolService ridePoolService;
    private final FetchPoolDetails fetchPoolDetails;
    private final JwtUtil jwtUtil;

    public BusinessController(RidePoolService ridePoolService,
                              JwtUtil jwtUtil,
                              FetchPoolDetails fetchPoolDetails) {
        this.ridePoolService=ridePoolService;
        this.jwtUtil=jwtUtil;
        this.fetchPoolDetails=fetchPoolDetails;
    }
    @PostMapping("/content")
    public void content(@RequestBody String content) {
        System.out.println("Handling content: " + content);
    }

    @PostMapping("/trip")
    public ResponseEntity<?> requestTrip(@RequestBody Trip trip, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String username = jwtUtil.extractUsername(token);
        return ridePoolService.requestTrip(trip, username);
    }

    @PostMapping("/fetchTripDetails")
    public ResponseEntity<?> fetchTripDetails(@RequestBody PoolDetailsRequest poolDetailsRequest, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String username = jwtUtil.extractUsername(token);
        return fetchPoolDetails.fetchPoolMembers(poolDetailsRequest, username);
    }
}
