package com.service.Nimbus.Controller;

import com.service.Nimbus.DTO.PoolDetailsRequest;
import com.service.Nimbus.Model.Trip;
import com.service.Nimbus.Respository.PoolMemberRepository;
import com.service.Nimbus.Respository.PoolRepository;
import com.service.Nimbus.Respository.TripRepository;
import com.service.Nimbus.Service.CancelTrip;
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
    private final CancelTrip cancelTrip;

    public BusinessController(RidePoolService ridePoolService,
                              JwtUtil jwtUtil,
                              FetchPoolDetails fetchPoolDetails,
                              CancelTrip cancelTrip) {
        this.ridePoolService=ridePoolService;
        this.jwtUtil=jwtUtil;
        this.fetchPoolDetails=fetchPoolDetails;
        this.cancelTrip=cancelTrip;
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

    @GetMapping("/fetchTripDetails")
    public ResponseEntity<?> fetchTripDetails(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String username = jwtUtil.extractUsername(token);
        System.out.println(username);
        return fetchPoolDetails.fetchPoolMembers(username);
    }

    @GetMapping("/cancelTrip")
    public ResponseEntity<?> cancelTrip(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String username = jwtUtil.extractUsername(token);
        return cancelTrip.cancelUserTrip(username);
    }
}
