package com.polestar.bridges.controllers;

import com.polestar.bridges.entities.Bridge;
import com.polestar.bridges.services.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("bridges")
public class BridgeController {

    private BridgeService bridgeService;

    @Autowired
    public BridgeController(BridgeService bridgeService){
        this.bridgeService = bridgeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Bridge> getAllBridges(){
        return bridgeService.getAllBridges();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Bridge addBridge(@RequestBody Bridge bridge){
        return bridgeService.addBridge(bridge);
    }

}
