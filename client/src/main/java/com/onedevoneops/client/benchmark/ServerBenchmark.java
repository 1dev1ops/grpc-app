package com.onedevoneops.client.benchmark;

import com.google.protobuf.Empty;
import com.onedevoneops.grpcserver.service.HeroNameResponse;
import com.onedevoneops.springrestapp.bean.request.GetHeroNames;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.http.ResponseEntity;

/**
 * @author erdoganf
 */
public class ServerBenchmark {

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkGrpc(ExecutionPlan plan, Blackhole blackhole) {
    HeroNameResponse smallPayload = plan.stub.getSmallPayload(Empty.newBuilder().build());
    blackhole.consume(smallPayload);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkRest(ExecutionPlan plan, Blackhole blackhole) {
    ResponseEntity<GetHeroNames> smallPayloadRest = plan.restTemplate.getForEntity("http://localhost:8081/api/payloads/heroes", GetHeroNames.class);
    blackhole.consume(smallPayloadRest);
  }
}
