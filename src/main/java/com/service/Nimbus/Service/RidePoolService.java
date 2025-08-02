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
                trip.id(),
                trip.from_location(),
                trip.to_location(),
                trip.departure_time(),
                trip.seats_required(),
                user_id
        );
        Trip savedTrip = tripRepository.save(newTrip);

        List<Pool> poolList = poolRepository.searchPool(trip.from_location(),
                trip.to_location(),
                trip.departure_time(),
                trip.seats_required());
        if(poolList.size() == 0){
            Pool newPool = new Pool(
                    null,
                    trip.from_location(),
                    trip.to_location(),
                    trip.departure_time(),
                    4,
                    4-trip.seats_required()
            );
            Pool savedPool = poolRepository.save(newPool);
            System.out.println(savedPool);
            PoolMember poolMember = new PoolMember(
                    null,
                    savedPool.id(),
                    savedTrip.id()
            );
            poolMemberRepository.save(poolMember);
        }else{
            Pool currPool = poolList.get(0);
            Pool newPool = new Pool(
                    currPool.id(),
                    currPool.from_location(),
                    currPool.to_location(),
                    currPool.departure_time(),
                    currPool.capacity(),
                    currPool.seats_available() - trip.seats_required()
            );
            Pool savedPool = poolRepository.save(newPool);
            PoolMember poolMember = new PoolMember(
                    null,
                    savedPool.id(),
                    savedTrip.id()
            );
            System.out.println(savedPool);
            poolMemberRepository.save(poolMember);
        }
        System.out.println(poolList);
        return ResponseEntity.ok(poolList);
    }
}
