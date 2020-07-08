package com.onedevoneops.client.benchmark;

import com.onedevoneops.grpc.service.PayloadTestServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.springframework.web.client.RestTemplate;

/**
 * @author erdoganf
 */
@State(Scope.Benchmark)
public class ExecutionPlan {

  private static final ManagedChannel CHANNEL =
      ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

  public RestTemplate restTemplate;

  public PayloadTestServiceGrpc.PayloadTestServiceBlockingStub stub;

  public String uriForSmallPayload;

  public String uriForLargePayload;

  @Setup(Level.Invocation)
  public void setUp() {
    stub = PayloadTestServiceGrpc.newBlockingStub(CHANNEL)
                                 .withMaxInboundMessageSize(100000000);

    restTemplate = new RestTemplate();

    uriForSmallPayload = "http://localhost:8081/api/payloads/heroes";
    uriForLargePayload = "http://localhost:8081/api/payloads/fights";
  }
}