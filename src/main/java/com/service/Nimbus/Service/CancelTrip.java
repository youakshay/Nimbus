package com.service.Nimbus.Service;

import com.service.Nimbus.Model.Pool;
import com.service.Nimbus.Model.PoolMember;
import com.service.Nimbus.Model.Trip;
import com.service.Nimbus.Respository.CrudRepo;
import com.service.Nimbus.Respository.PoolMemberRepository;
import com.service.Nimbus.Respository.PoolRepository;
import com.service.Nimbus.Respository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CancelTrip {
    private final PoolRepository poolRepository;
    private final PoolMemberRepository poolMemberRepository;
    private final TripRepository tripRepository;
    private final CrudRepo crudRepo;

    public CancelTrip(PoolRepository poolRepository,
                      PoolMemberRepository poolMemberRepository,
                      TripRepository tripRepository,
                      CrudRepo crudRepo) {
        this.poolRepository = poolRepository;
        this.poolMemberRepository = poolMemberRepository;
        this.tripRepository = tripRepository;
        this.crudRepo = crudRepo;
    }

    public ResponseEntity<?> cancelUserTrip(String username) {
        Long userId, tripId, poolId;
        userId = crudRepo.findIdFromUsername(username);
        tripId = tripRepository.findTripIdByUserId(userId);
        if(tripId == null) {
            return ResponseEntity.badRequest().body("You have no active trip!!!");
        }
        poolId = poolMemberRepository.findPoolIdByTripId(tripId);
        if(poolMemberRepository.countNoOfMembersInAPool(poolId) == 1) {
            tripRepository.deleteById(tripId);
            poolRepository.deleteById(poolId);
        }else{
            Optional<Trip> tripToDelete = tripRepository.findById(tripId);
            Optional<Pool> poolToUpdate = poolRepository.findById(poolId);
            poolToUpdate.get().setSeatsAvailable(poolToUpdate.get().getSeatsAvailable() + tripToDelete.get().getSeatsRequired());
            System.out.println(poolToUpdate);
            poolRepository.save(poolToUpdate.get());
            tripRepository.deleteById(tripId);
        }
        return ResponseEntity.ok().body("Trip deleted Successfully");
    }
}
