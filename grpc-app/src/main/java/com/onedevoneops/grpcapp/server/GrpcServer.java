package com.onedevoneops.grpcapp.server;

import com.onedevoneops.grpcapp.service.PayloadTestServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

/**
 * @author erdoganf
 */
public class GrpcServer {

  private static final Server SERVER = ServerBuilder.forPort(8080)
                                                    .addService(new PayloadTestServiceImpl())
                                                    .build();

  public void initializeServer() throws IOException, InterruptedException {
    SERVER.start();
    SERVER.awaitTermination();
  }
}
