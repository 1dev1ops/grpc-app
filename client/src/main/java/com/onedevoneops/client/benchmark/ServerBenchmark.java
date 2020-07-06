package com.onedevoneops.client.benchmark;

import com.google.protobuf.Empty;
import com.onedevoneops.grpcserver.service.HeroNameResponse;
import com.onedevoneops.grpcserver.service.TeamFight;
import com.onedevoneops.grpcserver.service.TeamFightResponse;
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
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkSmallPayloadGrpc(ExecutionPlan plan, Blackhole blackhole) {
    HeroNameResponse smallPayload = plan.stub.getSmallPayload(Empty.newBuilder().build());
    blackhole.consume(smallPayload);
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkSmallPayloadRest(ExecutionPlan plan, Blackhole blackhole) {
    ResponseEntity<GetHeroNames> smallPayloadRest = plan.restTemplate.getForEntity("http://localhost:8081/api/payloads/heroes", GetHeroNames.class);
    blackhole.consume(smallPayloadRest);
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkLargePayloadGrpc(ExecutionPlan plan, Blackhole blackhole) {
    TeamFightResponse largePayload = plan.stub.getLargePayload(Empty.newBuilder().build());
    blackhole.consume(largePayload);
  }

  @Benchmark
  @BenchmarkMode(Mode.All)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkLargePayloadRest(ExecutionPlan plan, Blackhole blackhole) {
    ResponseEntity<GetTeamFights> largePayloadRest = plan.restTemplate.getForEntity("http://localhost:8081/api/payloads/teamfights", GetTeamFights.class);
    blackhole.consume(largePayloadRest);
  }
}
