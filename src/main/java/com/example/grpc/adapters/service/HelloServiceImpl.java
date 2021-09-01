package com.example.grpc.adapters.service;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import com.example.grpc.cmd.CmdInterface;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
  private static final Logger logger = LoggerFactory.getLogger(CmdInterface.class);

  @Override
  public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    logger.info("New request - {}", request.toString());

    String greeting = new StringBuilder()
            .append("Hello, ")
            .append(request.getFirstName())
            .append(" ")
            .append(request.getLastName())
            .toString();

    HelloResponse response = HelloResponse.newBuilder()
            .setGreeting(greeting)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
