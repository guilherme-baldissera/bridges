package com.polestar.bridges.services;

import com.polestar.bridges.Daos.BridgeRepository;
import com.polestar.bridges.entities.Bridge;
import com.polestar.bridges.exception.LatAndLongAlreadyExistException;
import com.polestar.bridges.exception.NameAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BridgeServiceImp implements BridgeService {

    private BridgeRepository bridgeRepository;

    @Autowired
    public BridgeServiceImp(BridgeRepository bridgeRepository){
        this.bridgeRepository = bridgeRepository;
    }

    @Override
    public Bridge addBridge(Bridge bridge) {
        this.validateConstraints(bridge);
        return  bridgeRepository.save(bridge);
    }

    @Override
    public List<Bridge> getAllBridges() {
        Iterable<Bridge> bridgesIterable = bridgeRepository.findAll();
        List<Bridge> bridges = new ArrayList<>();

        bridgesIterable.forEach(bridge -> bridges.add(bridge));

        //Do some validation Here

        return bridges;
    }

    public void validateConstraints(Bridge bridge){

        Bridge bridgeFromDbByName = bridgeRepository.findBridgeByName(bridge.getName());
        if(bridgeFromDbByName != null){
            throw  new NameAlreadyExistException("There is already a bridge with this name");
        }
        Bridge bridgeFromDbByPosition = bridgeRepository.findBridgeByLatitudeAndLongitude(bridge.getLatitude(), bridge.getLongitude());
        if(bridgeFromDbByPosition != null){
            throw new LatAndLongAlreadyExistException("There is already a bridge on this location");
        }
    }

}
