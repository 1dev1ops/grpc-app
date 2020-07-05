package com.onedevoneops.grpcserver.grpcapp;

import com.onedevoneops.grpcserver.server.GrpcServer;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcAppApplication {

  public static void main(String[] args) throws IOException, InterruptedException {
    SpringApplication.run(GrpcAppApplication.class, args);
    GrpcServer server = new GrpcServer();

    server.initializeServer();
  }
}
