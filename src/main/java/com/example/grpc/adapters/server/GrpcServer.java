package com.example.grpc.adapters.server;

import com.example.grpc.adapters.service.HelloServiceImpl;
import com.example.grpc.cmd.CmdInterface;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GrpcServer {
  private static final Logger logger = LoggerFactory.getLogger(CmdInterface.class);

  public static void run(int PORT_NUMBER) throws IOException, InterruptedException {

    Server server = ServerBuilder
            .forPort(PORT_NUMBER)
            .addService(new HelloServiceImpl())
            .build();


    server.start();

    logger.info("Server started");
    server.awaitTermination();
  }
}
