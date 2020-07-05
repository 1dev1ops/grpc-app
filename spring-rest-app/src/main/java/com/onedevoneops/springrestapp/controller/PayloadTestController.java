package com.onedevoneops.springrestapp.controller;

import com.onedevoneops.springrestapp.bean.request.GetHeroNames;
import com.onedevoneops.springrestapp.bean.request.GetTeamFights;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author erdoganf
 */
@RequestMapping("/api/payloads")
@RestController
public class PayloadTestController {

  private final GrpcController payloadTestService;

  public PayloadTestController(GrpcController payloadTestService) {
    this.payloadTestService = payloadTestService;
  }

  @GetMapping("/heroes")
  public ResponseEntity<GetHeroNames> getSmallPayload() {
    try {
      return payloadTestService.getSmallPayload();
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/fights")
  public ResponseEntity<GetTeamFights> getLargePayload() {
    try {
      return payloadTestService.getLargePayload();
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/heroes")
  public ResponseEntity postSmallPayload(@RequestBody GetHeroNames request) {
    payloadTestService.postSmallPayload(request);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/fights")
  public ResponseEntity postLargePayload(@RequestBody GetTeamFights request) {
    payloadTestService.postLargePayload(request);
    return ResponseEntity.ok().build();
  }
}
