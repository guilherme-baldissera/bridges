package com.polestar.bridges.services;

import com.polestar.bridges.Daos.BridgeRepository;
import com.polestar.bridges.entities.Bridge;
import com.polestar.bridges.exception.LatAndLongAlreadyExistException;
import com.polestar.bridges.exception.NameAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BridgeServiceImp implements BridgeService {

    private static String NAME_ALREADY_EXIST = "There is already a bridge with this name";
    private static String LOCATION_OCCUPIED = "There is already a bridge at this location";

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
        List<Bridge> bridges = bridgeRepository.findAll();

        return bridges;
    }

    public void validateConstraints(Bridge bridge){

        Bridge bridgeFromDbByName = bridgeRepository.findBridgeByName(bridge.getName());
        if(bridgeFromDbByName != null){
            throw  new NameAlreadyExistException(NAME_ALREADY_EXIST);
        }
        Bridge bridgeFromDbByPosition = bridgeRepository.findBridgeByLatitudeAndLongitude(bridge.getLatitude(), bridge.getLongitude());
        if(bridgeFromDbByPosition != null){
            throw new LatAndLongAlreadyExistException(LOCATION_OCCUPIED);
        }
    }

}
