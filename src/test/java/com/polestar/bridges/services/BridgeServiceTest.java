package com.polestar.bridges.services;

import com.polestar.bridges.Daos.BridgeRepository;
import com.polestar.bridges.entities.Bridge;
import com.polestar.bridges.exception.LatAndLongAlreadyExistException;
import com.polestar.bridges.exception.NameAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BridgeServiceTest {

    private BridgeRepository bridgeRepository;
    private BridgeService bridgeService;

    @BeforeEach
    public void initEachTest(){
        bridgeRepository = Mockito.mock(BridgeRepository.class);
        bridgeService = new BridgeServiceImp(bridgeRepository);
    }


    @Test
    public void testAddBridgeSuccess(){
        Bridge bridgeToAdd = new Bridge(1,2,3,4,56,"London Bridge");


        when(bridgeRepository.findBridgeByName(ArgumentMatchers.anyString())).thenReturn(null);
        when(bridgeRepository.findBridgeByLatitudeAndLongitude(ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble())).thenReturn(null);
        when(bridgeRepository.save(ArgumentMatchers.any(Bridge.class))).thenReturn(bridgeToAdd);

        Assert.isInstanceOf(Bridge.class, bridgeService.addBridge(bridgeToAdd), "Should return a bridge");
        verify(bridgeRepository,times(1)).findBridgeByName(ArgumentMatchers.anyString());
        verify(bridgeRepository,times(1)).findBridgeByLatitudeAndLongitude(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble());
        verify(bridgeRepository,times(1)).save(ArgumentMatchers.any(Bridge.class));
    }

    @Test
    public void testAddBridgeFailedNameAlreadyExist(){
        Bridge bridgeToAdd = new Bridge(1,2,3,4,56,"London Bridge");


        when(bridgeRepository.findBridgeByName(ArgumentMatchers.anyString())).thenReturn(bridgeToAdd);

        try{
            bridgeService.addBridge(bridgeToAdd);
        }catch (Exception e){
            Assert.isInstanceOf(NameAlreadyExistException.class, e, "Should return exception when name already exist");
        }
        verify(bridgeRepository,times(1)).findBridgeByName(ArgumentMatchers.anyString());
        verify(bridgeRepository,times(0)).findBridgeByLatitudeAndLongitude(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble());
        verify(bridgeRepository,times(0)).save(ArgumentMatchers.any(Bridge.class));
    }

    @Test
    public void testAddBridgeFailedLocationOccupied(){
        Bridge bridgeToAdd = new Bridge(1,2,3,4,56,"London Bridge");
        Bridge bridgeDB = new Bridge(1,2,3,4,56,"Rio Bridge");


        when(bridgeRepository.findBridgeByName(ArgumentMatchers.anyString())).thenReturn(null);
        when(bridgeRepository.findBridgeByLatitudeAndLongitude(ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble())).thenReturn(bridgeDB);

        try{
            bridgeService.addBridge(bridgeToAdd);
        }catch (Exception e){
            Assert.isInstanceOf(LatAndLongAlreadyExistException.class, e, "Should return exception when location already occupied");
        }
        verify(bridgeRepository,times(1)).findBridgeByName(ArgumentMatchers.anyString());
        verify(bridgeRepository,times(1)).findBridgeByLatitudeAndLongitude(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble());
        verify(bridgeRepository,times(0)).save(ArgumentMatchers.any(Bridge.class));
    }

    @Test
    public void testGetAllBridges() {
        Bridge bridgeDB1 = new Bridge(1,2,3,78,56,"Rio Bridge");
        Bridge bridgeDB2 = new Bridge(1,2,3,4,20,"Denver Bridge");

        List<Bridge> databaseResponse = new ArrayList<>();
        databaseResponse.add(bridgeDB1);
        databaseResponse.add(bridgeDB2);

        when(bridgeRepository.findAll()).thenReturn(databaseResponse);

        Assert.isInstanceOf(ArrayList.class, bridgeService.getAllBridges(), "Should return a list of bridges");
    }

}
