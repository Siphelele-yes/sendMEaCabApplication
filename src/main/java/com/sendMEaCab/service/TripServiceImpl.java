package com.sendMEaCab.service;

import com.sendMEaCab.model.Trip;
import com.sendMEaCab.repository.TripRepository;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Override
    public Trip saveTrip(final Trip trip){
        return tripRepository.save(trip);
    }

}
