package com.onedevoneops.client.benchmark;

import com.google.protobuf.Empty;
import com.onedevoneops.client.benchmark.ExecutionState;
import com.onedevoneops.grpc.service.HeroNameResponse;
import com.onedevoneops.grpc.service.TeamFightResponse;
import com.onedevoneops.springrestapp.bean.request.GetHeroNames;
import com.onedevoneops.springrestapp.bean.request.GetTeamFights;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.http.ResponseEntity;

/**
 * @author erdoganf
 */
public class ServerBenchmark {

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void grpcGetWithSmallPayload(ExecutionState state, Blackhole blackhole) {
    HeroNameResponse heroNameResponse = state.stub.getSmallPayload(Empty.newBuilder().build());

    blackhole.consume(heroNameResponse);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void grpcGetWithLargePayload(ExecutionState state, Blackhole blackhole) {
    TeamFightResponse teamFightResponse = state.stub.getLargePayload(Empty.newBuilder().build());

    blackhole.consume(teamFightResponse);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void grpcPostWithSmallPayload(ExecutionState state, Blackhole blackhole) {
    Empty empty = state.stub.postSmallPayload(state.heroNameGrpcRequest);

    blackhole.consume(empty);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void grpcPostWithLargePayload(ExecutionState state, Blackhole blackhole) {
    Empty empty = state.stub.postLargePayload(state.teamFightGrpcRequest);

    blackhole.consume(empty);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void restGetWithSmallPayload(ExecutionState plan, Blackhole blackhole) {
    ResponseEntity<GetHeroNames> smallPayloadRest = plan.restTemplate.getForEntity(plan.uriForSmallPayload,
                                                                                   GetHeroNames.class);
    blackhole.consume(smallPayloadRest);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void restGetWithLargePayload(ExecutionState plan, Blackhole blackhole) {
    ResponseEntity<GetTeamFights> largePayloadRest = plan.restTemplate.getForEntity(plan.uriForLargePayload,
                                                                                    GetTeamFights.class);
    blackhole.consume(largePayloadRest);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void restPostWithSmallPayload(ExecutionState state, Blackhole blackhole) {
    ResponseEntity<GetHeroNames> response = state.restTemplate.postForEntity(state.uriForSmallPayload,
                                                                             state.heroNameRestRequest,
                                                                             GetHeroNames.class);

    blackhole.consume(response);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void restPostWithLargePayload(ExecutionState state, Blackhole blackhole) {
    ResponseEntity<GetTeamFights> response = state.restTemplate.postForEntity(state.uriForLargePayload,
                                                                              state.teamFightRestRequest,
                                                                              GetTeamFights.class);

    blackhole.consume(response);
  }
}
