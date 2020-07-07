package com.onedevoneops.client.benchmark;

import com.google.protobuf.Empty;
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
  public void benchmarkGrpcWithSmallPayload(ExecutionPlan plan, Blackhole blackhole) {
    HeroNameResponse smallPayload = plan.stub.getSmallPayload(Empty.newBuilder().build());

    blackhole.consume(smallPayload);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkSmallPayloadRest(ExecutionPlan plan, Blackhole blackhole) {
    ResponseEntity<GetHeroNames> smallPayloadRest = plan.restTemplate.getForEntity(plan.uriForSmallPayload, GetHeroNames.class);
    blackhole.consume(smallPayloadRest);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkGrpcWithLargePayload(ExecutionPlan plan, Blackhole blackhole) {
    TeamFightResponse largePayload = plan.stub.getLargePayload(Empty.newBuilder().build());

    blackhole.consume(largePayload);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkLargePayloadRest(ExecutionPlan plan, Blackhole blackhole) {
    ResponseEntity<GetTeamFights> largePayloadRest = plan.restTemplate.getForEntity(plan.uriForLargePayload, GetTeamFights.class);
    blackhole.consume(largePayloadRest);
  }
}
