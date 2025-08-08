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

import java.util.List;

@Service
public class RidePoolService {
    private final TripRepository tripRepository;
    private final PoolRepository poolRepository;
    private final PoolMemberRepository poolMemberRepository;
    private final CrudRepo crudRepo;

    public RidePoolService(TripRepository tripRepository,
                           PoolRepository poolRepository,
                           PoolMemberRepository poolMemberRepository,
                           CrudRepo crudRepo) {
        this.tripRepository = tripRepository;
        this.poolRepository = poolRepository;
        this.poolMemberRepository = poolMemberRepository;
        this.crudRepo=crudRepo;
    }

    public ResponseEntity<?> requestTrip(Trip trip, String username) {
        Long user_id = crudRepo.findIdFromUsername(username);
        Trip newTrip = new Trip(
                trip.getId(),
                trip.getFromLocation(),
                trip.getToLocation(),
                trip.getDepartureTime(),
                trip.getSeatsRequired(),
                user_id
        );
        Trip savedTrip = tripRepository.save(newTrip);

        List<Pool> poolList = poolRepository.searchPool(trip.getFromLocation(),
                                                        trip.getToLocation(),
                                                        trip.getDepartureTime(),
                                                        trip.getSeatsRequired());
        Pool newPool;
        if(poolList.size() == 0){
            newPool = new Pool(
                    null,
                    trip.getFromLocation(),
                    trip.getToLocation(),
                    trip.getDepartureTime(),
                    4,
                    4-trip.getSeatsRequired()
            );
        }else{
            Pool currPool = poolList.get(0);
            newPool = new Pool(
                    currPool.getId(),
                    currPool.getFromLocation(),
                    currPool.getToLocation(),
                    currPool.getDepartureTime(),
                    currPool.getCapacity(),
                    currPool.getSeatsAvailable() - trip.getSeatsRequired()
            );
        }
        Pool savedPool = poolRepository.save(newPool);
        System.out.println(savedPool);
        PoolMember poolMember = new PoolMember(
                null,
                savedPool.getId(),
                savedTrip.getId()
        );
        poolMemberRepository.save(poolMember);
        System.out.println(poolList);
        return ResponseEntity.ok(savedPool);
    }
}
