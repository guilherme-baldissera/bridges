package com.polestar.bridges.Daos;

import com.polestar.bridges.entities.Bridge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BridgeRepository extends CrudRepository<Bridge, Integer> {

    Bridge findBridgeByName(String name);

    Bridge findBridgeByLatitudeAndLongitude(double latitude, double longitude);

    List<Bridge> findAll();
}
