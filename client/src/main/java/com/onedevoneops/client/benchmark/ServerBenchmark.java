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
import org.springframework.http.ResponseEntity;

/**
 * @author erdoganf
 */
public class ServerBenchmark {

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkGrpc(ExecutionPlan plan) {
    HeroNameResponse smallPayload = plan.stub.getSmallPayload(Empty.newBuilder().build());
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void benchmarkRest(ExecutionPlan plan) {
    String url = "http://localhost:8081/api/payloads/heroes";
    ResponseEntity<GetHeroNames> smallPayloadRest = plan.restTemplate.getForEntity(url, GetHeroNames.class);
  }
}
