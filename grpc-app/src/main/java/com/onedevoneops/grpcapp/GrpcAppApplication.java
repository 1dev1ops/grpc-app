package com.onedevoneops.grpcapp;

import com.onedevoneops.grpcapp.server.GrpcServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcAppApplication {

  public static void main(String[] args) throws IOException, InterruptedException {
    SpringApplication.run(GrpcAppApplication.class, args);
    Logger.getLogger("io.netty").setLevel(Level.OFF);

    GrpcServer grpcServer = new GrpcServer();

    grpcServer.initializeServer();
  }

}
