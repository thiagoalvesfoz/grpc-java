package com.example.grpc.adapters.client;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import com.example.grpc.cmd.CmdInterface;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.grpc.helpers.Utils.DEFAULT_HOST;

public class GrpcClient {
  private static final Logger logger = LoggerFactory.getLogger(CmdInterface.class);

  public static void run(int PORT_NUMBER, String firstName, String lastName) {
    logger.info("Client started. Connecting to server...\n");

    firstName = !firstName.isBlank() ? firstName : "Foo";
    lastName = !lastName.isBlank() ? lastName : "Bar";

    ManagedChannel channel = ManagedChannelBuilder.forAddress(DEFAULT_HOST, PORT_NUMBER)
            .usePlaintext()
            .build();

    HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

    HelloResponse helloResponse = stub.hello(
            HelloRequest.newBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .build()
    );

    logger.info("New response - {}", helloResponse.getGreeting());
    channel.shutdown();
  }
}
