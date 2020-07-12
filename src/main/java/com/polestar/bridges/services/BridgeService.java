package com.polestar.bridges.services;

import com.polestar.bridges.entities.Bridge;

import java.util.List;

public interface BridgeService {

    Bridge addBridge(Bridge bridge);

    List<Bridge> getAllBridges();
}
