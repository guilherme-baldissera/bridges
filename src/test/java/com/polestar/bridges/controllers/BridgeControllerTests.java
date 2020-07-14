package com.polestar.bridges.controllers;

import com.polestar.bridges.entities.Bridge;
import com.polestar.bridges.services.BridgeService;
import org.junit.jupiter.api.BeforeAll;
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
public class BridgeControllerTests {

    private BridgeService bridgeService;
    private BridgeController bridgeController;

    @BeforeAll
    public void init(){
        bridgeService = Mockito.mock(BridgeService.class);
    }

    @BeforeEach
    public void initEachTest(){
        bridgeController = new BridgeController(bridgeService);
    }

    @Test
    public void testGetAllBridges(){
        Bridge bridge = new Bridge(1,2,3,4,56,"London Bridge");
        List<Bridge> bridgeList = new ArrayList<Bridge>();
        bridgeList.add(bridge);

        when(bridgeService.getAllBridges()).thenReturn(bridgeList);

        Assert.isInstanceOf(ArrayList.class, bridgeController.getAllBridges(),"Should return a list of bridges");
        verify(bridgeService, times(1)).getAllBridges();
    }

    @Test
    public void testAddBridge(){
        Bridge bridge = new Bridge(1,2,3,4,56,"London Bridge");

        when(bridgeService.addBridge(ArgumentMatchers.any(Bridge.class))).thenReturn(bridge);

        Assert.isInstanceOf(Bridge.class, bridgeController.addBridge(bridge), "Should return a bridge");
        verify(bridgeService, times(1)).addBridge(ArgumentMatchers.any(Bridge.class));
    }
}
