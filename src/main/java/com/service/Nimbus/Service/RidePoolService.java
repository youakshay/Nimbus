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
                trip.getFrom_location(),
                trip.getTo_location(),
                trip.getDeparture_time(),
                trip.getSeats_required(),
                user_id
        );
        Trip savedTrip = tripRepository.save(newTrip);

        List<Pool> poolList = poolRepository.searchPool(trip.getFrom_location(),
                                                        trip.getTo_location(),
                                                        trip.getDeparture_time(),
                                                        trip.getSeats_required());
        Pool newPool;
        if(poolList.size() == 0){
            newPool = new Pool(
                    null,
                    trip.getFrom_location(),
                    trip.getTo_location(),
                    trip.getDeparture_time(),
                    4,
                    4-trip.getSeats_required()
            );
        }else{
            Pool currPool = poolList.get(0);
            newPool = new Pool(
                    currPool.getId(),
                    currPool.getFrom_location(),
                    currPool.getTo_location(),
                    currPool.getDeparture_time(),
                    currPool.getCapacity(),
                    currPool.getSeats_available() - trip.getSeats_required()
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
