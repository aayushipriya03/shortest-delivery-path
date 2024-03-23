package com.deliverysystem.controller;

import com.deliverysystem.dto.RequestDto;
import com.deliverysystem.service.LoadBalancer;
import com.deliverysystem.service.OptimizedPathFinder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/shortest-delivery-path")
@AllArgsConstructor
public class DeliverySystemController {

    private OptimizedPathFinder optimizedPathFinder;

    private LoadBalancer loadBalancer;

    @PostMapping("")
    public ResponseEntity<?> calculateShortestDeliveryPath(@RequestBody RequestDto requestDto) {
        try {
            log.info("Request Body: {}", requestDto);
            double response = optimizedPathFinder.minimumTimeCalculator(requestDto);
            return new ResponseEntity<>(Map.of("minimum time", response), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred in triggering api call: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/server")
    public void  callServer(){
        loadBalancer.loadBalancing();
    }

}
