package com.onedevoneops.client.benchmark;

import com.onedevoneops.grpc.service.HeroNameResponse;
import com.onedevoneops.grpc.service.PayloadTestServiceGrpc;
import com.onedevoneops.grpc.service.TeamFightResponse;
import com.onedevoneops.grpcapp.service.PayloadTestServiceImpl;
import com.onedevoneops.springrestapp.bean.request.GetHeroNames;
import com.onedevoneops.springrestapp.bean.request.GetTeamFights;
import com.onedevoneops.springrestapp.service.PayloadTestService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.springframework.web.client.RestTemplate;

/**
 * @author erdoganf
 */
@State(Scope.Benchmark)
public class ExecutionState {

  private static final ManagedChannel CHANNEL = ManagedChannelBuilder.forAddress("localhost", 8080)
                                                                     .usePlaintext()
                                                                     .build();

  public PayloadTestServiceGrpc.PayloadTestServiceBlockingStub stub;

  public RestTemplate restTemplate;

  public String uriForSmallPayload;

  public String uriForLargePayload;

  public HeroNameResponse heroNameGrpcRequest;

  public TeamFightResponse teamFightGrpcRequest;

  public GetHeroNames heroNameRestRequest;

  public GetTeamFights teamFightRestRequest;

  @Setup(Level.Trial)
  public void setUp() throws IOException {
    stub = PayloadTestServiceGrpc.newBlockingStub(CHANNEL)
                                 .withMaxInboundMessageSize(100000000);

    restTemplate = new RestTemplate();

    uriForSmallPayload = "http://localhost:8081/api/payloads/heroes";
    uriForLargePayload = "http://localhost:8081/api/payloads/fights";

    heroNameGrpcRequest = HeroNameResponse.newBuilder()
                                          .addAllHeroNames(PayloadTestServiceImpl.getHeroNamesProto())
                                          .build();
    teamFightGrpcRequest = TeamFightResponse.newBuilder()
                                            .addAllTeamFights(PayloadTestServiceImpl.getTeamFightsProto())
                                            .build();
    heroNameRestRequest = PayloadTestService.getHeroNamesRequest();
    teamFightRestRequest = PayloadTestService.getTeamFightsRequest();
  }
}
