package com.onedevoneops.client.benchmark;

import com.onedevoneops.grpcserver.service.PayloadTestServiceGrpc;
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
@State(Scope.Thread)
public class ExecutionPlan {

  private static final ManagedChannel CHANNEL =
      ManagedChannelBuilder.forAddress("localhost", 8080).maxInboundMessageSize(9999999).usePlaintext().build();

  public RestTemplate restTemplate;

  public PayloadTestServiceGrpc.PayloadTestServiceBlockingStub stub;

  @Setup(Level.Trial)
  public void setUp() {
    stub = PayloadTestServiceGrpc.newBlockingStub(CHANNEL);

    restTemplate = new RestTemplate();
  }
}
